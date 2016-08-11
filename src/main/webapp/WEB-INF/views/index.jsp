<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Css -->
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/css/bootstrap-theme.css"
	var="bootstrapTheme" />
<spring:url value="/resources/css/font-awesome.css" var="fontAwesome" />
<spring:url value="/resources/customCss" var="customCss" />
<spring:url value="/resources/images" var="images" />
<!-- JavaScript -->
<spring:url value="/resources/customScript/home.js" var="angularApp" />
<spring:url value="/resources/js/angular.js" var="Angularjs" />
<spring:url value="/resources/js/angular-route.js" var="angularRoute" />
<spring:url value="/resources/js/jquery-1.12.4.js" var="jQuery" />
<spring:url value="/resources/js/bootstrap.js" var="boostrapJs" />
<spring:url value="/resources/js" var="js" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="./shared/header.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<!-- 	Navbar goes here -->
		<%@include file="./shared/menubar.jsp"%>


		<div class="row">
			<!-- Sidebar goes her -->
	<br>
			<div class="col-md-2">
				<%@include file="./shared/sidebar.jsp"%>
			</div>
			<!--  col-sm-2 ends here -->
			
			<!-- Main Content goes here -->
			<div class="col-sm-10">
				<!-- activates Home -->
				<c:if test="${isHomeClicked==true }">
					<%@include file="home.jsp"%>
				</c:if>

				<!-- activates Login Page -->
				<c:if test="${isLoginClicked==true }">
					<%@include file="login.jsp"%>
				</c:if>

				<!-- Activates Product Details Page-->
				<c:if test="${isClickedProductDetail==true }">
					<%@include file="productDetail.jsp"%>
				</c:if>

				<!-- Activates View All Products -->
				<c:if test="${isClickedViewAllProducts==true }">
					<%@include file="allProducts.jsp"%>
				</c:if>

				<!-- Activates on AdminViewProducts clicked -->
				<c:if test="${isClickedAdminViewProducts==true }">
					<%@include file="./admin/adminViewProducts.jsp"%>
				</c:if>

				<!-- Activates on AdiminViewCategory clicked -->
				<c:if test="${isClickedAdminViewCategory==true }">
					<%@include file="./admin/adminViewCategory.jsp"%>
				</c:if>
				
				<!-- Activates on AdiminViewSupplier clicked -->
				<c:if test="${isClickedAdminViewSupplier==true }">
					<%@include file="./admin/adminViewSupplier.jsp"%>
				</c:if>

				<!-- Activates Add Product -->
				<c:if test="${isAddProductClicked==true }">
					<%@include file="./product/addProduct.jsp"%>
				</c:if>

				<!--Activates Update Product -->
				<c:if test="${isUpdateProductClicked==true }">
					<%@include file="./product/updateProduct.jsp"%>
				</c:if>

				<!-- Activates Add Category -->
				<c:if test="${isAddCategoryClicked==true }">
					<%@include file="./category/addCategory.jsp"%>
				</c:if>

				<!--Activates Update Category -->
				<c:if test="${isClickedAdminUpdateCategory==true }">
					<%@include file="./category/updateCategory.jsp"%>
				</c:if>

				<!-- Activates Add Supplier -->
				<c:if test="${isAddSupplierClicked==true }">
					<%@include file="./supplier/addSupplier.jsp"%>
				</c:if>

				<!--Activate Update Supplier -->
				<c:if test="${isUpdateSupplierClicked==true }">
					<%@include file="./supplier/updateSupplier.jsp"%>
				</c:if>
			</div>
			<!--  col-sm-9 ends here -->
		</div>
		<!-- row ends here -->

		<!-- footer -->
		<%@include file="./shared/footer.jsp"%>


	</div>
</body>
</html>