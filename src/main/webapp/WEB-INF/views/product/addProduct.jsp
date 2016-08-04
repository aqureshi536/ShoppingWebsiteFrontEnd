<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Add Product -->

<div class="panel panel-success">
	<div class="panel-heading">Add Product</div>
	<div class="panel-body">
		<div class="row">

			<form:form
				action="${contextPath}/admin/viewProducts?${_csrf.parameterName}=${_csrf.token}"
				class="form-horizontal" commandName="Product"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="productName" class="control-label col-sm-2">Product
						Name</label>
					<div class="col-sm-10">
						<form:input type="text" path="productName" class="form-control"
							placeholder="Enter product name" />
					</div>
				</div>
				<div class="form-group">
					<label for="productCategory" class="control-label col-sm-2">Select
						Category</label>
					<div class="col-sm-10">
						<form:select path="categoryId" name="productCategory" id=""
							class="form-control">
							<option value="-" disabled selected>Select Category</option>
							<c:forEach items="${categories}" var="category">
								<form:option value="${category.categoryId}">${category.categoryName}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="productSupplier" class="control-label col-sm-2">Product
						Supplier</label>
					<div class="col-sm-10">
						<form:select path="supplierId" name="productSupplier" id=""
							class="form-control">
							<option value="-" selected disabled>Select Supplier</option>
							<c:forEach items="${suppliers}" var="supplier">
								<form:option value="${supplier.supplierId}">${supplier.supplierName}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="productPrice" class="control-label col-sm-2">Product
						price</label>
					<div class="col-sm-10">
						<form:input path="price" type="text" pattern="^[0-9]+$"
							name="productPrice" class="form-control" />

					</div>
				</div>
				<div class="form-group">
					<label for="productStock" class="control-label col-sm-2">Units
						Available</label>
					<div class="col-sm-10">
						<form:input path="quantity" type="text" pattern="^[0-9]+$"
							name="productStock" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Product
						description</label>
					<div class="col-sm-10">
						<form:textarea path="description" rows="7" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">Product Image</label>
					<div class="col-sm-10">
						<form:input type="file" path="imageUrl" class="form-control " />
					</div>


				</div>
				<div class="col-sm-offset-2">
					<input type="submit" class="btn btn-success btn-md"
						value="Add Product">
				</div>
			</form:form>
		</div>
	</div>
</div>
