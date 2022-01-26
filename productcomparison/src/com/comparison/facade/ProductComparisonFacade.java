package com.comparison.facade;

import com.comparison.data.ComparisonItemData;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The interface Product comparison facade.
 *
 * @author Catalin Margarit
 */
public interface ProductComparisonFacade {
    /**
     * Gets product comparison codes.
     *
     * @return the product comparison codes
     */
    List<String> getProductComparisonCodes();

    /**
     * Process product comparison map.
     *
     * @return the map
     */
    Map<String, LinkedList<Object>> processProductComparison();

    /**
     * Add product to product comparison queue boolean.
     *
     * @param code the code
     * @return the boolean
     */
    boolean addProductToProductComparisonQueue(final String code);

    /**
     * Remove product comparison queue string.
     *
     * @return the string
     */
    String removeProductComparisonQueue();

    /**
     * Remove product from product comparison queue boolean.
     *
     * @param productCode the product code
     * @return the boolean
     */
    boolean removeProductFromProductComparisonQueue(final String productCode);

    /**
     * Clear product comparison queue.
     */
    void clearProductComparisonQueue();

    /**
     * Gets all comparison elements.
     *
     * @return the all comparison elements
     */
    List<ComparisonItemData> getAllComparisonElements();

    /**
     * Gets all marked elements.
     *
     * @param productComparison the product comparison
     * @return the all marked elements
     */
    Map<String, Boolean> getAllMarkedElements(Map<String, LinkedList<Object>> productComparison);
}
