<div class="table-outer">
		<table class="table table-hover" id="viewTable">
			<thead>

				<tr class="table-primary">

					<c:forEach items="${products}" var="product">
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
					<th>Product Description</th>
					
					
					<th>Product Quantity</th>
					<th>Product Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${products}" var="p">


					<tr>
						<td>
							<%
								out.println(i);
							%>
						</td>
						<td><img src="${images}/product/${}.png" class="cart-Image"></td>
						<td></td>
						<td></td>	
						<td></td>
						<td></td>
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