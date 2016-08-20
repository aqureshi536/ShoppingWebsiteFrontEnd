var myApp = angular.module("angularModule",[]);

myApp.controller('allProducts',function($scope,$http){
	$http.get('http://localhost:8080/ShoppingWebsite/product/all')
		.success(function(data){
			$scope.listOfProducts=data;
		});
});

myApp.controller('productDetail',function($scope,$http){
	$http.get('http://localhost:8080/ShoppingWebsite/product/productDetail/{productId}')
		.success(function(data){
			$scope.product=data;
		});
});
