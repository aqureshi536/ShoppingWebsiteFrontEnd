<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Add Supplier -->

<div class="panel panel-success">
	<div class="panel-heading">Add Supplier</div>
	<div class="panel-body">
		<div class="row">

			<form:form
				action="${contextPath}/admin/viewSupplier?${_csrf.parameterName}=${_csrf.token}"
				class="form-horizontal" method="post" commandName="supplier"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="supplierName" class="control-label col-sm-2">Supplier
						Name</label>
					<div class="col-sm-10">
						<form:errors path="supplierName" class="error" />
						<form:input type="text" path="supplierName" name="supplierName"
							placeholder="Enter supplier name" class="form-control"
							required="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Supplier
						Contact No</label>
					<div class="col-sm-10">
						<form:errors path="supplierContact" class="error" />
						<form:input path="supplierContact" type="text"
							pattern="^[0-9]{10}" class="form-control" required="true" />
					</div>
				</div>
				<div class="form-group">
					<label for="supplierEmail" class="control-label col-sm-2">Supplier
						Email Id</label>
					<div class="col-sm-10">
						<form:errors path="supplierEmail" class="error" />
						<form:input path="supplierEmail" type="email" name="supplierEmail"
							class="form-control" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="supplierAddress" class="control-label col-sm-2">Supplier
						Address</label>
					<div class="col-sm-10">
						<form:errors path="supplierAddress" class="error" />
						<form:textarea path="supplierAddress" name="supplierAddress"
							rows="7" class="form-control" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="supplierImage" class="control-label col-sm-2">Upload
						Supplier Image</label>
					<div class="col-sm-10">
						<form:input path="supplierImage" type="file" class="form-control"
							name="supplierImage" />
					</div>
				</div>
				<input type="submit" value="Add Supplier"
					class="col-sm-offset-2 btn btn-success btn-md">
			</form:form>
		</div>
	</div>
</div>

