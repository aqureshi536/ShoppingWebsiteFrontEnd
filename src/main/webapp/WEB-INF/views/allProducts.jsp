<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="productMainDiv" ng-controller="allProducts">
<c:if test="${not empty nothing}">
<p style="font-size:5em;">${nothing}</p>
</c:if>
	<c:if test="${not empty addToCartAllProducts }">
		<div class="alert alert-success">${addToCartAllProducts}</div>
	</c:if>
	<c:if test="${not empty cancelledAddToCart }">
		<div class="alert alert-danger">${cancelledAddToCart}</div>
	</c:if>
	
	<div class="row">

		<div
			ng-repeat="product in listOfProducts | filter : searchProduct">

			<%-- 	<c:forEach items="${products}" var="product"> --%>
			<%-- onclick="myhref('${contextPath}/productDetail/{{product.product.productId}}')" --%>
			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv">
				<img ng-src="${images}/product/{{product.product.productId}}.png"
					class="img-rounded img-thumbnail"
					alt="{{product.product.productName}}"
					title="{{product.product.productName }}"
					style="height: 120px; width: 170px;" />
				<p class="pricePara">
					<b><span class="fa fa-inr"></span> {{product.product.price}}</b>
				</p>
				<sec:authorize access="!hasRole('ROLE_ADMIN')">
					<div class="btn-group-vertical" id="actionButtons">
						<a
							ng-href="${contextPath}/user/allProducts/addToCart/{{product.product.productId}}"
							class="btn btn-warning btn-md">Add to Cart</a>
							 <a	href="${contextPath}/productDetail/{{product.product.productId}}"
							class="btn btn-success btn-md">View</a>
					</div>
				</sec:authorize>
				<h4>{{product.product.productName}}</h4>
				<h5>{{product.product.description}}</h5>

			</div>
			<%-- 	<h2 ng-if="filteredProducts.length == 0">Sorry ,no products found</h2>  --%>
			<%-- </c:forEach> --%>
		</div>
	</div>
</div>