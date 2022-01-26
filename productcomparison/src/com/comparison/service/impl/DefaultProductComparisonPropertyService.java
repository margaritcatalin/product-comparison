package com.comparison.service.impl;

import com.comparison.dao.ProductComparisonPropertyDao;
import com.comparison.model.config.ProductComparisonPropertyModel;
import com.comparison.service.ProductComparisonFieldLabelProvider;
import com.comparison.service.ProductComparisonFieldValueProvider;
import com.comparison.service.ProductComparisonPropertyService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * The type Default product comparison property service.
 *
 * @param <T> the type parameter
 * @param <D> the type parameter
 * @author Catalin Margarit
 */
public class DefaultProductComparisonPropertyService<T extends ProductModel, D> implements ProductComparisonPropertyService<T, D> {
    private final ProductComparisonPropertyDao productComparisonPropertyDao;
    private final ModelService modelService;
    private final TypeService typeService;

    /**
     * Instantiates a new Default product comparison property service.
     *
     * @param productComparisonPropertyDao the product comparison property dao
     * @param modelService                 the model service
     * @param typeService                  the type service
     */
    public DefaultProductComparisonPropertyService(ProductComparisonPropertyDao productComparisonPropertyDao, ModelService modelService, TypeService typeService) {
        this.productComparisonPropertyDao = productComparisonPropertyDao;
        this.modelService = modelService;
        this.typeService = typeService;
    }

    /**
     * Gets all product comparison properties.
     *
     * @return the all product comparison properties
     */
    @Override
    public List<ProductComparisonPropertyModel> getAllProductComparisonProperties() {
        return getProductComparisonPropertyDao().find();
    }

    /**
     * Gets attribute value.
     *
     * @param model              the model
     * @param comparisonProperty the comparison property
     * @return the attribute value
     */
    @Override
    public D getAttributeValue(T model, ProductComparisonPropertyModel comparisonProperty) {
        if (Objects.isNull(comparisonProperty.getFieldValueProvider()) || StringUtils.isBlank(comparisonProperty.getFieldValueProvider())) {
            String attributeName = comparisonProperty.getName();
            if (StringUtils.isNotEmpty(attributeName)) {
                ComposedTypeModel composedType = this.typeService.getComposedTypeForClass(model.getClass());
                if (getTypeService().hasAttribute(composedType, attributeName)) {
                    return getModelService().getAttributeValue(model, attributeName);
                }
            }
        } else {
            ProductComparisonFieldValueProvider<T, D> fieldValueProvider = this.getFieldValueProvider(comparisonProperty);
            if (Objects.nonNull(fieldValueProvider)) {
                D fieldValue = fieldValueProvider.getAttributeValue(model);
                if (Objects.nonNull(fieldValue)) {
                    return fieldValue;
                } else {
                    return fieldValueProvider.getAttributeValue(model, comparisonProperty.getName());
                }
            }
        }
        return null;
    }

    /**
     * Gets attribute label.
     *
     * @param model              the model
     * @param comparisonProperty the comparison property
     * @return the attribute label
     */
    @Override
    public String getAttributeLabel(T model, ProductComparisonPropertyModel comparisonProperty) {
        if (Objects.isNull(comparisonProperty.getFieldNameProvider()) || StringUtils.isBlank(comparisonProperty.getFieldNameProvider())) {
            String attributeName = comparisonProperty.getName();
            if (StringUtils.isNotEmpty(attributeName)) {
                ComposedTypeModel composedType = this.typeService.getComposedTypeForClass(model.getClass());
                if (getTypeService().hasAttribute(composedType, attributeName)) {
                    String objectType = getModelService().getModelType(model);
                    String attributeKey = "type." + objectType + "." + attributeName + ".name";
                    String label = de.hybris.platform.util.localization.Localization.getLocalizedString(attributeKey);
                    if (label.contains(objectType)) {
                        String fallbackAttributeKey = "type.Product." + attributeName + ".name";
                        return de.hybris.platform.util.localization.Localization.getLocalizedString(fallbackAttributeKey);
                    } else {
                        return label;
                    }
                }
            }
        } else {
            ProductComparisonFieldLabelProvider<T> fieldValueProvider = this.getFieldLabelProvider(comparisonProperty);
            if (Objects.nonNull(fieldValueProvider)) {
                String fieldName = fieldValueProvider.getAttributeLabel(model);
                if (Objects.nonNull(fieldName)) {
                    return fieldName;
                } else {
                    return fieldValueProvider.getAttributeLabel(model, comparisonProperty.getName());
                }
            }
        }
        return null;
    }

    /**
     * Gets field value provider.
     *
     * @param property the property
     * @return the field value provider
     */
    protected ProductComparisonFieldValueProvider<T, D> getFieldValueProvider(final ProductComparisonPropertyModel property) {
        final String name = property.getFieldValueProvider();
        return Objects.nonNull(name) ? Registry.getApplicationContext().getBean(name, ProductComparisonFieldValueProvider.class) : null;
    }

    /**
     * Gets field label provider.
     *
     * @param property the property
     * @return the field label provider
     */
    protected ProductComparisonFieldLabelProvider<T> getFieldLabelProvider(final ProductComparisonPropertyModel property) {
        final String name = property.getFieldNameProvider();
        return Objects.nonNull(name) ? Registry.getApplicationContext().getBean(name, ProductComparisonFieldLabelProvider.class) : null;
    }

    /**
     * Gets product comparison property dao.
     *
     * @return the product comparison property dao
     */
    public ProductComparisonPropertyDao getProductComparisonPropertyDao() {
        return productComparisonPropertyDao;
    }

    /**
     * Gets type service.
     *
     * @return the type service
     */
    public TypeService getTypeService() {
        return typeService;
    }

    /**
     * Gets model service.
     *
     * @return the model service
     */
    public ModelService getModelService() {
        return modelService;
    }

}
