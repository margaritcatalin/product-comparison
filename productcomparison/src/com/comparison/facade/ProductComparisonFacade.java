package com.comparison.facade;

import com.comparison.data.ComparisonItemData;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Catalin Margarit
 */
public interface ProductComparisonFacade {
    List<String> getProductComparisonCodes();

    Map<String, LinkedList<Object>> processProductComparison();

    boolean addProductToProductComparisonQueue(final String code);

    String removeProductComparisonQueue();

    boolean removeProductFromProductComparisonQueue(final String productCode);

    void clearProductComparisonQueue();

    List<ComparisonItemData> getAllComparisonElements();

    Map<String, Boolean> getAllMarkedElements(Map<String, LinkedList<Object>> productComparison);
}
