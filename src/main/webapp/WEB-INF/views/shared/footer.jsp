<hr />
<footer>
	<div>
		<span>&#169;</span> All Rights Reserved
	</div>
</footer>

<!-- <script type="text/javascript"> -->
<!-- // 	(function($) {
// 		$("#fakeLoader").fakeLoader();
// 	}(jQuery)); -->
<!-- </script> -->
<script>
(function ($) {
$("#fakeLoader").fakeLoader({ 
timeToHide:1200, 
zIndex:9999, 
spinner:"spinner6", 
bgColor:"#a01414" 
});
}(jQuery));
</script>
<%-- <script src="${js}/fakeLoaderOptional.js"></script> --%>
<script>
	window.activeMenu = '${active}';
</script>
<script>
	window.activeNavMenu = '${activeNavMenu}'
</script>
<script>
	function myhref(web) {
		window.location.href = web;
	}
</script>



<!-- Submits the  form -->
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

<script src="${js}/jquery-confirm.js"></script>
<script src="${js}/jquery.validate.js"></script>
<script src="${js}/webapp.js"></script>
<script src="${js}/bootstrap.js"></script>
<script src="${js}/bootstrap-dropdownhover.js"></script>

<script src="${js}/main.js"></script>
