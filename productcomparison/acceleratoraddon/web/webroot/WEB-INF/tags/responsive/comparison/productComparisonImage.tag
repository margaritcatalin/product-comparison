<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set value="${ycommerce:productImage(product, format)}" var="primaryImage"/>

<c:set value="${fn:escapeXml(product.name)}" var="productNameHtml"/>

<c:choose>
	<c:when test="${not empty primaryImage}">
		<c:choose>
			<c:when test='${fn:startsWith(primaryImage.url, originalContextPath)}'>
				<c:url value="${primaryImage.url}" var="primaryImageUrl" context="/"/>
			</c:when>
			<c:otherwise>
				<c:url value="${primaryImage.url}" var="primaryImageUrl" context="${originalContextPath}"/>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${not empty primaryImage.altText}">
				<c:set value="${fn:escapeXml(primaryImage.altText)}" var="altTextHtml"/>
				<img src="${fn:escapeXml(primaryImageUrl)}" alt="${altTextHtml}" title="${altTextHtml}"  style="height: 200px; width: 200px;"/>
			</c:when>
			<c:otherwise>
                <img src="${fn:escapeXml(primaryImageUrl)}" alt="${productNameHtml}" title="${productNameHtml}"  style="height: 200px; width: 200px;"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<spring:htmlEscape defaultHtmlEscape="true" />
		<spring:theme code="img.missingProductImage.responsive.${format}" text="/" var="imagePath" htmlEscape="false"/>
		<c:choose>
			<c:when test="${originalContextPath ne null}">
				<c:choose>
					<c:when test='${fn:startsWith(imagePath, originalContextPath)}'>
						<c:url value="${imagePath}" var="imageUrl" context="/"/>
					</c:when>
					<c:otherwise>
						<c:url value="${imagePath}" var="imageUrl" context="${originalContextPath}"/>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:url value="${imagePath}" var="imageUrl" />
			</c:otherwise>
		</c:choose>
		<img src="${fn:escapeXml(imageUrl)}" alt="${fn:escapeXml(productNameHtml)}" title="${fn:escapeXml(productNameHtml)}" style="height: 200px; width: 200px;" />
	</c:otherwise>
</c:choose>
