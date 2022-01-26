# Product Comparison Installation Guide

This repository contains the sources for a Product Comparison solution. Please report Issues here. 

## Instalation steps:
 1. Run 
 ```
 ant addoninstall -Daddonnames="productcomparison" -DaddonStorefront.yacceleratorstorefront="teststorefront"
```
2. Import next impex file:
```
# -----------------------------------------------------------------------
# Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
# -----------------------------------------------------------------------
$contentCatalog = powertoolsContentCatalog
$contentCatalogName = Powertools Content Catalog
$contentCV = catalogVersion(CatalogVersion.catalog(Catalog.id[default=$contentCatalog]), CatalogVersion.version[default=Staged])[default=$contentCatalog:Staged]
$productCatalog = powertoolsProductCatalog
$productCatalogName = Powertools Product Catalog
$productCV = catalogVersion(catalog(id[default=$productCatalog]), version[default='Staged'])[unique=true, default=$productCatalog:Staged]
$contentPage = contentPage(uid, $contentCV);
$product = product(code, $productCV)
$category = category(code, $productCV)
$addonExtensionName = b2bacceleratoraddon
$medias = medias(code, $contentCV);
$mediaContainer = media(qualifier, $contentCV);
$siteUid = powertools
###### Product Comparison Page Start ######
INSERT_UPDATE ProductComparisonComponent; $contentCV[unique = true]; uid[unique = true]         ; name                         ; &componentRef              ;
                                        ;                          ; ProductComparisonComponent ; Product Comparison Component ; ProductComparisonComponent ;

INSERT_UPDATE ContentPage; $contentCV[unique = true]; uid[unique = true]; name               ; masterTemplate(uid, $contentCV); label; defaultPage[default = 'true']; approvalStatus(code)[default = 'approved']; homepage[default = 'false']; previewImage(code, $contentCV)[default = 'ContentPageModel__function_preview']
                         ;                          ; productComparison ; Product Comparison ; AccountPageTemplate            ; productComparison

INSERT_UPDATE ContentSlotForPage; $contentCV[unique = true]; uid[unique = true]            ; position[unique = true]; page(uid, $contentCV)[unique = true][default = 'productComparison']; contentSlot(uid, $contentCV)[unique = true]; ; ;
                                ;                          ; SideContent-productComparison ; SideContent            ;                                                                    ; SideContent-productComparison              ; ; ;
                                ;                          ; BodyContent-productComparison ; BodyContent            ;                                                                    ; BodyContent-productComparison              ; ; ;

INSERT_UPDATE ContentSlot; $contentCV[unique = true]; uid[unique = true]            ; name                                     ; active; cmsComponents(&componentRef); ; ;
                         ;                          ; SideContent-productComparison ; Side Content Slot for product comparison ; true  ;                             ; ; ;
                         ;                          ; BodyContent-productComparison ; Body Content Slot for product comparison ; true  ; ProductComparisonComponent  ; ; ;

INSERT_UPDATE AddToCompareAction; $contentCV[unique = true]; uid[unique = true] ; url                     ; name                  ; &actionRef
                                ;                          ; AddToCompareAction ; /product-comparison/add ; Add To Compare Action ; AddToCompareAction

INSERT_UPDATE ListAddToCompareAction; $contentCV[unique = true]; uid[unique = true]     ; url                     ; name                       ; &actionRef
                                    ;                          ; ListAddToCompareAction ; /product-comparison/add ; List Add To Compare Action ; ListAddToCompareAction

INSERT_UPDATE ApplicableCmsActionsTypeForCmsComponent; target(code)[unique = true]; source(code)[unique = true];
                                                     ; ProductListComponent       ; ListAddToCompareAction     ;
                                                     ; ProductGridComponent       ; ListAddToCompareAction     ;
                                                     ; ProductAddToCartComponent  ; ListAddToCompareAction     ;
                                                     ; JspIncludeComponent        ; ListAddToCompareAction     ;

INSERT_UPDATE CMSProductListComponent; $contentCV[unique = true]; uid[unique = true]   ; actions(uid, $contentCV);
                                     ;                          ; ProductListComponent ; ListAddToCompareAction  ;

INSERT_UPDATE ProductGridComponent; $contentCV[unique = true]; uid[unique = true]   ; actions(uid, $contentCV);
                                  ;                          ; ProductGridComponent ; ListAddToCompareAction  ;

INSERT_UPDATE SearchResultsListComponent; $contentCV[unique = true]; uid[unique = true]; actions(uid, $contentCV);
                                        ;                          ; SearchResultsList ; ListAddToCompareAction  ;

INSERT_UPDATE SearchResultsGridComponent; $contentCV[unique = true]; uid[unique = true]; actions(uid, $contentCV);
                                        ;                          ; SearchResultsGrid ; ListAddToCompareAction  ;

INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'ProductDetailsPageTemplate']; validComponentTypes(code); compTypeGroup(code)
                             ; AddToCompareSlot   ;                                                                                 ;                          ; wide
INSERT_UPDATE ContentSlot; $contentCV[unique = true]; uid[unique = true]; name                    ; active
                         ;                          ; AddToCompareSlot  ; Add To CompareSlot Slot ; true

INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]              ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'ProductDetailsPageTemplate']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; AddToCompareSlot-ProductDetails ; AddToCompareSlot       ;                                                                                     ; AddToCompareSlot                           ; true

INSERT_UPDATE ContentSlot; $contentCV[unique = true]; uid[unique = true]; cmsComponents(uid, $contentCV)
                         ;                          ; AddToCompareSlot  ; AddToCompareAction

###### Product Comparison Page End ######

###### Product Comparison Info Start ######
INSERT_UPDATE ProductComparisonInfoComponent; uid[unique = true]             ; name                                  ; $contentCV[unique = true]
                                            ; ProductComparisonInfoComponent ; Product Comparison Info CMS Component ;

INSERT_UPDATE ContentSlot; $contentCV[unique = true]; uid[unique = true] ; cmsComponents(uid, $contentCV)
                         ;                          ; ProductCompareSlot ; ProductComparisonInfoComponent

INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'LandingPage2Template']; validComponentTypes(code)      ; compTypeGroup(code)
                             ; ProductCompare     ;                                                                           ; ProductComparisonInfoComponent ;

INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]              ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'LandingPage2Template']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; ProductCompareSlot-LandingPage2 ; ProductCompare         ;                                                                               ; ProductCompareSlot                         ; true

INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'ProductGridPageTemplate']; validComponentTypes(code)      ; compTypeGroup(code)
                             ; ProductCompare     ;                                                                              ; ProductComparisonInfoComponent ;


INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]                 ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'ProductGridPageTemplate']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; ProductCompareSlot-ProductGridPage ; ProductCompare         ;                                                                                  ; ProductCompareSlot                         ; true

INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'ProductListPageTemplate']; validComponentTypes(code)      ; compTypeGroup(code)
                             ; ProductCompare     ;                                                                              ; ProductComparisonInfoComponent ;


INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]                         ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'ProductListPageTemplate']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; ProductCompareSlot-ProductListPageTemplate ; ProductCompare         ;                                                                                  ; ProductCompareSlot                         ; true



INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'SearchResultsGridPageTemplate']; validComponentTypes(code)      ; compTypeGroup(code)
                             ; ProductCompare     ;                                                                                    ; ProductComparisonInfoComponent ;


INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]                   ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'SearchResultsGridPageTemplate']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; ProductCompareSlot-SearchResultsGrid ; ProductCompare         ;                                                                                        ; ProductCompareSlot                         ; true

INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'SearchResultsListPageTemplate']; validComponentTypes(code)      ; compTypeGroup(code)
                             ; ProductCompare     ;                                                                                    ; ProductComparisonInfoComponent ;


INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]                               ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'SearchResultsListPageTemplate']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; ProductCompareSlot-SearchResultsListPageTemplate ; ProductCompare         ;                                                                                        ; ProductCompareSlot                         ; true


INSERT_UPDATE ContentSlotName; name[unique = true]; template(uid, $contentCV)[unique = true][default = 'ProductDetailsPageTemplate']; validComponentTypes(code)      ; compTypeGroup(code)
                             ; ProductCompare     ;                                                                                 ; ProductComparisonInfoComponent ;


INSERT_UPDATE ContentSlotForTemplate; $contentCV[unique = true]; uid[unique = true]                ; position[unique = true]; pageTemplate(uid, $contentCV)[unique = true][default = 'ProductDetailsPageTemplate']; contentSlot(uid, $contentCV)[unique = true]; allowOverwrite
                                    ;                          ; ProductCompareSlot-ProductDetails ; ProductCompare         ;                                                                                     ; ProductCompareSlot                         ; true

###### Product Comparison Info End ######

```
## Structure

