'use strict';
var mainApp = angular.module('mainApp.loginController',['mainApp.loginService']);

mainApp.controller('LoginCtrl', ['$scope', '$state', 'LoginService', function($scope, $state, Login){
    
    $scope.login = function(){
        var headers = $scope.credentials ? {authorization : "Basic " + btoa($scope.credentials.username + ":" + $scope.credentials.password)} : {};
        
        Login.getUser(headers).get().$promise.then(function(data){
            if(data.name){
                $state.go("dashboard");
                $scope.error = false;
            }else{
                $scope.error = true;
            }
        });
    };
}]);