<!-- Add Supplier -->

<div class="panel panel-success">
<div class="panel-heading">Add Supplier</div>
	<div class="panel-body">
		<div class="row">
			
				<form action="" class="form-horizontal">
					<div class="form-group">
						<label for="supplierName" class="control-label col-sm-2">Supplier
							Name</label>
						<div class="col-sm-10">
							<input type="text" name="supplierName"
								placeholder="Enter supplier name" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Supplier
							Contact No</label>
						<div class="col-sm-10">
							<input type="text" pattern="^[0-9]{10}" class="form-control"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="supplierEmail" class="control-label col-sm-2">Supplier
							Email Id</label>
						<div class="col-sm-10">
							<input type="email" name="supplierEmail" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="supplierAddress" class="control-label col-sm-2">Supplier
							Address</label>
						<div class="col-sm-10">
							<textarea name="supplierAddress" rows="7" class="form-control"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="supplierImage" class="control-label col-sm-2">Upload
							Supplier Image</label>
						<div class="col-sm-10">
							<input type="file" class="form-control" name="supplierImage">
						</div>
					</div>
					<input type="submit" value="Add Supplier"
						class="col-sm-offset-2 btn btn-success btn-md">
				</form>
			</div>
		</div>
	</div>

