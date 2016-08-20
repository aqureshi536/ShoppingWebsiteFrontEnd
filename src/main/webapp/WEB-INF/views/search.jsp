<div class="productMainDiv">
	<c:choose>
		<c:when test="${not empty noResultsFound}">
			<h2>${noResultsFound}</h2>
		</c:when>
		<c:otherwise>

			<div class="row">
				<c:forEach items="${listOfsearchedProducts}" var="product">

					<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
						onclick="myhref('${contextPath}/productDetail/${product.productId}')">
						<img src="${images}/product/${product.productId}.png"
							class="img-rounded img-thumbnail" alt="${product.productName }"
							title="${product.productName }"
							style="height: 120px; width: 170px;" />
						<p class="pricePara">
							<b><span class="fa fa-inr"></span> ${product.price}</b>
						</p>
						<sec:authorize access="!hasRole('ROLE_ADMIN')">
							<div class="btn-group-vertical" id="actionButtons">
								<a class="btn btn-warning btn-md">Add to Cart</a>

							</div>
						</sec:authorize>
						<h4>${product.productName}</h4>
						<h5>${product.description}</h5>

					</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
</div>