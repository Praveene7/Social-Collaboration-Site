var app = angular.module('myApp', ['ngRoute']);

/*app.controller('HomeController', function($scope) {
    $scope.message = "Hello from HomeController";
});*/

/*Starting of UserController*/
app.config(function($routeProvider){
	$routeProvider
	
	.when('/', {
		templateUrl : 'index.html' ,
		
	})
	
	.when('/login', {
		templateUrl : 'c_user/login.html' ,
		controller : 'UserController'
	})
	
	.when('/register', {
		templateUrl : 'c_user/register.html' ,
		controller : 'UserController'
	})
	
	.when('/friend', {
		templateUrl : 'c_friend/view_friend.html' ,
		controller : 'FriendController'
	})
	
	
	.otherwise({redirectTo: '/'});
});