package com.comparison.service.providers;

import com.comparison.service.ProductComparisonFieldValueProvider;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Objects;

/**
 * The type Default unit comparison field value provider.
 *
 * @param <T> the type parameter
 * @param <D> the type parameter
 * @author Catalin Margarit
 */
public class DefaultUnitComparisonFieldValueProvider<T extends ProductModel, D> implements ProductComparisonFieldValueProvider<T, D> {

    /**
     * Gets attribute value.
     *
     * @param item the item
     * @return the attribute value
     */
    @Override
    public D getAttributeValue(T item) {
        if (Objects.nonNull(item) && Objects.nonNull(item.getUnit())) {
            return (D) item.getUnit().getCode();
        }
        return null;
    }

    /**
     * Gets attribute value.
     *
     * @param item                the item
     * @param attributeIdentifier the attribute identifier
     * @return the attribute value
     */
    @Override
    public D getAttributeValue(T item, String attributeIdentifier) {
       //DO NOTHING
        return null;
    }
}
