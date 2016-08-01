<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-warning">
	<div class="panel-heading">Update Product</div>
	<div class="panel-body">
		<div class="row form-horizontal">


			<div class="row">
				<div class="col-sm-6">
					<h4>Existing Product</h4>
					<form:hidden path="productId"/>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">Product Name</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								value="${productToUpdate.productName}" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">Product
							Category</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" value="${categoryName}"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">Product
							Supplier</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" value="${supplierName}"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">Product Price</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								value="${productToUpdate.price }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">Product Units</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								value="${productToUpdate.quantity}" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">Product
							Description</label>
						<div class="col-sm-10">
							<textarea rows="7" class="form-control"
								placeholder="${productToUpdate.description}" disabled></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Product Image</label>
						<div class="col-sm-10">
							<img class="img-rounded"
								alt="${images}/product/${productToUpdate.productId}.png"
								src="${images}/product/${productToUpdate.productId}.png"
								id="formImage">
						</div>
					</div>
				</div>


				<div class="col-sm-6">
					<h4>Update Product</h4>
					<form:form action="/admin/viewProducts/updateProduct" method="post" commandName="Product" enctype="multipart/form-data">
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Product Name</label>
							<div class="col-sm-10">
								<form:input type="text" path="productName" class="form-control"
									placeholder="Enter new product name" />
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Product
								Category</label>
							<div class="col-sm-10">
								<select name="updateProductCategory" id="" class="form-control">
									<option value="" selected disabled>Select the category
										to be updated</option>
									<c:forEach items="${categories}" var="category">
										<form:option value="${category.categoryId}">${category.categoryName}</form:option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Product
								Supplier</label>
							<div class="col-sm-10">
								<select name="updateProductSupplier" id="" class="form-control">
									<option value="" selected disabled>Select the supplier
										to be updated</option>
									<c:forEach items="${suppliers}" var="supplier">
										<option value="${supplier.supplierId}">${supplier.supplierName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Product
								Price</label>
							<div class="col-sm-10">
								<input type="text" pattern="^[0-9]+$" class="form-control"
									placeholder="Enter new product price">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Product
								Units</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"
									placeholder="Enter new product stock">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">Product
								Description</label>
							<div class="col-sm-10">
								<textarea rows="7" class="form-control"
									placeholder="Enter a new product description"></textarea>
							</div>
						</div>
					</form:form>
				</div>

			</div>
		</div>
		<center>
			<input type="submit" value="Update Product"
				class="btn btn-md btn-warning">
		</center>

	</div>
</div>
</div>
