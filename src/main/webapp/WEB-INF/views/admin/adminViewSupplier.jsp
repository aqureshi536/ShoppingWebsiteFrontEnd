<div class="productMainDiv">

	<c:if test="${ addedSupplierMessage==true}">
		<div class="alert alert-success" id="addedSupplierMessage">
			<strong>Added!</strong>supplier with name<strong>${supplierName}</strong>
			on row number ${supplierSize}
		</div>
	</c:if>
	<c:if test="${deletedSupplierMessage== true }">
		<div class="alert alert-danger" id="deletedSupplierMessage">
			<strong>Deleted!</strong> supplier with name <strong>${supplierNameDeleted}</strong>
		</div>
	</c:if>
	<c:if test="${updatedSupplierMessage== true }">
		<div class="alert alert-warning" id="updatedSupplierMessage">
			<strong>Updated!</strong> supplier with name <strong>${updatedSupplierName}</strong>
		</div>
	</c:if>



	<a href="${contextPath}/admin/viewSupplier/addSupplier"
		class="btn btn-lg btn-success">Add Supplier</a> <br> <br>
	<c:choose>
		<c:when test="${not empty noSupplier}">
			<h2>${noSupplier}</h2>
		</c:when>
		<c:otherwise>
			<div class="table-outer">
				<table class="table table-hover table-responsive" id="viewTable">
					<thead>
						<%
							int i = 1;
									int j = 0;
						%>

						<tr class="table-primary">
							<c:forEach items="${suppliers}" var="supplier">
								<%
									j++;
								%>
							</c:forEach>
							<th>Sr.No <span>( <%
								out.println(j);
							%> )
							</span>
							</th>


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
									<%
										out.println(i);
									%>
								</td>
								<td><img
									src="${contextPath}/images/suppliers/${supplier.supplierId}.png"
									id="tableImage"></td>
								<td>${supplier.supplierName}</td>
								<td>${supplier.supplierContact}</td>
								<td>${supplier.supplierEmail}</td>
								<td>${supplier.supplierAddress}</td>
								<td>
									<div class="btn-group-vertical">
										<a
											href="${contextPath}/admin/viewSupplier/updateSupplier/${supplier.supplierId}"
											class="btn btn-sm btn-warning">Update Supplier</a> <a
											onclick="return confirm('Do you want to delete supplier ${supplier.supplierName}  ?')"
											href="${contextPath}/admin/viewSupplier/deleteSupplier/${supplier.supplierId}"
											class="btn btn-sm btn-danger"> Delete Supplier </a>
									</div>
								</td>
							</tr>
							<%
								i++;
							%>
						</c:forEach>
					</tbody>
				</table>
		</c:otherwise>
	</c:choose>
</div>

</div>