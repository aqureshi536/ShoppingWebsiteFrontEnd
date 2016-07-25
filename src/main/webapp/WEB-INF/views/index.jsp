<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
                  <!-- Css -->
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss"/>
<spring:url value="/resources/css/bootstrap-theme.css" var="bootstrapTheme"/>
<spring:url value="/resources/css/font-awesome.css" var="fontAwesome"/>
<spring:url value="/resources/customCss/indexCss.css" var="customCss"/>
<spring:url value="/resources/images" var="images"/>
          <!-- JavaScript -->
<spring:url value="/resources/customScript/home.js" var="angularApp"/>
<spring:url value="/resources/js/angular.js" var="Angularjs"/>
<spring:url value="/resources/js/angular-route.js" var="angularRoute"/>
<spring:url value="/resources/js/jquery-1.12.4.js" var="jQuery"/>
<spring:url value="/resources/js/bootstrap.js" var="boostrapJs"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="./shared/header.jsp" %>
<title>Insert title here</title>
</head>
<body>
<%@include file="./shared/menubar.jsp" %>
<%@include file="./shared/sidebar.jsp" %>
<%@include file="./shared/footer.jsp" %>



</body>
</html>