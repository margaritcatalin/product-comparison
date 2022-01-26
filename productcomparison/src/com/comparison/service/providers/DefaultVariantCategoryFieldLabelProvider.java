package com.comparison.service.providers;

import com.comparison.service.ProductComparisonFieldLabelProvider;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantCategoryModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Objects;

/**
 * The type Default variant category field label provider.
 *
 * @param <T> the type parameter
 * @author Catalin Margarit
 */
public class DefaultVariantCategoryFieldLabelProvider<T extends ProductModel> implements ProductComparisonFieldLabelProvider<T> {

    /**
     * Gets attribute label.
     *
     * @param item the item
     * @return the attribute label
     */
    @Override
    public String getAttributeLabel(T item) {
        //DO NOTHING
        return null;
    }

    /**
     * Gets attribute label.
     *
     * @param item                the item
     * @param attributeIdentifier the attribute identifier
     * @return the attribute label
     */
    @Override
    public String getAttributeLabel(T item, String attributeIdentifier) {
        if (Objects.nonNull(item)) {
            VariantCategoryModel variantCategoryModel = getVariantCategory(item, attributeIdentifier);
            if (Objects.nonNull(variantCategoryModel)) {
                return variantCategoryModel.getName();
            } else if (item instanceof VariantProductModel) {
                ProductModel baseProduct = ((VariantProductModel) item).getBaseProduct();
                if (Objects.nonNull(baseProduct)) {
                    VariantCategoryModel baseVariantCategoryModel = getVariantCategory(baseProduct, attributeIdentifier);
                    if (Objects.nonNull(baseVariantCategoryModel)) {
                        return baseVariantCategoryModel.getName();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Gets variant category.
     *
     * @param productModel        the product model
     * @param variantCategoryCode the variant category code
     * @return the variant category
     */
    protected VariantCategoryModel getVariantCategory(final ProductModel productModel, final String variantCategoryCode) {
        for (final CategoryModel categoryProductModel : productModel.getSupercategories()) {
            if (categoryProductModel instanceof VariantCategoryModel && categoryProductModel.getCode().equalsIgnoreCase(variantCategoryCode)) {
                return (VariantCategoryModel) categoryProductModel;
            }
        }
        return null;
    }
}
