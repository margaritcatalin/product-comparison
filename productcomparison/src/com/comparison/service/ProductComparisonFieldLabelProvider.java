package com.comparison.service;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author Catalin Margarit
 */
public interface ProductComparisonFieldLabelProvider<T extends ProductModel> {
    String getAttributeLabel(T item);

    String getAttributeLabel(T item, String attributeIdentifier);

}
