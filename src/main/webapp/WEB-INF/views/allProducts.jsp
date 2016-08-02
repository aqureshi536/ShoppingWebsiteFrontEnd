<div id="productMainDiv">

	<div class="row">
		<c:forEach items="${products}" var="product">

			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" onclick="myhref('${contextPath}/productDetail/${product.productId}')" id="productDiv">
					<img src="${images}/product/${product.productId}.png"
						class="img-rounded img-thumbnail" alt="${product.productName }"
						title="${product.productName }"
						style="height: 120px; width: 170px;" />
					<h2 id="pricePara">
						<b><span class="fa fa-inr"></span> ${product.price}</b>
					</h2>
					<div class="btn-group-vertical" id="actionButtons">
						<a class="btn btn-warning btn-md">Add to Cart</a>
						
					</div>
					<h4>${product.productName}</h4>
					<h5>${product.description}</h5>

				</div>
		</c:forEach>
	</div>
</div>