
<%
	int i = 1;
	int j = 0;
	int k = 0;
%>
<div class="productMainDiv">

	<c:if test="${deleteProductSuccessMessage==true}">
		<div class="alert alert-danger" id="message-danger">
			<strong>Deleted!</strong> product with name <strong>${product.productName}</strong>.
		</div>
	</c:if>


	<!-- for displaying add product success Message -->

	<c:if test="${addProductSuccessMessage==true}">
		<div class="alert alert-success" id="message-success">
			<strong>Added!</strong> product with name <strong>${lastProduct}</strong>
			on row number <strong>${productListSize}</strong>.
		</div>
	</c:if>

	<c:if test="${updateProductSuccessMessage==true}">
		<div class="alert alert-warning" id="ProductUpdated-warning">
			Product <strong>Updated!</strong> with name <strong>${productNameToUpdate}</strong>
		</div>
	</c:if>
	<%-- with name <strong>${lastProduct}</strong> on --%>
	<%-- 		row number <strong>${productListSize}</strong>. --%>
	<a href="${contextPath}/admin/viewProducts/addProduct"
		class="btn btn-lg btn-success">Add Product</a> <br> <br>
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
					<th>Product Category</th>
					<th>Product Supplier</th>
					<th>Product Quantity</th>
					<th>Product Price</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${products}" var="p">


					<tr onclick="myhref('${contextPath}/productDetail/${p.product.productId}')">
						<td>
							<%
								out.println(i);
							%>
						</td>
						<td><img src="${images}/product/${p.product.productId}.png"
							id="tableImage"></td>
						<td>${p.product.productName }</td>
						<td>${p.product.description }</td>
						<td>${p.categoryName}</td>

						<td>${p.supplierName}</td>
						<c:choose> 
						<c:when test="${p.product.isOutOffStock}">
						<td style="color:red;font-size:20px;">${p.product.quantity }</td>
						</c:when>
						<c:otherwise >
						<td>${p.product.quantity }</td>
						</c:otherwise>
						</c:choose>
						<td>${p.product.price }</td>
						<td>
							<div class="btn-group-vertical">
								<a
									href="${contextPath}/admin/viewProducts/updateProduct/${p.product.productId}"
									class="btn btn-sm btn-warning">Update Product</a> <a
									href="${contextPath}/admin/viewProducts/delete/${p.product.productId}"
									class="btn btn-sm btn-danger" id="confirmDelete">Delete
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
</div>
