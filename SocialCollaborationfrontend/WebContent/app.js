var app = angular.module('myApp', ['ngRoute','ngCookies']);

/*app.controller('HomeController', function($scope) {
    $scope.message = "Hello from HomeController";
});*/

/*Starting of UserController*/
app.config(function($routeProvider){
	$routeProvider
	
	.when('/', {
		templateUrl : 'c_home/home.html' ,
		controller : 'HomeController'
		
	})
	
	.when('/login', {
		templateUrl : 'c_user/login.html' ,
		controller : 'UserController'
	})
	
	.when('/register', {
		templateUrl : 'c_user/register.html' ,
		controller : 'UserController'
	})
	
	//Blog related mapping
	
	.when('/create_blog', {
		templateUrl : 'c_blog/create_blog.html' ,
		controller : 'BlogController'
	})
	.when('/list_blog', {
		templateUrl : 'c_blog/list_blog.html' ,
		controller : 'BlogController'
	})
	.when('/view_blog', {
		templateUrl : 'c_blog/view_blog.html' ,
		controller : 'BlogController'
	})
	
	//Chat mapping
	.when('/chat', {
		templateUrl : 'c_chat/chat.html' ,
		controller : 'ChatController'
	})
	
	
	.when('/logout', {
		templateUrl : 'c_user/home.html' ,
		controller : 'UserController'
	})
	
	//Friend related mapping
	.when('/add_friend', {
		templateUrl : 'c_friend/add_friend.html' ,
		controller : 'FriendController'
	})
	
	.when('/view_friend', {
		templateUrl : 'c_friend/view_friend.html' ,
		controller : 'FriendController'
	})
	
	.when('/search_friend', {
		templateUrl : 'c_friend/search_friend.html' ,
		controller : 'FriendController'
	})
	
	
	.otherwise({redirectTo: '/'});
});

app.run(function ($rootScope, $location, $cookieStore, $http){

	$rootScope.$on('$locationChangeStart', function(event, next, current){
	console.log("$locationChangeStart")
	//redirect to login page if not logged in and typing to access a restricted page

	var restrictedPage=$.inArray($location.path(), ['/','/login','/register']) ===-1;
	console.log("restrictedPage:" +restrictedPage)
	var loggedIn=$rootScope.currentUser.username;
	console.log("loggedIn:"+loggedIn)
	if(restrictedPage & !loggedIn){
	console.log("Navigating to login page:")
	alert("You are not logged and so you can't do this operation")
	$location.path('/login');
	}
	});

	//keep user logged in after page refresh
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if($rootScope.currentUser){
	$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser;
	
	}
	});