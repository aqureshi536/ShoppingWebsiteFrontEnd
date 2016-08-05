<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<div id="productMainDiv">
	<div class="row">
		<div class="col-sm-5" id="productDetail">
			<img src="${images }/product/${product.productId}.png"
				class="img-responsive  img-rounded"
				>
		</div>

		<div class="col-sm-5">
			<h1>${product.productName }</h1>
			<h2>
				<span class="fa fa-inr"></span> ${product.price }
			</h2>
			<h3>Product By ${supplierName}</h3>
			<h4>Description:
			<p>${product.description }</p></h4>	
			<br> 
			<sec:authorize access="hasRole('ROLE_USER')">
				<a class="btn btn-lg btn-warning">Add to Cart</a>
				<a class="btn btn-lg btn-success">Buy</a>
			</sec:authorize>		
		</div>
	</div>
</div>
<div id="productMainDiv">
<h2 id="productMainTitle">View Similar Products</h2>
</div>