3. Run a Catalog Sync.


## How to add another attributes to product comparison page.

To add a new attribute to the product comparison page you need to write an impex file with the next configuration:
- name : This attribute represent the attribute unique descriptor ( ex: code, name, unit, s.a.m.d).
- fieldValueProvider : This attribute is used to take the value for the attribute. This can be done just implementing the ```ProductComparisonFieldValueProvider``` interface (Example: ```DefaultVariantCategoryFieldValueProvider```).  For the any text attribute it can be blank.
- fieldNameProvider : This attribute is used to change the displayed attribute name. This can be done just implementing the ```ProductComparisonFieldLabelProvider``` interface (Example: ```DefaultVariantCategoryFieldLabelProvider```). If this attribute is black it will use the backoffice label.

Example:
```
INSERT_UPDATE ProductComparisonProperty; name  ; fieldValueProvider                ; fieldNameProvider                ;
                                       ; name  ;                                   ;                                  ;
                                       ; unit  ; unitComparisonFieldValueProvider  ;                                  ;
                                       ; color ; colorComparisonFieldValueProvider ; colorComparisonFieldNameProvider ;
```

## Images
### Product Listing Page
[![ProductComparison](https://github.com/margaritcatalin/product-comparison/blob/main/images/product-comparison-plp.png?raw=true)](#)

### Product Details Page (The bottom popup will be displayed in Product Listing Page and Product Details Page)
[![ProductComparison](https://github.com/margaritcatalin/product-comparison/blob/main/images/product-comparison-pdp.png?raw=true)](#)

### Product comparison table
[![ProductComparison](https://github.com/margaritcatalin/product-comparison/blob/main/images/product-comparison-page.png?raw=true)](#)

### Product comparison table (Highlighted differences)
[![ProductComparison](https://github.com/margaritcatalin/product-comparison/blob/main/images/product-comparison-page-highlight.png?raw=true)](#)