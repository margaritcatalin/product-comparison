package com.comparison.controllers.pages;

import com.comparison.data.CompareResponseData;
import com.comparison.facade.ProductComparisonFacade;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Catalin Margarit
 */
@Controller
@RequestMapping(value = "/product-comparison")
public class ProductComparisonPageController extends AbstractAddOnPageController {

    @Resource(name = "productComparisonFacade")
    private ProductComparisonFacade productComparisonFacade;

    private static final String PRODUCT_COMPARISON_CMS_PAGE = "productComparison";
    private static final String BREADCRUMBS_ATTR = "breadcrumbs";

    @GetMapping()
    public String getProductComparisonCmsPage(final Model model) throws CMSItemNotFoundException {
        final ContentPageModel productComparisonPage = getContentPageForLabelOrId(PRODUCT_COMPARISON_CMS_PAGE);
        storeCmsPageInModel(model, productComparisonPage);
        setUpMetaDataForContentPage(model, productComparisonPage);
        final Breadcrumb productComparisonBreadcrumb = new Breadcrumb("#", getMessageSource().getMessage("text.product.comparison.breadcrumbs", null, "text.product.comparison.breadcrumbs", getI18nService().getCurrentLocale()), null);
        model.addAttribute(BREADCRUMBS_ATTR, Collections.singletonList(productComparisonBreadcrumb));
        return getViewForPage(model);
    }

    @ResponseBody
    @GetMapping(value = "/comparison-items")
    public List<String> getAllCodes() {
        return productComparisonFacade.getProductComparisonCodes();
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public CompareResponseData add(@RequestParam("code") final String productCode) {
        CompareResponseData compareResponseData = new CompareResponseData();
        boolean addItemStatus = productComparisonFacade.addProductToProductComparisonQueue(productCode);
        if (addItemStatus == true) {
            compareResponseData.setStatusCode(200);
        } else {
            compareResponseData.setStatusCode(500);
        }
        compareResponseData.setSuccess(addItemStatus);
        compareResponseData.setCompareItems(productComparisonFacade.getAllComparisonElements());
        return compareResponseData;
    }

    @ResponseBody
    @PostMapping(value = "/remove")
    public CompareResponseData remove(@RequestParam("code") final String productCode) {
        CompareResponseData compareResponseData = new CompareResponseData();
        boolean removeItemStatus = productComparisonFacade.removeProductFromProductComparisonQueue(productCode);
        if (removeItemStatus == true) {
            compareResponseData.setStatusCode(200);
        } else {
            compareResponseData.setStatusCode(500);
        }
        compareResponseData.setSuccess(removeItemStatus);
        compareResponseData.setCompareItems(productComparisonFacade.getAllComparisonElements());
        return compareResponseData;
    }

    @ResponseBody
    @PostMapping(value = "/clear")
    public CompareResponseData clear() {
        CompareResponseData compareResponseData = new CompareResponseData();
        productComparisonFacade.clearProductComparisonQueue();
        compareResponseData.setStatusCode(200);
        compareResponseData.setSuccess(true);
        compareResponseData.setCompareItems(productComparisonFacade.getAllComparisonElements());
        return compareResponseData;
    }

}
