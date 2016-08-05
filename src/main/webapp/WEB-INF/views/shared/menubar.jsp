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



	<button type="button" class="navbar-toggle"  data-toggle="collapse"
		data-target="#homeNavbar" id="homenavbarToggle" >
		<span><b>Categories</b></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
	</button>
</div>
<div style="clear:both"></div>
<div class="navbar navbar-inverse collapse navbar-collapse"
	id="homeNavbar" data-spy="affix" data-offset-top="124">

	<ul class="nav navbar-nav">
		<li id="viewAllProducts"><a href="${contextPath}/allProducts"
			id="A_viewAllProducts">View all products</a></li>
		<li><a href="">Curtains</a></li>
		<li><a href="">Sofa</a></li>
		<li><a href="">Bed</a></li>
		<li><a href="">Table</a></li>
		<li><a href="">Cabinet</a></li>
		<li><a href="">Dining Table</a></li>
		<li><a href="">Cupboards</a></li>
		<li><a href="">Chair</a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right">

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<c:if
				test="${pageBeforeAdminAction==true && pageContext.request.userPrincipal.name != null }">
				<li><a href="${contextPath}/admin/viewProducts"><span
						class="glyphicon glyphicon-user"></span>
						${pageContext.request.userPrincipal.name}</a></li>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
			<c:if test="${ pageContext.request.userPrincipal.name != null }">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						${pageContext.request.userPrincipal.name}</a></li>
			</c:if>
		</sec:authorize>

	</ul>
</div>
