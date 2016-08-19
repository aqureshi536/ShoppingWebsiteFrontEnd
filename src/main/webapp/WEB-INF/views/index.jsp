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
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<%@include file="./shared/header.jsp" %>
<body>
	<div class="container-fluid">
		<!-- 	Navbar goes here -->
		<%@include file="./shared/menubar.jsp"%>


		<div class="content">
			<!-- Sidebar goes her -->

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
				<%--View Cart --%>
				<c:if test="${isClickedViewCart==true }">
					<%@include file="./cart/viewCart.jsp"%>			
				</c:if>
				<%-- --%>
				<c:if test="${isViewProductByCategory==true }">
				<%@include file="productByCategory.jsp" %>
				</c:if>
				<c:if test="${isShippingAddress==true }">
				<%@include file="./cart/shippingAddress.jsp" %>
				</c:if>
				<c:if test="${isBillingAddress==true }">
				<%@include file="./cart/billingAddress.jsp" %>
				</c:if>
			</div>
			<!--  col-sm-9 ends here -->
		
		<!-- row ends here -->

		<!-- footer -->
		<%@include file="./shared/footer.jsp"%>
</div>

	
</body>
</html>