<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="productMainDiv">
<c:if test="${not empty addToCartSuccessMessage}">
<div id="addToCartSuccessMessage" class="alert alert-success">
<strong>${addToCartSuccessMessage}</strong>
</div>
</c:if>
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
				<a href="${contextPath}/cart/addToCart/${product.productId}" class="btn btn-lg btn-warning">Add to Cart</a>
				<a class="btn btn-lg btn-success">Buy</a>
			</sec:authorize>		
		</div>
	</div>
</div>
<div class="productMainDiv">
<h2 id="productMainTitle">View Similar Products</h2>
</div>
