<!-- Add Product -->

<div class="panel panel-success">
<div class="panel-heading">Add Product</div>
	<div class="panel-body">
		<div class="row">
			
				<form action="" class="form-horizontal">
					<div class="form-group">
						<label for="productName" class="control-label col-sm-2">Product
							Name</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="productName"
								placeholder="Enter product name">
						</div>
					</div>
					<div class="form-group">
						<label for="productCategory" class="control-label col-sm-2">Select
							Category</label>
						<div class="col-sm-10">
							<select name="productCategory" id="" class="form-control">
								<option value="" disabled selected>Select Category</option>
								<option value="">ss</option>
								<option value="">ss</option>
								<option value="">ss</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="productSupplier" class="control-label col-sm-2">Product
							Supplier</label>
						<div class="col-sm-10">
							<select name="productSupplier" id="" class="form-control">
								<option value="" selected disabled>Select Supplier</option>
								<option value="">sd</option>
								<option value="">ds</option>
								<option value="">xx</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="productPrice" class="control-label col-sm-2">Product
							price</label>
						<div class="col-sm-10">
							<input type="text" pattern="^[0-9]+$" name="productPrice"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="productStock" class="control-label col-sm-2">Units
							Available</label>
						<div class="col-sm-10">
							<input type="text" pattern="^[0-9]+$" name="productStock"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Product
							description</label>
						<div class="col-sm-10">
							<textarea rows="7" class="form-control"></textarea>
						</div>
					</div>
					<div class="col-sm-offset-2">
						<input type="submit" class="btn btn-success btn-md"
							value="Add Product">
					</div>
				</form>
			</div>
		</div>
	</div>
