'use strict';

/*global app:true*/

app.controller('ToonVakantieCtrl', function ($scope, $routeParams, Vakantie, Auth) {

  $scope.vakantie = Vakantie.get($routeParams.vakantieId);
  $scope.signedIn = Auth.signedIn;
  $scope.comments = Vakantie.getComments($routeParams.vakantieId);
  $scope.comment = {};
  $scope.user = Auth.user;
  $scope.filter = 'false';
  $scope.error = 'Gebruik gepaste taal!';
  $scope.verwijderVakantie = function() {
    return Vakantie.delete($scope.vakantie);
  };

    $scope.voegCommentToe = function() {
   	$scope.comment.creator = $scope.user.profile.username;
   	if(!filter($scope.comment))
   	{
  		Vakantie.comment($scope.comment, $routeParams.vakantieId);
  		$scope.filter='false';
  	}	
  	else
  	{
  		$scope.filter='true';
  	}
  };

  $scope.admin = function(role){
    return role === '99';
    };

  function filter(comment)
  {
  	var subcomment = comment.tekst.split(' ');
  	var notAllowedWords = ['anal','anus','arse','ass','ballsack','balls','bastard','bitch','biatch','bloody','blowjob','bollock','bollok','boner','boob','bugger','bum','butt','buttplug','clitoris','cock','coon','crap','cunt','damn','dick','dildo','dyke','fag','feck','fellate','fellatio','felching','fuck','fudgepacker','fudge packer','flange','Goddamn','hell','homo','jerk','jizz','knobend','labia','lmao','lmfao','muff','nigger','nigga','omg','penis','piss','poop','prick','pube','pussy','queer','scrotum','sex','shit','sh1t','slut','smegma','spunk','tit','tosser','turd','twat','vagina','wank','whore','wtf'];
  	var index;
  	var bool;
  	for(var i = 0; i < subcomment.length; i++)
  	{
  		for(var j = 0; j < notAllowedWords.length; j++)
  		{
  		index = subcomment[i].localeCompare(notAllowedWords[j]);
  		if(index === 0)
  		{
  			 return true;
  		}
  		else{
  			bool = false;
  		}
  	}	
  	}
  	return bool;
  }
});