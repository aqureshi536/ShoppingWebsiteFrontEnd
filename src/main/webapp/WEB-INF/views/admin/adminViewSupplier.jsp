<div id="productMainDiv">
<a href="" class="btn btn-lg btn-success">Add Supplier</a><br>
<br>
<table class="table table-hover table-responsive" id="viewTable">
	<thead>
	<%
			int i = 1;
	int j=0;
		%>
		
		<tr class="table-primary">
		<c:forEach items="${suppliers}" var="supplier">
		<%j++; %>
		</c:forEach>
			<th>Sr.No <span>( <%out.println(j);%> )</span> 	</th>
			
			
			<th>Supplier Image</th>
			<th>Supplier Name</th>
			
			
			<th>Supplier Contact</th>
			<th>Supplier Email</th>
			<th>Supplier Address</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		
		<c:forEach items="${suppliers}" var="supplier">
			<tr>
				<td>
					<%out.println(i);%>
				</td>
				<td><img src="${images}/supplier/${supplier.supplierImage}"
					id="tableImage"></td>
				<td>${supplier.supplierName}</td>
				<td>${supplier.supplierContact}</td>
				<td>${supplier.supplierEmail}</td>
				<td>${supplier.supplierAddress}</td>
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

</div>