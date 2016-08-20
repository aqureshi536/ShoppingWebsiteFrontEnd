<div class="productMainDiv">

	<c:if test="${addedCategoryMessage==true }">
		<div class="alert alert-success" id="messageDelete-success">
			<strong>Added!</strong> category with name <strong>${categoryName}</strong>
			on row number <strong>${categoryListSize}</strong>
		</div>
	</c:if>
	<c:if test="${deletedCategoryMessage==true }">
		<div class="alert alert-danger" id="messageDelete-danger">
			<strong>Deleted!</strong> category with name <strong>${categoryNameDeleted}</strong>
		</div>
	</c:if>
	<c:if test="${categoryUpdateMessage==true }">
		<div class="alert alert-warning" id="categoryUpdateMessage">
			<strong>Updated!</strong> category with name <strong>${categoryNameAfterUpdate}</strong>
		</div>
	</c:if>
	<a href="${contextPath}/admin/viewCategory/addCategory"
		class="btn btn-lg btn-success">Add Category </a> <br> <br>
	<div class="table-outer">
		<table class="table table-hover" id="viewTable">
			<thead>
				<%
					int i = 1;
					int j = 0;
				%>
				<tr class="table-primary">
					<c:forEach items="${categories }" var="category">
						<%
							j++;
						%>
					</c:forEach>
					<th>Sr.No <span>( <%
						out.println(j);
					%> )
					</span>
					</th>
					<th>Category Image</th>
					<th>Category Name</th>
					<th>Category Description</th>
					<th>Products Present</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${categories}" var="c">

					<tr onclick="myhref('${contextPath}/allProducts/${c.category.categoryId}')">

						<td>
							<%
								out.println(i);
							%>
						</td>

						<td><img
							src="${images}/category/${c.category.categoryId}.png"
							id="tableImage"></td>
						<td>${c.category.categoryName}</td>
						<td>${c.category.categoryDescription}</td>
						<td>${c.noOfProducts}</td>
						<td>
							<div class="btn-group-vertical">
								<a
									href="${contextPath}/admin/viewCategory/update/${c.category.categoryId}"
									class="btn btn-sm btn-warning">Update Category</a> <a
									href="${contextPath}/admin/viewCategory/delete/${c.category.categoryId}"
									class="btn btn-sm btn-danger">Delete Category</a>
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
</div>