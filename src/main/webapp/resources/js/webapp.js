//SideBar
$(function() {
	switch (activeMenu) {
	case 'login':
		$('#login').addClass('sidebar-active')
		// alert(activeMenu);
		break;
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
	default:
		$('#home').addClass('sidebar-active')
		// alert(activeMenu);
		break;
	}
});

// NavBar
$(function() {
	switch (activeNavMenu) {
	case 'viewAllProducts':
		$('#viewAllProducts').addClass('nav-active');
		$('#A_viewAllProducts').css("color", "black");
		// alert(activeNavMenu);
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


categoryUpdateMessage
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