package com.comparison.service;

import com.comparison.model.config.ProductComparisonPropertyModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

/**
 * @author Catalin Margarit
 */
public interface ProductComparisonPropertyService<T extends ProductModel, D> {

    List<ProductComparisonPropertyModel> getAllProductComparisonProperties();

    D getAttributeValue(T item, ProductComparisonPropertyModel comparisonProperty);

    String getAttributeLabel(T model, ProductComparisonPropertyModel comparisonProperty);
}
