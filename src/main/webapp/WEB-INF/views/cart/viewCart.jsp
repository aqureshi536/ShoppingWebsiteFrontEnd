<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="table-outer productMainDiv">
	<c:if test="${not empty cartItemRemoved }">
		<div id="cartItemRemoved" class="alert alert-danger">${cartItemRemoved}</div>
	</c:if>
	<a href="${contextPath}/allProducts" class="btn btn-lg btn-info">Continue
		Shopping <span class="glyphicon glyphicon-new-window"></span>
	</a> <br> <br>



	<c:choose>
		<c:when test="${not empty cartEmpty}">
			<h2 style="margin: auto;text-align:center;">No products in cart</h2>
		</c:when>
		<c:otherwise>
			<table class="table table-hover table-reponsive table-cart">
				<thead>
					<%
						int i = 1;
								int j = 0;
					%>
					<tr class="table-primary">

						<c:forEach items="${cartItems}" var="cartItem">
							<%
								j++;
							%>
						</c:forEach>
						
						<th>Sr.No <span>( <%
							out.println(j);
						%> )
						</span>
						</th>
						<th>Product Image</th>
						<th>Product Name</th>
						<th>Product Quantity</th>
						<th>Total Price</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${cartItems}" var="c">


						<tr>
							<td>
								<%
									out.println(i);
								%>
							</td>
							<td><img src="${images}/product/${c.cartItem.productId}.png"
								class="cart-Image"></td>
							<td>${c.productName}</td>
							<td>${c.cartItem.quantity}</td>
							<td>${c.cartItem.totalPrice}</td>
							<td>
								<div class="btn-group-vertical">

									<a href="${contextPath}/user/cart/remove/${c.cartItem.cartItemId}"
										class="btn btn-md btn-danger"><span
										class="glyphicon glyphicon-remove-sign"></span> Remove</a>
								</div>
							</td>
						</tr>
						<%
							i++;
						%>
					</c:forEach>
				</tbody>
			</table>


			<a class="col-xs-offset-5 btn btn-lg btn-success" href="${contextPath}/checkout">Checkout
				&nbsp;&nbsp;<span class="fa fa-inr"></span> ${grandTotal}
			</a>
		</c:otherwise>
	</c:choose>
</div>