<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="comparison" tagdir="/WEB-INF/tags/responsive/comparison" %>
<%@ taglib prefix="comparison" tagdir="/WEB-INF/tags/addons/productcomparison/responsive/comparison" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<div id="track-order-page-container" class="container-lg">
    <div class="account-section-header clearfix">
        <div class="acount-section-header-title">
            <span class="body-header-text pb-3">
                <spring:theme code="text.product.comparison.header"/>
            </span>
        </div>
    </div>
    <div class="product-comparison">
        <c:choose>
        <c:when test="${comparisonProducts.size()>1}">
        <div class="d-xs d-sm d-md-none d-lg-none  hidden-md hidden-lg visible-xs visible-sm"><a
                class="btn btn-default-variant btn-block" href="javascript:history.back()"><spring:theme
                code="text.product.comparison.return.button"/></a>
            <div>
                <span><spring:theme code="text.product.comparison.text"/></span>
                <a href="#" class="btn btn-active-highlight btn-block js-compare-hide-difference" id="hide-compare-diff"
                   data-action-type="hide"><spring:theme code="text.product.comparison.button"/></a>
            </div>
        </div>
        <table id="product-properties" class="responsive-table">
            <thead class="thead-inverse">
            <tr class="width-150">
                <td class="responsive-table-cell" colspan="1"><a class="btn btn-default-variant btn-block"
                                                                 href="javascript:history.back()"><spring:theme
                        code="text.product.comparison.return.button"/></a>
                    <div>
                        <span><spring:theme code="text.product.comparison.text"/> </span>
                        <a href="#" class="btn btn-active-highlight btn-block js-compare-hide-difference"
                           id="hide-compare-diff" data-action-type="hide"><spring:theme
                                code="text.product.comparison.button"/></a>
                    </div>
                </td>
                <c:forEach items="${comparisonProducts}" var="product">
                    <c:url value="${product.url}" var="productUrl"/>
                    <td colspan="1" class="sm-hide responsive-table-cell"
                        style=" text-align: center; vertical-align: middle;">
                        <a class="thumb" href="${fn:escapeXml(productUrl)}" title="${fn:escapeXml(product.name)}">
                            <comparison:productComparisonImage product="${product}" format="product"/>
                        </a>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td class="responsive-table-cell">&nbsp;</td>
                <c:forEach items="${comparisonProducts}" var="product">
                    <td class="responsive-table-cell" style=" text-align: center; vertical-align: middle;">
                        <h3>
                            <c:out escapeXml="false" value="${ycommerce:sanitizeHTML(product.name)}"/>
                        </h3>
                        <h4 class="table-compare-product-sku"><b><spring:theme
                                code="text.product.comparison.article.number"/></b> <span
                                class="code">${fn:escapeXml(product.code)}</span>
                        </h4>
                    </td>
                </c:forEach>
            </tr>
            <tr class="responsive-table-cell">
                <th colspan="1" class="width-250 responsive-table-cell"
                    data-label="<spring:theme code="text.product.comparison.component.column"/>"><spring:theme
                        code="text.product.comparison.component.column"/></th>
                <th colspan="${comparisonProducts.size()}"
                    data-label="<spring:theme code="text.product.comparison.value.column"/>"
                    class="responsive-table-cell"
                    style=" text-align: center; vertical-align: middle;"><spring:theme
                        code="text.product.comparison.value.column"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${comparisonData}" var="comparison">
                <tr class="responsive-table-cell ${markedElement.get(comparison.key) == true ? " difference-column table-compare-diff":""}">
                    <td class="responsive-table-cell"
                        data-label="<spring:theme code="text.product.comparison.column.product.code"/>">${comparison.key}</td>
                    <c:forEach items="${comparison.value}" var="value" varStatus="status">
                        <c:choose>
                            <c:when test="${value.getClass().name eq 'de.hybris.platform.commercefacades.product.data.PriceData'}">
                                <td class="responsive-table-cell"
                                    data-label="${comparisonProducts.get(status.index).code}">
                                    <c:choose>
                                        <c:when test="${not empty value}">
                                            <format:price priceData="${value}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <span class='mandatory-remove'>&nbsp;</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="responsive-table-cell"
                                    data-label="${comparisonProducts.get(status.index).code}">
                                        ${not empty value ? value:"<span class='mandatory-remove'>&nbsp;</span>"}
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </c:when>
    <c:otherwise>
        <span><spring:theme code="text.product.comparison.text.empty"/></span>
        <a class="btn btn-default-variant btn-block" href="javascript:history.back()"><spring:theme
                code="text.product.comparison.return.button"/></a>
    </c:otherwise>
    </c:choose>

</div>