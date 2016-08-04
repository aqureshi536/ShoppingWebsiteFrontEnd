
<!-- Side Bar For All pages  -->


<ul id="sideMenu" class="nav nav-pills nav-stacked">
	<li id="home"><a href="${contextPath}/index"><span
			class="glyphicon glyphicon-home"></span> Home</a></li>

	<c:if test="${pageContext.request.userPrincipal.name == null }">
		<li id="login"><a href="${contextPath}/login"><span
				class="glyphicon glyphicon-log-in"></span> Login</a></li>
	</c:if>
	<c:if test="${pageBeforeAdminAction==true && pageContext.request.userPrincipal.name != null }">
		<li><a href="${contextPath}/admin/viewProducts"><span
				class="glyphicon glyphicon-user"></span> Admin</a></li>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != null }">
		<li ><a href="${contextPath}/login"><span
				class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	</c:if>

	<c:if test="${displayCart == true }">
		<li><a href=""><span class="fa fa-cart-plus"></span> Cart</a></li>
	</c:if>

	<c:if test="${displayAdminAction == true}">
		<li id="viewProducts"><a href="${contextPath}/admin/viewProducts"><span
				class="glyphicon glyphicon-hdd"></span> Product</a></li>
		<li id="viewCategory"><a href="${contextPath}/admin/viewCategory"><span
				class="glyphicon glyphicon-tasks"></span> Category</a></li>
		<li id="viewSupplier"><a href="${contextPath}/admin/viewSupplier"><span
				class="fa fa-users"></span> Supplier</a></li>
	</c:if>


</ul>
