package com.comparison.service;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author Catalin Margarit
 */
public interface ProductComparisonFieldValueProvider<T extends ProductModel, D> {
    D getAttributeValue(T item);

    D getAttributeValue(T item, String attributeIdentifier);
}
