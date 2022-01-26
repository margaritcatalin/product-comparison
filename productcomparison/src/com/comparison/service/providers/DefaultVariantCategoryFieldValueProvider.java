package com.comparison.service.providers;

import com.comparison.service.ProductComparisonFieldValueProvider;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantCategoryModel;
import de.hybris.platform.variants.model.VariantProductModel;
import de.hybris.platform.variants.model.VariantValueCategoryModel;

import java.util.Objects;

/**
 * @author Catalin Margarit
 */
public class DefaultVariantCategoryFieldValueProvider<T extends ProductModel, D> implements ProductComparisonFieldValueProvider<T, D> {

    @Override
    public D getAttributeValue(T item) {
        //DO NOTHING
        return null;
    }

    @Override
    public D getAttributeValue(T item, String attributeIdentifier) {
        if (Objects.nonNull(item)) {
            VariantCategoryModel variantCategoryModel = getVariantCategory(item, attributeIdentifier);
            if (Objects.nonNull(variantCategoryModel)) {
                VariantValueCategoryModel variantValueCategoryModel = getVariantValuesCategory(item, variantCategoryModel.getCode());
                if (Objects.nonNull(variantValueCategoryModel)) {
                    return (D) variantValueCategoryModel.getName();
                }
            } else if (item instanceof VariantProductModel) {
                ProductModel baseProduct = ((VariantProductModel) item).getBaseProduct();
                if (Objects.nonNull(baseProduct)) {
                    VariantCategoryModel baseVariantCategoryModel = getVariantCategory(baseProduct, attributeIdentifier);
                    if (Objects.nonNull(baseVariantCategoryModel)) {
                        VariantValueCategoryModel variantValueCategoryModel = getVariantValuesCategory(item, baseVariantCategoryModel.getCode());
                        if (Objects.nonNull(variantValueCategoryModel)) {
                            return (D) variantValueCategoryModel.getName();
                        }
                    }
                }
            }
        }
        return null;
    }

    protected VariantCategoryModel getVariantCategory(final ProductModel productModel, final String variantCategoryCode) {
        for (final CategoryModel categoryProductModel : productModel.getSupercategories()) {
            if (categoryProductModel instanceof VariantCategoryModel && categoryProductModel.getCode().equalsIgnoreCase(variantCategoryCode)) {
                return (VariantCategoryModel) categoryProductModel;
            }
        }
        return null;
    }

    protected VariantValueCategoryModel getVariantValuesCategory(final ProductModel productModel, String variantCategoryCode) {
        for (final CategoryModel categoryProductModel : productModel.getSupercategories()) {
            if (categoryProductModel instanceof VariantValueCategoryModel && categoryProductModel.getSupercategories().stream().anyMatch(superCategory -> superCategory.getCode().equalsIgnoreCase(variantCategoryCode))) {
                return (VariantValueCategoryModel) categoryProductModel;
            }
        }
        return null;
    }
}
