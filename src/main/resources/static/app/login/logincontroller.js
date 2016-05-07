'use strict';
var mainApp = angular.module('mainApp.loginController',['mainApp.loginService']);

mainApp.controller('LoginCtrl', ['$scope', '$state', 'LoginService', function($scope, $state, Login){
    var authenticate = function(credentials){
        //var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
        var headers = credentials ? {authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)} : {authorization : "tes"};
        
        var user = Login.getUser(headers).get();
        if(user.name){
            return true;
        }else{
            return false;
        }
    };
    
    $scope.login = function(){
        var authenticated = authenticate($scope.credentials);
        if(authenticated){
            $state.go("/");
            $scope.error = false;
        }else{
            $state.go("login");
            $scope.error = true;
        }
    };
}]);