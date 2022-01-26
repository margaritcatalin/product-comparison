package com.comparison.controllers.cms;

import com.comparison.facade.ProductComparisonFacade;
import com.comparison.model.cms.ProductComparisonInfoComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Catalin Margarit
 */
@Controller("ProductComparisonInfoComponentController")
@RequestMapping("/view/" + ProductComparisonInfoComponentModel._TYPECODE + "Controller")
public class ProductComparisonInfoComponentController extends AbstractCMSAddOnComponentController<ProductComparisonInfoComponentModel> {

    @Resource(name = "productComparisonFacade")
    private ProductComparisonFacade productComparisonFacade;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;
    final List<ProductOption> options = new ArrayList<>(Arrays.asList(ProductOption.BASIC, ProductOption.URL, ProductOption.GALLERY));

    @Override
    protected void fillModel(HttpServletRequest request, Model model, ProductComparisonInfoComponentModel component) {
        final List<String> codes = productComparisonFacade.getProductComparisonCodes();
        List<ProductData> products = new ArrayList<>();
        codes.forEach(code -> {
            ProductData productData = productFacade.getProductForCodeAndOptions(code, options);
            if (Objects.nonNull(productData)) {
                products.add(productData);
            }
        });
        model.addAttribute("selectedComparison", products);
    }
}
