package com.comparison.service;

import com.comparison.model.config.ProductComparisonPropertyModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

/**
 * The interface Product comparison property service.
 *
 * @param <T> the type parameter
 * @param <D> the type parameter
 * @author Catalin Margarit
 */
public interface ProductComparisonPropertyService<T extends ProductModel, D> {

    /**
     * Gets all product comparison properties.
     *
     * @return the all product comparison properties
     */
    List<ProductComparisonPropertyModel> getAllProductComparisonProperties();

    /**
     * Gets attribute value.
     *
     * @param item               the item
     * @param comparisonProperty the comparison property
     * @return the attribute value
     */
    D getAttributeValue(T item, ProductComparisonPropertyModel comparisonProperty);

    /**
     * Gets attribute label.
     *
     * @param model              the model
     * @param comparisonProperty the comparison property
     * @return the attribute label
     */
    String getAttributeLabel(T model, ProductComparisonPropertyModel comparisonProperty);
}
