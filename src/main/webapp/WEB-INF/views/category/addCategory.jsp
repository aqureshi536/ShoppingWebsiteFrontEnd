<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- ADD Category -->

<div class="panel panel-success">
<div class="panel-heading">Add Category</div>
	<div class="panel-body">
		<div class="row">
			

				<form:form class="form-horizontal" role="form" action="admin/viewProduct/addProduct" method="post" commandName="Category">
					<div class="form-group">
						<label for="categoryName" class="control-label col-sm-2">Category
							Name</label>
						<div class="col-sm-10">
							<form:input path="categoryName" type="text" class="form-control"
								placeholder="Enter category name"></form:input>
						</div>
					</div>
					<div class="form-group">
						<label for="categorySupplier" class="control-label col-sm-2">Select
							Supplier</label>
						<div class="col-sm-10">
							<select name="categorySupplier" id="" class="form-control">
								<option disabled selected>Select Supplier</option>
								<option value="">ss</option>
								<option value="">ss</option>
								<option value="">ss</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Category
							Description</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="7" cols="10"
								placeholder="Give a category Description"></textarea>
						</div>
					</div>
					<div class="col-sm-offset-2">
						<input type="submit" class="btn btn-success btn-md"
							value="Add Category">
					</div>
				</form:form>

			</div>
		</div>
	</div>
