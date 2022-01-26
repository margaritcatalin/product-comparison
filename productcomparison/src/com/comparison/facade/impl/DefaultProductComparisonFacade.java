package com.comparison.facade.impl;

import com.comparison.constants.ProductcomparisonConstants;
import com.comparison.data.ComparisonItemData;
import com.comparison.data.WrapperQueueProductComparison;
import com.comparison.facade.ProductComparisonFacade;
import com.comparison.model.config.ProductComparisonPropertyModel;
import com.comparison.service.ProductComparisonPropertyService;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * @author Catalin Margarit
 */
public class DefaultProductComparisonFacade implements ProductComparisonFacade {
    private final ProductComparisonPropertyService<ProductModel, Object> productComparisonPropertyService;
    private final SessionService sessionService;
    private final ConfigurationService configurationService;
    private final ProductService productService;
    private final ProductFacade productFacade;
    final List<ProductOption> options = new ArrayList<>(Arrays.asList(ProductOption.BASIC, ProductOption.URL, ProductOption.GALLERY));

    private final Logger LOG = Logger.getLogger(DefaultProductComparisonFacade.class);
    private final int MAX_QUEUE_SIZE;

    public DefaultProductComparisonFacade(ProductComparisonPropertyService<ProductModel, Object> productComparisonPropertyService, SessionService sessionService, ConfigurationService configurationService, ProductService productService, ProductFacade productFacade) {
        this.productComparisonPropertyService = productComparisonPropertyService;
        this.sessionService = sessionService;
        this.configurationService = configurationService;
        this.productService = productService;
        this.productFacade = productFacade;
        MAX_QUEUE_SIZE = getConfigurationService().getConfiguration().getInt("product.comparison.max.queue.size", 3);
    }

    @Override
    public Map<String, LinkedList<Object>> processProductComparison() {
        Map<String, LinkedList<Object>> productComparison = new HashMap<>();
        List<String> productCodes = getProductComparisonCodes();
        List<ProductComparisonPropertyModel> allComparisonAttributes = getProductComparisonPropertyService().getAllProductComparisonProperties();
        if (CollectionUtils.isNotEmpty(productCodes) && CollectionUtils.isNotEmpty(allComparisonAttributes)) {
            for (var productComparisonProperty : allComparisonAttributes) {
                LinkedList<Object> attributeValues = new LinkedList<Object>();
                String attributeName = null;
                for (var productCode : productCodes) {
                    ProductModel productModel = getProductService().getProductForCode(productCode);
                    Object attributeValue = getProductComparisonPropertyService().getAttributeValue(productModel, productComparisonProperty);
                    attributeValues.add(attributeValue);
                    if (Objects.isNull(attributeName)) {
                        attributeName = getProductComparisonPropertyService().getAttributeLabel(productModel, productComparisonProperty);
                    }
                }
                if (Objects.nonNull(attributeName) && attributeValues.stream().anyMatch(Objects::nonNull)) {
                    productComparison.put(attributeName, attributeValues);
                }
            }
        }
        return productComparison;
    }

    @Override
    public Map<String, Boolean> getAllMarkedElements(Map<String, LinkedList<Object>> productComparison) {
        Map<String, Boolean> markElements = new HashMap<>();
        for (Map.Entry<String, LinkedList<Object>> entry : productComparison.entrySet()) {
            markElements.put(entry.getKey(), !verifyAllEqualUsingStream(entry.getValue()));
        }
        return markElements;
    }

    public boolean verifyAllEqualUsingStream(List<Object> list) {
        return list.stream()
                .distinct()
                .count() <= 1;
    }

    @Override
    public List<String> getProductComparisonCodes() {
        final List productComparisonCodes = Arrays.asList(retrieveQueueFromSession());
        if (CollectionUtils.isNotEmpty(productComparisonCodes)) {
            return (List<String>) productComparisonCodes.get(0);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean addProductToProductComparisonQueue(final String code) {
        if (Objects.isNull(code)) {
            return false;
        }

        //RETRIEVE QUEUE
        final Queue queue = retrieveQueueFromSession();

        //if the variable size hits the max, then remove an entry.
        if ((queue.size() == this.MAX_QUEUE_SIZE)) {
            if (!queue.contains(code)) {
                queue.remove();
            }
        }
        if (queue.contains(code)) {
            queue.remove(code);
        } else {
            if (this.MAX_QUEUE_SIZE > 0) {
                queue.add(code);
            }
        }
        updateQueueToSession(queue);

        return true;
    }

    @Override
    public String removeProductComparisonQueue() {
        //RETRIEVE CURRENT QUEUE
        final Queue queue = retrieveQueueFromSession();

        if (queue.size() <= 0) {
            return null;
        }

        final String removed = (String) queue.remove();

        //UPDATE CURRENT QUEUE
        updateQueueToSession(queue);
        return removed;
    }

    @Override
    public boolean removeProductFromProductComparisonQueue(final String productCode) {
        //RETRIEVE QUEUE
        final Queue queue = retrieveQueueFromSession();
        if (productCode == null || productCode.isEmpty() || queue.size() < 0) {
            return false;
        }
        final boolean removed = queue.remove(productCode);

        //UPDATE QUEUE
        updateQueueToSession(queue);
        return removed;
    }

    @Override
    public void clearProductComparisonQueue() {
        final Queue queue = retrieveQueueFromSession();
        queue.clear();
        updateQueueToSession(queue);
    }

    @Override
    public List<ComparisonItemData> getAllComparisonElements() {
        final List<String> codes = this.getProductComparisonCodes();
        List<ComparisonItemData> products = new ArrayList<>();
        codes.forEach(code -> {
            ProductModel productModel = getProductService().getProductForCode(code);
            if (Objects.nonNull(productModel)) {
                ComparisonItemData item = new ComparisonItemData();
                item.setCode(productModel.getCode());
                item.setName(productModel.getName());
                products.add(item);
            }
        });
        return products;
    }

    private Queue retrieveQueueFromSession() {
        if (sessionService.getAttribute(ProductcomparisonConstants.SESSION_ATTR_PRODUCTCOMPARISON) == null) {
            sessionService.setAttribute(ProductcomparisonConstants.SESSION_ATTR_PRODUCTCOMPARISON,
                    new WrapperQueueProductComparison());
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Retrieving Session id = " + getSessionService().getCurrentSession().getSessionId());
        }
        return ((WrapperQueueProductComparison) getSessionService().getAttribute(ProductcomparisonConstants.SESSION_ATTR_PRODUCTCOMPARISON)).getQueue();
    }

    private void updateQueueToSession(final Queue queue) {
        final WrapperQueueProductComparison wrapperQueue = (WrapperQueueProductComparison) sessionService
                .getAttribute(ProductcomparisonConstants.SESSION_ATTR_PRODUCTCOMPARISON);
        wrapperQueue.setQueue(queue);
        sessionService.setAttribute(ProductcomparisonConstants.SESSION_ATTR_PRODUCTCOMPARISON, wrapperQueue);
    }

    public ProductComparisonPropertyService<ProductModel, Object> getProductComparisonPropertyService() {
        return productComparisonPropertyService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }
}
