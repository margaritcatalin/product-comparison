package com.comparison.service.providers;

import com.comparison.service.ProductComparisonFieldValueProvider;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Objects;

/**
 * @author Catalin Margarit
 */
public class DefaultUnitComparisonFieldValueProvider<T extends ProductModel, D> implements ProductComparisonFieldValueProvider<T, D> {

    @Override
    public D getAttributeValue(T item) {
        if (Objects.nonNull(item) && Objects.nonNull(item.getUnit())) {
            return (D) item.getUnit().getCode();
        }
        return null;
    }

    @Override
    public D getAttributeValue(T item, String attributeIdentifier) {
       //DO NOTHING
        return null;
    }
}
