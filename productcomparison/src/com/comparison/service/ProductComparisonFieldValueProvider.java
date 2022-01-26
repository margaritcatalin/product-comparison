package com.comparison.service;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * The interface Product comparison field value provider.
 *
 * @param <T> the type parameter
 * @param <D> the type parameter
 * @author Catalin Margarit
 */
public interface ProductComparisonFieldValueProvider<T extends ProductModel, D> {
    /**
     * Gets attribute value.
     *
     * @param item the item
     * @return the attribute value
     */
    D getAttributeValue(T item);

    /**
     * Gets attribute value.
     *
     * @param item                the item
     * @param attributeIdentifier the attribute identifier
     * @return the attribute value
     */
    D getAttributeValue(T item, String attributeIdentifier);
}
