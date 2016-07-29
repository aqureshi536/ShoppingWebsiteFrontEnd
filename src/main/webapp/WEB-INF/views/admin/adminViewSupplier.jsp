<table class="table table-hover table-responsive" id="viewTable">
	<thead>
	<%
			int i = 1;
	int j=0;
		%>
		
		<tr class="table-primary">
		<c:forEach items="curtains-001,curtains-002,curtains-003" var="product">
		<%j++; %>
		</c:forEach>
			<th>Sr.No <span>( <%out.println(j);%> )</span> 	</th>
			
			
			<th>Supplier Image</th>
			<th>Supplier Name</th>
			
			
			<th>Product Supplier</th>
			<th>Product Quantity</th>
			<th>Product Price</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		
		<c:forEach items="curtains-001,curtains-002,curtains-003" var="product">
			<tr>
				<td>
					<%out.println(i);%>
				</td>
				<td><img src="${images}/curtains/${product}.jpg"
					id="tableImage"></td>
				<td>Supplier Name</td>
				
				
				<td>Supplier Supplier</td>
				<td>Supplier Quantity</td>
				<td>Supplier Price</td>
				<td>
					<div class="btn-group-vertical">
						<button class="btn btn-sm btn-warning">Update Supplier</button>
						<button class="btn btn-sm btn-danger"> Delete Supplier </button>
					</div>
				</td>
			</tr>
			<%
				i++;
			%>
		</c:forEach>
	</tbody>
</table>