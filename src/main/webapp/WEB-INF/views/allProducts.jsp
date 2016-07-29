<div id="productMainDiv">

	<div class="row">
		<c:forEach items="${products}" var="product">

			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" id="productDiv">
				<img src="${images}/${product.imageUrl}"
					class="img-rounded img-thumbnail" alt="${product.productName }" title="${product.productName }"
					style="height: 120px; width: 170px;" />
				<h2 id="pricePara">
					<b><span class="fa fa-inr"></span> ${product.price}</b>
				</h2>
				<div class="btn-group-vertical" id="actionButtons">
					<button class="btn btn-warning btn-md">Add to Cart</button>
					<button class="btn btn-success btn-md">Buy</button>
				</div>
				<h4>${product.productName}</h4>
				<h5>${product.description}</h5>

			</div>

		</c:forEach>
		<c:forEach items="beds-001,beds-002,beds-003" var="product">

			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" id="productDiv">
				<img src="${images}/beds/${product}.jpg"
					class="img-rounded img-thumbnail" alt=""
					style="height: 120px; width: 170px;" />
				<h2 id="pricePara">
					<b><span class="fa fa-inr"></span> 5233</b>
				</h2>
				<div class="btn-group-vertical" id="actionButtons">
					<button class="btn btn-warning btn-md">Add to Cart</button>
					<button class="btn btn-success btn-md">Buy</button>
				</div>
				<h4>bed name</h4>
				<h5>bed brand</h5>
			</div>

		</c:forEach>
		<c:forEach items="sofa-001,sofa-002,sofa-003" var="product">

			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" id="productDiv">
				<img src="${images}/sofas/${product}.jpg"
					class="img-rounded img-thumbnail" alt=""
					style="height: 120px; width: 170px;" />
				<h2 id="pricePara">
					<b><span class="fa fa-inr"></span> 5233</b>
				</h2>
				<div class="btn-group-vertical" id="actionButtons">
					<button class="btn btn-warning btn-md">Add to Cart</button>
					<button class="btn btn-success btn-md">Buy</button>
				</div>
				<h4>sofa name</h4>
				<h5>sofa brand</h5>
			</div>

		</c:forEach>

	</div>


</div>