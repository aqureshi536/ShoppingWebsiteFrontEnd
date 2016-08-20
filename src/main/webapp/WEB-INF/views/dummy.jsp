<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Css -->
<spring:url value="/resources/css" var="css" />
<spring:url value="/resources/images" var="images" />
<!-- JavaScript -->
<spring:url value="/resources/js" var="js" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
</head>
<body ng-app="angularModule">
	<div ng-controller="dummyController">
		<div ng-repeat="dummy in dummmyList">
			{{dummy.productName}}<br> {{dummy.price}}
		</div>
	</div>
	<script src="${js}/jquery-1.12.4.js"></script>
	<script src="${js}/bootstrap.js"></script>
	<script src="${js}/angular.js"></script>
	<script src="${js}/app.js"></script>
</body>
</html>