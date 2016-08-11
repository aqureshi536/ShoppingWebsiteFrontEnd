<div class="table-outer">
		<table class="table table-hover table-reponsive" class="table-cart">
			<thead>
<%
				int i = 1;
				int j = 0;
			%>
				<tr class="table-primary">

					<c:forEach items="${listOfCartItems}" var="cartItem">
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

				<c:forEach items="${listOfCartItems}" var="cartItem">


					<tr>
						<td>
							<%
								out.println(i);
							%>
						</td>
						<td><img src="${images}/product/${cartItem.productId}.png" class="cart-Image"></td>
						<td></td>							
						<td>${cartItem.quantity}</td>
						<td>${cartItem.totalPrice}</td>
						<td>
							<div class="btn-group-vertical">
								
									<a href="#"
									class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove-sign"></span> 
									Delete
									Product</a>
							</div>
						</td>
					</tr>
					<%
						i++;
					%>
				</c:forEach>
			</tbody>
		</table>
	</div>