'use strict';
/*global app:true*/
app.controller('AuthCtrl', function($scope, $location, Auth, user) {
	if(user){
		$location.path('/');
	}

	$scope.login = function() {
		Auth.login($scope.user).then(function() {
			$location.path('/');
		}, function (error) {
			$scope.error = error.toString();
			console.log(error.toString());
		});
	};

	$scope.loginWithFacebook = function() {
		Auth.facebookLogin().then(function(){
			if (!Auth.user){
				Auth.createFbProfile(Auth.user);
			}
			//Auth.createFbProfile(Auth.user);
			$location.path('/');
		});
	};

	$scope.register = function() {
		Auth.register($scope.user).then(function(user) {
			return Auth.login($scope.user).then(function() {
				user.username = $scope.user.username;
				return Auth.createProfile(user);
			}).then(function() {
				$location.path('/');
			});
		}, function(error) {
			$scope.error = error.toString();
			console.log(error.toString());
		});
	};
});