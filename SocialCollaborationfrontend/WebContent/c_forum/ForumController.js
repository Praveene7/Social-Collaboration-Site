'use strict';
console.log(" start of forumController...")
app.controller('BlogController',['$scope','ForumService','$location','$rootScope','$http',
						function($scope, ForumService, $location,$routeParams, $rootScope,$http) {
							console.log("ForumController...")
							var self = this;
							self.blog = {
								userid : '',
								id : '',
								title: '',
								description: '',
								status : '',
								reason : '',
								posted_dt : '',
								errorCode : '',
								errorMessage : ''
							};
							self.forums = [];

							self.fetchAllForums = function() {
								console.log("fetchAllForums...")
								BlogService.fetchAllForums()
										.then(function(d) {
													self.forums = d;
													console.log("Got the forum list")
												},
												function(errResponse) {
													console.error('Error while fetching Forums');
												});
							};
							
							

							self.createForum = function(Forum) {
								console.log("createForum...")
								ForumService.createForum(Forum)
										.then(
												self.fetchAllForums,
												function(errResponse) {
													console
															.error('Error while creating Forum.');
												});
							};
							
							
							self.submit = function() {
								{
									console.log('Create new forum', self.Forum);
									self.createForum(self.Forum);
								}
								self.reset();
							};

							self.reset = function() {
								self.Forum = {
										userid : '',
										id : '',
										title: '',
										description: '',
										status : '',
										reason : '',
										errorCode : '',
										errorMessage : ''
					};
								$scope.myForm.$setPristine(); // reset Form
							};
							
							self.fetchAllForums();
				          

						} ]);