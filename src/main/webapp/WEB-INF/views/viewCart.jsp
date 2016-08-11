<div class="table-outer productMainDiv">
	<a href="${contextPath}/allProducts"
		class="btn btn-lg btn-info">Continue Shopping <span
		class="glyphicon glyphicon-new-window"></span></a> <br> <br>

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

							<a href="#" class="btn btn-md btn-danger"><span
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
<a class="col-xs-offset-7 btn btn-lg btn-success">Checkout &nbsp;&nbsp;<span class="fa fa-inr"></span> 	${grandTotal}</a>
</div>