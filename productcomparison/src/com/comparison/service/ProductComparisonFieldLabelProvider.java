package com.comparison.service;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * The interface Product comparison field label provider.
 *
 * @param <T> the type parameter
 * @author Catalin Margarit
 */
public interface ProductComparisonFieldLabelProvider<T extends ProductModel> {
    /**
     * Gets attribute label.
     *
     * @param item the item
     * @return the attribute label
     */
    String getAttributeLabel(T item);

    /**
     * Gets attribute label.
     *
     * @param item                the item
     * @param attributeIdentifier the attribute identifier
     * @return the attribute label
     */
    String getAttributeLabel(T item, String attributeIdentifier);

}
