/// <reference path="../js/angular.js"/>
var app=angular.module("homeModule",[])
			 // .config(function($routeProvider,$locationProvider)
				// {
				// 	$routeProvider.
				// 	when("ClassWork/pagesforproject/home",{
				// 		templateUrl:"index.html",
				// 		controller:"productController"
				// 	})
				// 	when("/login",{
				// 		templateUrl:"login.html",
				// 		controller:"loginController"
				// 	})
				// 	$locationProvider.html5Mode(true);
				// });
			  app .controller("productController",function($scope)
					{
						var beds=
						[{"name":"Bed 001","brand":"Johnson","price":"15000","image":"beds-001.jpg","alt":"beds-001"},
						{"name":"Bed 002","brand":"Past","price":"15003","image":"beds-002.jpg","alt":"beds-002"},
						{"name":"Bed 003","brand":"Futura","price":"150334","image":"beds-003.jpg","alt":"beds-003"}
						];
						$scope.beds=beds;


						var sofas=
						[{"name":"Sofa 001","brand":"Johnson","price":"15000","image":"sofa-001.jpg","alt":"sofa-001"},
						{"name":"Sofa 002","brand":"Past","price":"15003","image":"sofa-002.jpg","alt":"sofa-002"}
						];
						$scope.sofas=sofas;
					});
			 /* app.controller("loginController",function($scope)
			   {
					$scope.message="Login Page";
			   });
				*/



