'use strict';
var mainApp = angular.module('mainApp', ['ui.router', 'ui.bootstrap', 'mainApp.itemController', 'mainApp.loginService']);

mainApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/"); // set default url
        var item = {
            url: '/item',
            templateUrl: '/app/item/item.html',
            controller: 'ItemCtrl'
        };       
        
        var dashboard = {
            url: '/',
            templateUrl: '/app/dashboard/dashboard.html'
        };
        
        var login = {
            url:'/login',
            templateUrl: '/app/login/login.html',
            controller: 'MainCtrl'
        };
        
        $stateProvider.state('item', item)
                .state('dashboard',dashboard)
                .state('login', login);
}]);

mainApp.controller('MainCtrl',['$scope', '$state', 'LoginService', function($scope, $state, Login){
    
    $scope.login = function(){
        var headers = $scope.credentials ? {authorization : "Basic " + btoa($scope.credentials.username + ":" + $scope.credentials.password)} : {};
        
        Login.getUser(headers).get().$promise.then(function(data){
            if(data.name){
                $state.go("dashboard");
                $scope.error = false;
                $scope.authenticated = true;
            }else{
                $scope.error = true;
                $scope.authenticated = false;
            }
        });
    };
}]);