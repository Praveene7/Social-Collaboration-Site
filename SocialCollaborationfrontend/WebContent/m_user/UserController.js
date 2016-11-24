'use strict';

app.controller('UserController', ['$scope', 'UserService', function($scope,UserService) {
	console.log("UserController...")
	var self = this;
	self.user={id:'',name:'',password:'',mobile:'',address:'',email:'',role:''};
	self.user=[];
	
	self.fetchAllUsers = function(){
		UserService.fetchAllUsers()
		.then(
				   function(d) {
					   self.users = d;
				   },
				   function(errResponse){
					  console.error('Error while fetching Users') 
				   }
	);
};
	
self.createUsers = function(user){
	UserService.createUsers()
	.then(
			   
				   self.fetchAllusers;
			   
			   function(errResponse){
				  console.error('Error while creating Users') 
			   }
);
	
};

self.updateUsers = function(user,id){
	UserService.updateUsers()
	.then(
			   
				   self.fetchAllusers;
			   
			   function(errResponse){
				  console.error('Error while updating Users') 
			   }
);
	
};

self.deleteUsers = function(id){
	UserService.deleteUsers()
	.then(
			   
				   self.fetchAllusers;
			   
			   function(errResponse){
				  console.error('Error while deleting Users') 
			   }
);
};

self.fetchAllUsers();

self.submit = function() {
	if(self.user.id===null){
	console.log('Saving New Users', self.user);
	self.createUser(self.user);
	}else{
		/* self.updateUser(self.user, self.user.id);
		 console.log('User updated with id ', self.user.id);*/
		console.log('Saving New User', self.user);
		self.createUser(self.user);
	}
	self.reset();
};

self.edit = function(id){
	console.log('id to be edited', id);
	for(var i= 0; i < self.users.length; i++){
		if(self.users[i].id === id){
			self.user = angular.copy(self.users[i]);
			break;
		}
	}
};

self.remove = function(id){
	console.log('id to be deleted', id);
	if(self.users[i].id === id) {
		self.reset();
	}
	self.deleteUser(id);
		
		
	};
	
	self.reset = function(){
		self.user={id:'',name:'',password:'',mobile:'',address:'',email:''};
		$scope.myForm.$setPristine();
	};
		
		
	}]);


			
			
	
		
		
		
		
	