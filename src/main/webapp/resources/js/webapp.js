//SideBar
$(function() {
	switch (activeMenu) {
	
	case 'logout':
		$('#logout').addClass('sidebar-active')
		// alert(activeMenu);
		break;
	case 'adminProducts':
		$('#viewProducts').addClass('sidebar-active')
		// alert(activeMenu);
		break;
	case 'adminCategory':
		$('#viewCategory').addClass('sidebar-active');
		// alert(activeMenu);
		break;
	case 'adminSupplier':
		$('#viewSupplier').addClass('sidebar-active');
		// alert(activeMenu);
		break;
	case 'viewCart':
	$('#viewCart').addClass('sidebar-active');
	break;
	
	}
});

// NavBar
$(function() {
	switch (activeNavMenu) {
	case 'category':
		$('#category').addClass('nav-active');
		$('#A_category').css("color", "black");
		// alert(activeNavMenu);
		break;
	case 'login':
		$('#login').addClass('nav-active');
		$('#A_login').css("color", "black");
		// alert(activeNavMenu);
		break;
	case 'viewCart':
		$('#viewCart').addClass('nav-active');
		$('#A_viewCart').css("color", "black");
		// alert(activeNavMenu);
		break;
	default:
		$('#home').addClass('nav-active');
	$('#A_home').css("color", "black");
		// alert(activeMenu);
		break;
	}
});

// To disappear on delete message
$(function() {
	$("#message-danger").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});

// To disappear the success message on addition
$(function() {
	$("#message-success").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});
//To disappear the update message on addition
$(function() {
	$("#ProductUpdated-warning").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});

$(function() {
	$("#messageDelete-success").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});

$(function() {
	$("#messageDelete-danger").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});
$(function() {
	$("#addedSupplierMessage").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});
$(function() {
	$("#deletedSupplierMessage").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});
$(function() {
	$("#updatedSupplierMessage").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});

$(function() {
	$("#categoryUpdateMessage").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});

$(function() {
	$("#cartItemRemoved").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});
$(function() {
	$("#addToCartSuccessMessage").fadeTo(4000, 500).slideUp(500, function() {
		$("#success-alert").alert('close');
	});
});

/*//to make navbar scroll smoother
$('#homeNavbar').affix({
    offset: $('#homeNavbar').position()
});
*/




/*//To confirm delete or not
document.getElementById("confirmDelete").addEventListener("click",
		function(e) { // e => event
			if (!confirm("Do you really want to do this?")) {
				e.preventDefault(); // ! => don't want to do this
			} else {
				// want to do this! => maybe do something about it?
				window.location.href = "http://www.google.co.in";
			}
		});
*/