<table class="table table-hover" id="viewTable">
		<thead>
		<%int i=1;
		int j=0;%>
			<tr class="table-primary">
			<c:forEach items="${categories }" var="category">
		<%j++; %>
		</c:forEach>
			<th>Sr.No <span>( <%out.println(j);%> )</span> 	</th>
				<th>Category Image</th>
				<th>Category Name</th>
				<th>Category Description</th>
				<th>Products Present</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${categories}" var="c">
		
			<tr>
			
			<td><%out.println(i);%></td>
			
				<td><img src="${images}/category/${c.category.categoryImage}" id="tableImage"></td>
				<td valign="middle">${c.category.categoryName}</td>
				<td>${c.category.categoryDescription}</td>
				<td>${c.noOfProducts}</td>
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