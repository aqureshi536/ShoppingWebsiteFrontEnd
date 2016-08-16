<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-sm-6">
		<img src="${images}/My Furniture Logo.png" class="img-responsive">
	</div>
	<div class="col-sm-6">
		<div class="input-group" style="padding-top: 50px;">
			<input type="search" class="form-control" placeholder="Search"
				ng-model="searchProduct"> <span class="input-group-addon"><div
					class="glyphicon glyphicon-search"></div></span>
		</div>
	</div>



	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target="#homeNavbar" id="homenavbarToggle">
		<span><b>Categories</b></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span> <span class="icon-bar"></span>
	</button>
</div>

<div class="navbar navbar-inverse collapse navbar-collapse"
	id="homeNavbar" data-spy="affix" data-offset-top="124">

	<ul class="nav navbar-nav" data-hover="dropdown" data-animations="fadeInDown fadeInRight fadeInUp fadeInLeft">
		<li id="home"><a id="A_home" href="${contextPath}/index"><span
				class="glyphicon glyphicon-home"></span> Home</a></li>
		<li id="category"><a href="${contextPath}/allProducts"
			id="A_category">Home & Furniture</a></li>

<%-- 		<c:forEach items="${categoryList}" var="category"> --%>
<%-- 			<li><a href="${contextPath}/allProducts/${category.categoryId}">${category.categoryName}</a></li> --%>
<%-- 			<%-- <c:choose> --%>
<%-- 		<c:when test="${isViewProductByCategory==true }">		 --%>
<%-- 		<li><a href="${contextPath}/allProducts/${category.categoryId}">${category.categoryName}</a></li> --%>
<%-- 		</c:when> --%>
<%-- 		<c:otherwise> --%>
		
<%-- 		</c:otherwise> --%>
<%-- 		</c:choose> --%> --%>
<%-- 		</c:forEach> --%>

	</ul>
	<ul class="nav navbar-nav navbar-right">
		<sec:authorize access="hasRole('ROLE_USER')">
			<c:if test="${displayCart == true }">
				<li id="viewCart"><a id="A_viewCart" href="${contextPath}/user/cart/"><span
						class="fa fa-cart-plus"></span> Cart <span class="badge">${noOfProducts}</span></a></li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<c:if
				test="${pageContext.request.userPrincipal.name != null }">
				<li><a href="${contextPath}/admin/viewProducts"><span
						class="glyphicon glyphicon-user"></span>
						${pageContext.request.userPrincipal.name}</a></li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
			<c:if test="${ pageContext.request.userPrincipal.name != null }">
				<li><a href="javascript:void(0)"><span class="glyphicon glyphicon-user"></span>
						${pageContext.request.userPrincipal.name}</a></li>
			</c:if>
		</sec:authorize>
		<c:if test="${pageContext.request.userPrincipal.name == null }">
			<li id="login"><a id="A_login" href="${contextPath}/login"><span
					class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</c:if>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<li><a href="javascript:formSubmit()"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>

		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name != null }">
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
		</c:if>



	</ul>
</div>
