<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Side Bar For All pages  -->


	

		
	<div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sidebar" id="sideBarToggle">
      <span><b>Options</b></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
       
    </div>
		
    <div class="collapse navbar-collapse" id="sidebar">
		
		<ul id="sideMenu" class="nav nav-pills nav-stacked">

	

			
			<sec:authorize access="hasRole('ROLE_USER')">
				<c:if test="${displayCart == true }">
					<li id="viewCart"><a href="${contextPath}/cart/"><span class="fa fa-cart-plus"></span> Cart <span class="badge">${noOfProducts}</span></a></li>
				</c:if>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
					<c:if test="${displayAdminAction == true}"> 
				<li id="viewProducts"><a
					href="${contextPath}/admin/viewProducts"><span
						class="glyphicon glyphicon-hdd"></span> Product</a></li>
				<li id="viewCategory"><a
					href="${contextPath}/admin/viewCategory"><span
						class="glyphicon glyphicon-tasks"></span> Category</a></li>
				<li id="viewSupplier"><a
					href="${contextPath}/admin/viewSupplier"><span
						class="fa fa-users"></span> Supplier</a></li>
				</c:if> 
			</sec:authorize>

		</ul>
		</div>
		
	
	