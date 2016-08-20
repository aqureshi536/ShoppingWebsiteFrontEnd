<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-sm-7">
		<img src="${images}/My Furniture Logo.png" class="img-responsive">
	</div>
	<div class="col-sm-4 col-xs-9">
		<div style="padding-top: 50px;">
			<form:form action="${contextPath}/search/" method="get">
				<input type="search" class="form-control" placeholder="Search"
					ng-model="searchProduct" name="keyword">
		</div>
	</div>
	<div class="col-sm-1 col-xs-2" style="padding-top: 50px;">
		<input type="submit" value="search" class="btn btn-md btn-info"
			ng-model="searchProduct">
	</div>
	</form:form>



</div>







<button type="button" class="navbar-toggle" data-toggle="collapse"
	data-target="#homeNavbar" id="homenavbarToggle">
	<span><b>Categories</b></span> <span class="icon-bar"></span> <span
		class="icon-bar"></span> <span class="icon-bar"></span>
</button>


<div class="navbar navbar-inverse collapse navbar-collapse"
	id="homeNavbar" data-spy="affix" data-offset-top="124">

	<ul class="nav navbar-nav" data-hover="dropdown"
		data-animations="jello fadeInRight fadeInUp fadeInLeft">
		<li id="home"><a id="A_home" href="${contextPath}/index"><span
				class="glyphicon glyphicon-home"></span> Home</a></li>
		<sec:authorize access="!hasRole('ROLE_ADMIN')">
			<li id="category"><a href="${contextPath}/allProducts"
				id="A_category">Home & Furniture</a></li>

			<li class="dropdown" id="MenuHoverCategory"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown" role="button"
				aria-expanded="false">Categories <span class="caret"></span>
			</a>
		</sec:authorize>
		<ul class="dropdown-menu dropdownhover-bottom" role="menu">
			<c:forEach items="${categoryList}" var="category">
				<li><a style="font-size: 20px;"
					href="${contextPath}/allProducts/${category.categoryId}">${category.categoryName}</a></li>
			</c:forEach>
		</ul>
		</li>



	</ul>
	<ul class="nav navbar-nav navbar-right" data-hover="dropdown"
		data-animations="jello fadeInRight fadeInUp fadeInLeft">
		<sec:authorize access="hasRole('ROLE_USER')">

			<li id="viewCart"><a id="A_viewCart"
				href="${contextPath}/user/cart/"><span class="fa fa-cart-plus"></span>
					Cart <span class="badge">${noOfProducts}</span></a></li>

		</sec:authorize>



		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<c:if test="${pageContext.request.userPrincipal.name != null }">
				<li class="dropdown" id="adminHoverMenu"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					role="button" aria-expanded="false"> <span
						class="glyphicon glyphicon-user"></span>
						${pageContext.request.userPrincipal.name} <span class="caret"></span></a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu">
						<li><a style="font-size: 20px;"
							href="${contextPath}/admin/viewProducts">Product</a></li>
						<li><a style="font-size: 20px;"
							href="${contextPath}/admin/viewCategory">Category</a></li>
						<li><a style="font-size: 20px;"
							href="${contextPath}/admin/viewSupplier">Supplier</a></li>
					</ul></li>
			</c:if>
		</sec:authorize>



		<sec:authorize access="hasRole('ROLE_USER')">
			<c:if test="${ pageContext.request.userPrincipal.name != null }">
				<li><a href="javascript:void(0)"><span
						class="glyphicon glyphicon-user"></span>
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
