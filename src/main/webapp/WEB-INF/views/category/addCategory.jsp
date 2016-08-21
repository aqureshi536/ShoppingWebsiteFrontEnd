<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- ADD Category -->

<div class="panel panel-success">
	<div class="panel-heading">Add Category</div>
	<div class="panel-body">
		<div class="row">


			<form:form class="form-horizontal" role="form"
				action="${contextPath}/admin/viewCategory?${_csrf.parameterName}=${_csrf.token}"
				enctype="multipart/form-data" method="post"
				modelAttribute="category">
				<div class="form-group">
					<label for="categoryName" class="control-label col-sm-2">Category
						Name</label>
					<div class="col-sm-10">
						<form:errors path="categoryName" class="error" />
						<form:input path="categoryName" type="text" class="form-control"
							placeholder="Enter category name" required="required"></form:input>
					</div>
				</div>

				<div class="form-group">
					<label for="" class="control-label col-sm-2">Category
						Description</label>
					<div class="col-sm-10">
						<form:errors path="categoryDescription" class="error" />
						<form:textarea path="categoryDescription" class="form-control"
							rows="7" cols="10" placeholder="Give a category Description"
							required="required"></form:textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Category Image</label>
					<div class="col-sm-10">
						<form:input path="categoryImage" class="form-control" type="file" />
					</div>
				</div>
				<div class="col-sm-offset-2">
					<input type="submit" class="btn btn-success btn-md"
						value="Add Category"> 
						<a onclick=window.history.back()
						class="btn btn-md btn-danger"> <span></span> Cancel
					</a>
					
				</div>
			</form:form>

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