<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="panel panel-primary">
		<div class="panel-heading">Shipping Address</div>
		<div class="panel-body">
			<form:form action="" class="form-horizontal" commandName="shippingAddress" >
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Line 1</label>
					<div class="col-sm-10"><form:input path="line1" type="text" class="form-control"/></div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Line 2</label>
					<div class="col-sm-10"><form:input path="line2" type="text" class="form-control"/></div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">City</label>
					<div class="col-sm-10"><input type="text" path="city" class="form-control"></div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">State</label>
					<div class="col-sm-10"><input type="text" path="" class="form-control"></div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Country</label>
					<div class="col-sm-10"><input type="text" path="" class="form-control"></div>
				</div>
				<div class="form-group">
					<label for="" class="control-label col-sm-2">Zip code</label>
					<div class="col-sm-10"><input type="text" path="" class="form-control"></div>
				</div>
			</form:form>
		</div>
	</div>