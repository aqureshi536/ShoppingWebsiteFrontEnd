<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/bootstrap/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/bootstrap/js/jquery.min.js" var="jquery" />
<spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrapJs" />
<<spring:url value="/resources/customCss/" var="customCss"></spring:url>


<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="${customCss}full.css">

<link rel="stylesheet" href="${bootstrapCss}">
<link rel="stylesheet" href="${customCss}login.css">
<script src="${jquery}"></script>
<script src="${bootstrapJs}"></script>



<script>
	function checkEmail() {
		var loUserName = document.getElementById("email").value;
		var loPwd = document.getElementById("pwd").value;

		if (loUserName == "s@g.c") {
			document.getElementById("email").style.backgroundColor = 'red';
			return false;
		} else {
			document.getElementById("email").style.backgroundColor = 'white';
		}
	}
	var reEmail = document.getElementById("reemail").value;
	var rePwd = document.getElementById("repwd").value;
	var reConPwd = document.getElementById("reconpwd").value;
</script>
</head>
<body>
	<div class="container">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navigation,#loginNav">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="#" class="navbar-brand">My Furniture</a>

				</div>

				<ul class="nav navbar-nav" id="navigation">
					<li><a href="indx">Home</a></li>
					<li><a href="#">About Us</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="loginNav">
					<li class="active"><a href="javascript:void(0)"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>

			</div>
		</div>
		<br> <br> <br>
		<div class="row ">

			<!--  Panel for Login form ---->
			<div class="col-sm-5">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<b>Login</b>
					</div>
					<div class="panel-body">
					
						<form class="form-horizontal" role="form" action="validate" method="post">
							<div class="form-group">
								<label class="control-label col-sm-2" for="email">Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="email" name="loginEmail"
										placeholder="Enter email" required>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="pwd" name="loginPwd"
										placeholder="Enter password" required>
								</div>
							</div>
							<input type="submit" value="Login"
								class="btn btn-primary btn-md col-sm-offset-2"  onclick="checkEmail()">
						</form>
					</div>
					<!-- <div class="panel-footer">this is  a footer</div> -->
				</div>
			</div>








			<div class="col-sm-1"></div>

			<!--  Panel for Sign Up form ---->

			<div class="col-sm-6 ">
				<div class="panel panel-success">
					<div class="panel-heading" id="panelHeading">Sign Up</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" method="post">
							<div class="form-group">
								<label class="control-label col-sm-2" for="reemail">Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="reemail"
										placeholder="Enter email" required>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2" for="rephone">Phone
									No</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" pattern="^\d{10}$"
										title="Enter 10 digit mobile number" id="rephone"
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
										placeholder="Enter password" required>
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
	</div>





</body>
</html>