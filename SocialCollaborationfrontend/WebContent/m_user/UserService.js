'use strict';

app.factory('UserService', ['$http', '$q', '$rootScope', function($http, $q,$rootScope){
	console.log("UserService...")
	
	var BASE_URL= 'http://localhost:8080/CollaborationBack';
		return {
		 fetchAllUsers: function() {
			 return $http.get(Base_URL+'/users')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching UsersDetailss');
						return $q.reject(errResponse);
					}
			
			
			
			); 
		 },
		 
		 createUsers: function(user) {
			 return $http.get(Base_URL+'/user/', user)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while creating user');
						return $q.reject(errResponse);
					}
			
			
			); 
		 },
		 
		 
		 updateUsers: function(user, id) {
			 return $http.put(Base_URL+'/user/'+id, user)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while updating user');
						return $q.reject(errResponse);
					}
			
			
			
			); 
		 },
		 
		
		 deleteUsers: function(id) {
			 return $http.get(Base_URL+'/user/'+id)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while deleting users');
						return $q.reject(errResponse);
					}
			
			
			
			); 
		 },
		 
	
	
	
	
	                       
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            ]
)