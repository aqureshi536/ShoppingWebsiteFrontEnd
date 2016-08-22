function verifyConfirmPassword() {
	var pass1 = document.getElementById("repwd").value;
	var pass2 = document.getElementById("reconpwd").value;

	if (pass1 != pass2) {
		alert("Both password and confirm password field should match");
		return false;
	}
	else{
		
		return true;
	}
	
}