
<div id="divCarousel">
	<div id="productCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="productCarousel" data-slide-to="0" class="active"></li>
			<li data-target="productCarousel" data-slide-to="1"></li>
			<li data-target="productCarousel" data-slide-to="2"></li>
			<li data-target="productCarousel" data-slide-to="3"></li>
			<li data-target="productCarousel" data-slide-to="4"></li>
		</ol>

		<div class="carousel-inner" role="listbox">
			<div class="item active	">
				<img src="resources/images/bigsale1.jpg" class="img-responsive"
					alt="" style="height: 500px; width: 1300px;">
			</div>
			<div class="item">
				<img src="resources/images/curtainhome1.jpg" alt=""
					class="img-responsive" style="height: 500px; width: 1300px;">
			</div>
			<div class="item">
				<img src="resources/images/bed3.jpg" alt="" class="img-responsive"
					style="height: 500px; width: 1300px;">
			</div>
			<div class="item">
				<img src="resources/images/chair4.jpg" alt="" class="img-responsive"
					style="height: 500px; width: 1300px;">
			</div>
			<div class="item">
				<img src="resources/images/cab5.jpg" alt="" class="img-responsive"
					style="height: 500px; width: 1300px;">
			</div>
		</div>
		<a class="left carousel-control" href="#productCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#productCarousel"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<!--div carousel  -->
</div>

<div class="productMainDiv">
	<div class="row">
		<h2 id="productMainTitle">Latest Products</h2>
		<c:forEach items="${productArray}" var="product">
			<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
				onclick="myhref('${contextPath}/productDetail/${product.productId}')">
				<img src="${images}/product/${product.productId}.png"
					class="img-rounded img-thumbnail" alt=""
					style="height: 150px; width: 200px;" />
				<p id="pricePara">
					<b>${product.price }</b>
				</p>
				<h4>${product.productName }</h4>
			</div>
		</c:forEach>
	</div>
</div>
<!--div bed controller  -->
<c:if test="${empty noProducts }">
	<div class="productMainDiv">

		<h2 style="border-left: 6px #3bb300 solid" id="productMainTitle">Top
			Products</h2>
		<div id="carouselProduct" class="carousel slide carousel-product"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselProduct" data-slide-to="0" class="active"></li>
				<li data-target="#carouselProduct" data-slide-to="1"></li>
				<li data-target="#carouselProduct" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active item-box">
					<div class="row" class="">
						<c:forEach items="${productArray1}" var="product">
							<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
								onclick="myhref('${contextPath}/productDetail/${product.productId}')">
								<img src="${images}/product/${product.productId}.png"
									class="img-rounded img-thumbnail" alt=""
									style="height: 150px; width: 200px;" />
								<p id="pricePara">
									<b>${product.price }</b>
								</p>
								<h4>${product.productName }</h4>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="item item-box">
					<div class="row">
						<c:forEach items="${productArray2}" var="product">
							<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
								onclick="myhref('${contextPath}/productDetail/${product.productId}')">
								<img src="${images}/product/${product.productId}.png"
									class="img-rounded img-thumbnail" alt=""
									style="height: 150px; width: 200px;" />
								<p id="pricePara">
									<b>${product.price }</b>
								</p>
								<h4>${product.productName }</h4>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="item item-box">
					<div class="row">
						<c:forEach items="${productArray3}" var="product">
							<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 productDiv"
								onclick="myhref('${contextPath}/productDetail/${product.productId}')">
								<img src="${images}/product/${product.productId}.png"
									class="img-rounded img-thumbnail" alt=""
									style="height: 150px; width: 200px;" />
								<p id="pricePara">
									<b>${product.price }</b>
								</p>
								<h4>${product.productName }</h4>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#carouselProduct"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carouselProduct"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>

		<!--div sofa controller  -->
	</div>
</c:if>