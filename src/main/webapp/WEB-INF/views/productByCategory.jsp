<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="productMainDiv">
<sec:authorize access="!hasRole('ROLE_ADMIN')">
<a onclick= window.history.back() class="btn btn-lg btn-primary">Back</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="${contextPath}/admin/viewCategory" class="btn btn-lg btn-primary">Back</a>
</sec:authorize>
	<c:if test="${not empty productNotPresent}">
		<h2>${productNotPresent}</h2>
	</c:if>
	<div class="row">
		<c:forEach items="${productList}" var="product">

			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
				onclick="myhref('${contextPath}/productDetail/${product.productId}')">
				<img src="${images}/product/${product.productId}.png"
					class="img-rounded img-thumbnail" alt="${product.productName }"
					title="${product.productName }"
					style="height: 120px; width: 170px;" />
				<p class="pricePara">
					<b><span class="fa fa-inr"></span> ${product.price}</b>
				</p>

				<div class="btn-group-vertical" id="actionButtons">
					<sec:authorize access="hasRole('ROLE_USER')">
						<a href="${contextPath}/user/cart/addToCart/${product.productId}"
							class="btn btn-warning btn-md">Add to Cart</a>
					</sec:authorize>

				</div>

				<h4>${product.productName}</h4>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${contextPath}/admin/viewProducts/updateProduct/${product.productId}" class="btn btn-warning btn-md edit-button">Edit</a>
				</sec:authorize>
				<h5>${product.description}</h5>

			</div>
		</c:forEach>
	</div>
</div>