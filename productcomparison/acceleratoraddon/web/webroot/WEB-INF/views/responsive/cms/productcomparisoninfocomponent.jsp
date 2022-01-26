<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="comparison-bar" class="product-comparison-bar ${selectedComparison.size()<=0?' hidden':''}" >
    <p class="product-comparison-record"><spring:theme code="text.product.comparison.info.text"/><br>
        <a href="#" id="clear-all-compare" class="js-clear-compare-items"><spring:theme code="text.product.comparison.info.clear"/></a></p>
    <ul id="compare-elements" class="compare-list">
        <c:forEach items="${selectedComparison}" var="comparisonItem">
            <li title="${comparisonItem.name}">${comparisonItem.code}<a href="#" class="deselect js-remove-compare-item" data-product="${comparisonItem.code}" data-slug="cs-45703-e">x</a></li>
        </c:forEach>
    </ul>
    <spring:url value="/product-comparison" var="compareProductsButton" htmlEscape="false"/>
    <a href="${compareProductsButton}" class="btn btn-primary ${selectedComparison.size()<=1?' disabled':''}" id="compare-submit"><spring:theme code="text.product.comparison.info.button"/></a>
</div>

<script id="compare-element-li" type="text/x-jquery-tmpl">
    <li title="{{= name}}">{{= code}}<a href="#" class="deselect js-remove-compare-item" data-product="{{= code}}" data-slug="cs-45703-e">x</a></li>
</script>