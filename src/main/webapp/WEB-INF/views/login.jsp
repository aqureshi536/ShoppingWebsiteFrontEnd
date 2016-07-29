


<div class="row ">

	<!--  Panel for Login form ---->
	<div class="col-sm-5">
		<div class="panel panel-primary" id="panelLogin">
			<div class="panel-heading" id="panel-headingLogin">Login</div>
			<div class="panel-body">

				<form class="form-horizontal" role="form" action="validate"
					method="post">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email"
								name="loginEmail" placeholder="Enter email" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pwd"
								name="loginPwd" placeholder="Enter password" required>
						</div>
					</div>
					<input type="submit" value="Login"
						class="btn btn-primary btn-md col-sm-offset-2"
						onclick="checkEmail()">
				</form>
			</div>
			<!-- <div class="panel-footer">this is  a footer</div> -->
		</div>
	</div>








	<div class="col-sm-1"></div>

	<!--  Panel for Sign Up form ---->

	<div class="col-sm-6 ">
		<div class="panel panel-success" id="panelSignUp">
			<div class="panel-heading" id="panel-headingSignUp">Sign Up</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" method="post"
					action="register">
					<div class="form-group">
						<label class="control-label col-sm-2" for="reemail">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="reemail"
								placeholder="Enter email" required name="reEmail">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="rephone">Phone
							No</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" pattern="^\d{10}$"
								title="Enter 10 digit mobile number" id="rephone" name="rePhone"
								placeholder="Enter Contact Number" required>
						</div>
					</div>
					<div class="form-group ">
						<label for="gender" class="control-label col-sm-2">Gender</label>
						<div class="col-sm-offset-1 col-sm-9">
							<label for="maleGender" class="radio-inline  label-control"><input
								type="radio" name="gender" id="maleGender" required>Male</label>
							<label for="femaleGender" class="radio-inline label-control"><input
								type="radio" name="gender" id="femaleGender" required>Female</label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="repwd">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="repwd"
								placeholder="Enter password" required name="rePass">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="reconpwd">Confirm
							Password</label>
						<div class="col-sm-10">
							<input type="text" id="reconpwd" class="form-control"
								placeholder="Confirm Password" required>
						</div>
					</div>
					<input type="submit" value="Sign Up"
						class="col-sm-offset-2 btn btn-success btn-md">
				</form>
			</div>
			<!-- <div class="panel-footer">this is  a footer</div> -->
		</div>
	</div>
	<!--signup col -->


</div>
