<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Update Supplier Section -->

<div class="panel panel-warning">
	<div class="panel-heading">Update Supplier</div>
	<div class="panel-body">
		<div class="row">
			<div class="form-horizontal">
				<div class="col-md-6 ">
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Supplier Name</label>
						<div class="col-sm-10">
							<input type="text" value="${supplier.supplierName}"
								disabled class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Supplier
							Contact No</label>
						<div class="col-sm-10">
							<input type="text" disabled
								value="${supplier.supplierContact}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Email Id</label>
						<div class="col-sm-10">
							<input type="text" disabled
								value="${supplier.supplierEmail}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Supplier
							address</label>
						<div class="col-sm-10">
							<textarea type="text" rows="7" disabled
								placeholder="${supplier.supplierAddress}"
								class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Supplier
							Image</label>
						<div class="col-sm-10">
							<img src="${images}/supplier/${supplier.supplierId}"
								style="height: 100px; width: 100px"
								class="img-responsive img-rounded">
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<form:form action="${contextPath}/admin/viewSupplier/update?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" method="post" commandName="supplier" enctype="multipart/form-data">
					<form:hidden path="supplierId" />
					<div class="form-group">
						<label for="updateSupplierName" class="control-label col-sm-2">Supplier
							Name</label>
						<div class="col-sm-10">
							<form:input path="supplierName" type="text" name="updateSupplierName" value="${supplier.supplierName}" class="form-control"
								placeholder="Enter supplier's new name"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="updateSuppierContact" class="control-label col-sm-2">Supplier
							Contact No</label>
						<div class="col-sm-10">
							<form:input path="supplierContact" type="text" name="updateSuppierContact"
								class="form-control" value="${supplier.supplierContact}"
								placeholder="Enter supplier's new Contact No"/>
						</div>
					</div>
					<div class="form-group">
						<label for="updateSupplierEmail" class="control-label col-sm-2">Email
							Id</label>
						<div class="col-sm-10">
							<form:input type="email" path="supplierEmail" name="updateSupplierEmail"
								class="form-control" value="${supplier.supplierEmail}"  placeholder="Enter supplier's new Email Id"/>
						</div>
					</div>
					<div class="form-group">
						<label for="updateSupplierEmail" path="supplierAddress" class="control-label col-sm-2">Supplier
							Address</label>
						<div class="col-sm-10">
							<form:textarea type="text" rows="7" name="updateSupplierDescription"
								placeholder="Enter a new supplier description" path="supplierAddress"
								class="form-control"></form:textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="updateSupplierImage" class="control-label col-sm-2">Supplier
							Image</label>
						<div class="col-sm-10">
							<form:input type="file" path="supplierImage" name="updateSupplierImage"
								class="form-control" id=""/>
						</div>
					</div>
					<div class="form-group">
						<center>
							<input type="submit" value="Update Supplier"
								class="btn btn-warning btn-md">
						</center>
					</div>
				</form:form>
			</div>
			<!--  End of column two -->


		</div>
		<!-- End of row -->
	</div>
</div>


