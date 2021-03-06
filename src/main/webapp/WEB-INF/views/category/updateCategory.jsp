<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Update Category -->
<div class="panel panel-warning">
	<div class="panel-heading ">Update Category</div>
	<div class="panel-body">
		<div class="row">
			<div class="form-horizontal">
				<!-- Previous category -->
				<div class="col-sm-6">
					<h4 class="col-xs-offset-2">Existing Category</h4>
					<div class="form-group">
						<label for="categoryName" class="control-label col-sm-2">Category
							Name</label>
						<div class="col-sm-10">

							<input type="text"
								value=${categoryToUpdate.categoryName
								}
								class="form-control" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Category
							Description</label>
						<div class="col-sm-10">
							<textarea class="form-control"
								placeholder="${categoryToUpdate.categoryDescription}" rows="7"
								cols="10" disabled></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="control-label col-sm-2">Category
							Image</label>
						<div class="col-sm-10">
							<img src="${images }/category/${categoryToUpdate.categoryId}.png"
								id="formImage" />
						</div>
					</div>
				</div>
				<!-- End of previous product column-->

				<!--start of update category -->
				<form:form
					action="${contextPath}/admin/viewCategory/updated?${_csrf.parameterName}=${_csrf.token}"
					method="post" class="form-horizontal" modelAttribute="category"
					enctype="multipart/form-data">
					<div class="col-sm-6">
						<h4 class="col-xs-offset-2">Update Category</h4>
						<form:hidden path="categoryId" />
						<div class="form-group">
							<label for="categoryName" class="control-label col-sm-2">Category
								Name</label>
							<div class="col-sm-10">
								<form:errors path="categoryName" class="error" />
								<form:input path="categoryName" autofocus="true" type="text"
									value="${categoryToUpdate.categoryName}" class="form-control"
									placeholder="Enter new category name" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for="" class="control-label col-sm-2">Category
								Description</label>
							<div class="col-sm-10">
								<form:errors path="categoryDescription" class="error" />
								<form:textarea path="categoryDescription" class="form-control"
									rows="7" cols="10"
									placeholder="Give a new category Description"
									required="required"></form:textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="control-label col-sm-2">Category
								Image</label>
							<div class="col-sm-10">
								<form:input path="categoryImage" class="form-control col-sm-10"
									type="file" />
							</div>
						</div>
						<center>
							<input type="submit" value="Update Category"
								class="btn btn-md btn-warning">
								 <a href="${contextPath}/admin/viewCategory" class="btn btn-md btn-danger">
								<span></span> Cancel</a>
						</center>
					</div>
				</form:form>
				<!-- End of new product forms -->
			</div>
		</div>
	</div>
</div>
<script>
	var warning = true;
	window.onbeforeunload = function() {
		if (warning) {
			return "You have made changes on this page that you have not yet confirmed. If you navigate away from this page you will lose your unsaved changes";
		}
	}

	$('form').submit(function() {
		window.onbeforeunload = null;
	});
</script>
