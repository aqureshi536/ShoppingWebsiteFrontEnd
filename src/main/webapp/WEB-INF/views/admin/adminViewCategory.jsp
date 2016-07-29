<table class="table table-hover" id="viewTable">
		<thead>
		<%int i=1;
		int j=0;%>
			<tr class="table-primary">
			<c:forEach items="curtains-001,curtains-002,curtains-003" var="product">
		<%j++; %>
		</c:forEach>
			<th>Sr.No <span>( <%out.println(j);%> )</span> 	</th>
				<th>Product Image</th>
				<th>Product Name</th>
				<th>Product Description</th>
				<th>Product Category</th>
				<th>Product Supplier</th>
				<th>Product Quantity</th>
				<th>Product Price</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="curtains-001,curtains-002,curtains-003" var="product">
		
			<tr>
			
			<td><%out.println(i);%></td>
			
				<td><img src="${images}/curtains/${product}.jpg" id="tableImage"></td>
				<td>Category Name</td>
				<td>Category Description</td>
				<td>Category Category</td>
				<td>Category Supplier</td>
				<td>Category Quantity</td>
				<td>Category Price</td>
				<td>
					<div class="btn-group-vertical">
					<button class="btn btn-sm	 btn-warning">Update Category</button>
					<button class="btn btn-sm btn-danger">Delete Category</button>
					</div>
				</td>
			</tr>
			<%
				i++;
			%>
		</c:forEach>
		</tbody>
	</table>