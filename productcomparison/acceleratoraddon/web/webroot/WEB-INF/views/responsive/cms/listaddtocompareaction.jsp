<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<input type="checkbox" id="compare-button-${fn:escapeXml(product.code)}" name="compare-button-${fn:escapeXml(product.code)}" class="js-add-to-compare" data-product="${fn:escapeXml(product.code)}"/>
<label for="compare-button-${fn:escapeXml(product.code)}"><spring:theme code="text.product.comparison.checkbox.compare" text="Compare"/></label>
