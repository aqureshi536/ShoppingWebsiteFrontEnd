<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!-- Css -->
<spring:url value="/resources/css" var="css" />
<spring:url value="/resources/images" var="images" />
<!-- JavaScript -->
<spring:url value="/resources/js" var="js" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<%@include file="../shared/header.jsp"%>

<body>
	<div class="container-fluid">
		<%@include file="../shared/menubar.jsp"%>

		<div class="content">
			<div class="container">
				<div class="jumbotron">
					<h1>Order Confirmed,Product will be delivered shortly.</h1>
				</div>


			</div>
		</div>
		<%@include file="../shared/footer.jsp"%>
	</div>
</body>



<%@include file="../shared/footer.jsp"%>

</html>