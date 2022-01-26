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
 * The type Default product comparison facade.
 *
 * @author Catalin Margarit
 */
public class DefaultProductComparisonFacade implements ProductComparisonFacade {
    private final ProductComparisonPropertyService<ProductModel, Object> productComparisonPropertyService;
    private final SessionService sessionService;
    private final ConfigurationService configurationService;
    private final ProductService productService;
    private final ProductFacade productFacade;
    /**
     * The Options.
     */
    final List<ProductOption> options = new ArrayList<>(Arrays.asList(ProductOption.BASIC, ProductOption.URL, ProductOption.GALLERY));

    private final Logger LOG = Logger.getLogger(DefaultProductComparisonFacade.class);
    private final int MAX_QUEUE_SIZE;

    /**
     * Instantiates a new Default product comparison facade.
     *
     * @param productComparisonPropertyService the product comparison property service
     * @param sessionService                   the session service
     * @param configurationService             the configuration service
     * @param productService                   the product service
     * @param productFacade                    the product facade
     */
    public DefaultProductComparisonFacade(ProductComparisonPropertyService<ProductModel, Object> productComparisonPropertyService, SessionService sessionService, ConfigurationService configurationService, ProductService productService, ProductFacade productFacade) {
        this.productComparisonPropertyService = productComparisonPropertyService;
        this.sessionService = sessionService;
        this.configurationService = configurationService;
        this.productService = productService;
        this.productFacade = productFacade;
        MAX_QUEUE_SIZE = getConfigurationService().getConfiguration().getInt("product.comparison.max.queue.size", 3);
    }

    /**
     * Process product comparison map.
     *
     * @return the map
     */
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

    /**
     * Gets all marked elements.
     *
     * @param productComparison the product comparison
     * @return the all marked elements
     */
    @Override
    public Map<String, Boolean> getAllMarkedElements(Map<String, LinkedList<Object>> productComparison) {
        Map<String, Boolean> markElements = new HashMap<>();
        for (Map.Entry<String, LinkedList<Object>> entry : productComparison.entrySet()) {
            markElements.put(entry.getKey(), !verifyAllEqualUsingStream(entry.getValue()));
        }
        return markElements;
    }

    /**
     * Verify all equal using stream boolean.
     *
     * @param list the list
     * @return the boolean
     */
    public boolean verifyAllEqualUsingStream(List<Object> list) {
        return list.stream()
                .distinct()
                .count() <= 1;
    }

    /**
     * Gets product comparison codes.
     *
     * @return the product comparison codes
     */
    @Override
    public List<String> getProductComparisonCodes() {
        final List productComparisonCodes = Arrays.asList(retrieveQueueFromSession());
        if (CollectionUtils.isNotEmpty(productComparisonCodes)) {
            return (List<String>) productComparisonCodes.get(0);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Add product to product comparison queue boolean.
     *
     * @param code the code
     * @return the boolean
     */
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

    /**
     * Remove product comparison queue string.
     *
     * @return the string
     */
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

    /**
     * Remove product from product comparison queue boolean.
     *
     * @param productCode the product code
     * @return the boolean
     */
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

    /**
     * Clear product comparison queue.
     */
    @Override
    public void clearProductComparisonQueue() {
        final Queue queue = retrieveQueueFromSession();
        queue.clear();
        updateQueueToSession(queue);
    }

    /**
     * Gets all comparison elements.
     *
     * @return the all comparison elements
     */
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

    /**
     * Gets product comparison property service.
     *
     * @return the product comparison property service
     */
    public ProductComparisonPropertyService<ProductModel, Object> getProductComparisonPropertyService() {
        return productComparisonPropertyService;
    }

    /**
     * Gets session service.
     *
     * @return the session service
     */
    public SessionService getSessionService() {
        return sessionService;
    }

    /**
     * Gets configuration service.
     *
     * @return the configuration service
     */
    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    /**
     * Gets product service.
     *
     * @return the product service
     */
    public ProductService getProductService() {
        return productService;
    }

    /**
     * Gets product facade.
     *
     * @return the product facade
     */
    public ProductFacade getProductFacade() {
        return productFacade;
    }
}
