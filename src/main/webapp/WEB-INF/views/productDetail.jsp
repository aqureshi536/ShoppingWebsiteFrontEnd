<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="productMainDiv">
<a onclick= window.history.back() class="btn btn-lg btn-primary">Back</a>
<br><br>
	<c:if test="${not empty addToCartSuccessMessage}">
		<div id="addToCartSuccessMessage" class="alert alert-success">
			<strong>${addToCartSuccessMessage}</strong>
		</div>
	</c:if>
	<c:if test="${not empty cancelledAddToCart}">
		<div id="addToCartSuccessMessage" class="alert alert-danger">
			<strong>${cancelledAddToCart}</strong>
		</div>
	</c:if>
	
	<div class="row">
		<div class="col-sm-5" id="productDetail">
			<img src="${contextPath}/images/products/${product.productId}.png"
				class="img-responsive  img-rounded">
		</div>

		<div class="col-sm-5">
			<h1>${product.productName }</h1>
			<h2>
				<span class="fa fa-inr"></span> ${product.price }
			</h2>
			<h3>Product By ${supplierName}</h3>
			<h4>
				Description:
				<p>${product.description }</p>
			</h4><p style="font-size:15px;color:green;font-weight:bold;">Status : <br>
			 ${product.quantity} Available
			</p>
			<br>
			<sec:authorize access="!hasRole('ROLE_ADMIN')">
				<a href="${contextPath}/user/cart/addToCart/${product.productId}"
					class="btn btn-lg btn-warning">Add to Cart</a>
				<%-- <a class="btn btn-lg btn-success">Buy</a> --%>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a
					href="${contextPath}/admin/viewProducts/updateProduct/${product.productId}"
					class="btn btn-lg btn-warning">Edit Product</a>
			</sec:authorize>
		</div>
	</div>
</div>
<div class="productMainDiv">
	<h2 id="productMainTitle">View Similar Products</h2>

	<c:choose>
		<c:when test="${not empty noSimilarProducts }">
			<p style="text-align: center; font-size: 25px;">${noSimilarProducts}</p>
		</c:when>
		<c:otherwise>
			<div class="row">
				<c:forEach var="product" items="${similarProducts}">

					<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
						onclick="myhref('${contextPath}/productDetail/${product.productId}')">
						<img src="${contextPath}/images/products/${product.productId}.png"
							class="img-rounded img-thumbnail" alt="${product.productName }"
							title="${product.productName }"
							style="height: 120px; width: 170px;" />
						<p class="pricePara">
							<b><span class="fa fa-inr"></span> ${product.price}</b>
						</p>
						<sec:authorize access="!hasRole('ROLE_ADMIN')">
							<div class="btn-group-vertical" id="actionButtons">
								<a
									href="${contextPath}/user/cart/addToCart/${product.productId}"
									class="btn btn-warning btn-md">Add to Cart</a>

							</div>
						</sec:authorize>

						<h4>${product.productName}</h4>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a
								href="${contextPath}/admin/viewProducts/updateProduct/${product.productId}"
								class="btn btn-warning btn-md edit-button">Edit</a>
						</sec:authorize>
						<h5>${product.description}</h5>
					</div>

				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
</div>
