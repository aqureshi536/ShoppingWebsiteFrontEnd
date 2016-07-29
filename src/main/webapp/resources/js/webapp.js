//SideBar
$(function(){
	switch (activeMenu) {
	case 'login':
		$('#login').addClass('sidebar-active')
//		alert(activeMenu);
		break;
	case 'logout':	
		$('#logout').addClass('sidebar-active')
//		alert(activeMenu);
		break;
	case 'adminProducts':	
		$('#viewProductsAdmin').addClass('sidebar-active')
//		alert(activeMenu);
		break;
	case 'adminCategory':
		$('#viewCategoryAdmin').addClass('sidebar-active');
//		alert(activeMenu);
		break;
	case 'adminSupplier':
		$('#viewSupplierAdmin').addClass('sidebar-active');
//		alert(activeMenu);
		break;
	default:
		$('#home').addClass('sidebar-active')
		//alert(activeMenu);
		break;
	}
});

//NavBar
$(function(){
	switch(activeNavMenu)
	{
	case 'viewAllProducts':
		$('#viewAllProducts').addClass('nav-active');
		$('#A_viewAllProducts').css("color","black");
		//alert(activeNavMenu);
		break;
	}
});



	
