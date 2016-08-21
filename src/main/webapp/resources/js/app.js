/// <reference path="./angular.js"/>
(function() {
	var myApp = angular.module("angularModule", []);

	// For displaying all the products
	myApp.controller('allProducts', function($scope, $http) {
		$http.get('/ShoppingWebsite/product/all').success(function(data) {
			$scope.listOfProducts = data;
		});

		/*
		 * $scope.itemsPresent=function() { if ($scope.listOfProducts.length !=
		 * 0 || $scope.listOfProducts.length != null) { $scope.hasItems = true; }
		 * else { $scope.hasItems =false ; }
		 *  };
		 */
	});

	
	
//	Not implemented
	myApp.controller('productDetail',function($scope, $http) {
				$http.get('/ShoppingWebsite/product/productDetail/{productId}')
						.success(function(data) {
							$scope.product = data;
						});
			});
//	========================================================

	myApp.controller('cartController', [ '$scope', '$http',
			function($scope, $http) {
				$http.get('/ShoppingWebsite/view/cart/').success(function(data) {
					$scope.cartItems = data;
				});
			} ]);
	
	myApp.controller('orderedItemsCtrl',function($scope, $http) {
		$http.get('/ShoppingWebsite/view/orderedItems')
				.success(function(data) {
					$scope.orderedItems = data;
				});
	});

}()); //self calling function known as closure
