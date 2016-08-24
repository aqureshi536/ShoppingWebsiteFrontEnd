
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="row ">

	<!--  Panel for Login form ---->
	<div class="col-sm-5">
		<c:if test="${not empty msg }">
			<div class="alert alert-success">
				<b>${msg}</b>
			</div>
		</c:if>
		<c:if test="${not empty error }">
			<div class="alert alert-danger">
				<b>${error}</b>
			</div>
		</c:if>
		<c:if test="${registrationSuccessfull == true }">
			<div class="alert alert-success">
				<b>Registration successful, Please login again</b>
			</div>
		</c:if>








		<div class="panel panel-primary" id="panelLogin">
			<div class="panel-heading" id="panel-headingLogin">Login</div>
			<div class="panel-body">
				<%-- <c:url value="/j_spring_security_check" var="loginUrl" /> --%>


				<form class="form-horizontal" role="form"
					action=" ${contextPath}/j_spring_security_check" method="post">
					<div class="form-group">
						<label class="control-label col-sm-2" for="username">Email</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="Enter username" required
								autofocus="autofocus">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="password">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="Enter password" required>
						</div>
					</div>
					<input type="submit" value="Login"
						class="btn btn-primary btn-md col-sm-offset-2"> <input
						type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token }" />
				</form>
			</div>
			<!-- <div class="panel-footer">this is  a footer</div> -->
		</div>
	</div>








	<div class="col-sm-1"></div>

	<!--  Panel for Sign Up form ---->

	<div class="col-sm-6 ">
	<c:if test="${userExists==true}">
	<div class="alert alert-danger"><strong>Oops! Try different email or username </strong></div>
	</c:if>
		<div class="panel panel-success" id="panelSignUp">
			<div class="panel-heading" id="panel-headingSignUp">Sign Up</div>
			<div class="panel-body">
				<form:form class="form-horizontal" onsubmit="return verifyConfirmPassword()" role="form" method="post"
					modelAttribute="customer" action="${contextPath}/login?registered">
					<div class="form-group">
						<label class="control-label col-sm-2" for="reemail">Email</label>
						<div class="col-sm-10">
						<form:errors path="username" class="error"/>
							<form:input path="username" type="email" class="form-control"
								id="reemail" placeholder="Enter email" required="true"
								name="reEmail"  title="Enter a valid email Id"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="rephone">Phone
							No</label>
						<div class="col-sm-10">
						<form:errors path="phoneNo" class="error"/>
							<form:input path="phoneNo" type="text" class="form-control"
								pattern="^\d{10}$" title="Enter 10 digit mobile number"
								id="rephone" name="rePhone" placeholder="Enter Contact Number" 
								required="true" />
						</div>
					</div>
					<div class="form-group ">
					<form:errors path="gender" class="error"/>
						<label for="gender" class="control-label col-sm-2">Gender</label>
						<div class="col-sm-offset-1 col-sm-9">
							<label for="maleGender" class="radio-inline  label-control"><form:radiobutton
									path="gender" name="gender" id="maleGender" value="male" required="true" />Male</label>
							<label for="femaleGender" class="radio-inline label-control">
								<form:radiobutton path="gender" name="gender" value="female" id="femaleGender"
									required="true" />Female
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="repwd">Password</label>
						<div class="col-sm-10">
						<form:errors path="password" class="error"/>
							<form:input path="password" type="password" class="form-control"
								id="repwd" placeholder="Enter password" pattern="[0-9a-zA-Z]{8,}" title="Password should have minimum 8 charcters" required="true" 
								name="rePass" />
<!-- 								 -->
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="reconpwd">Confirm
							Password</label>
						<div class="col-sm-10">
							<input type="password" id="reconpwd" class="form-control"
								placeholder="Confirm Password" required>
						</div>
					</div>
					<input type="submit" value="Sign Up"
						class="col-sm-offset-2 btn btn-success btn-md">
				</form:form>
			</div>
			<!-- <div class="panel-footer">this is  a footer</div> -->
		</div>
	</div>
	<!--signup col -->


</div>
