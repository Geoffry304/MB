'use strict';
/*global app:true*/
app.controller('ProfileCtrl', function($scope, $routeParams, Profile) {
	var uid = $routeParams.userId;
  $scope.kind = {};
	$scope.profile = Profile.get(uid);
	

	$scope.submitProfiel = function () {
	console.log($scope.profile.username); 
	Profile.update(uid,$scope.profile);
   
  };

  $scope.submitKind = function () { 
  Profile.createKind(uid,$scope.kind);
   
  };
});