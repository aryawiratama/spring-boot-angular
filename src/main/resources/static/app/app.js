'use strict';
var mainApp = angular.module('mainApp', ['ui.router', 'ui.bootstrap', 'mainApp.itemController', 'mainApp.loginController']);

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
            controller: 'LoginCtrl'
        };
        
        $stateProvider.state('item', item)
                .state('dashboard',dashboard)
                .state('login', login);
}]);

mainApp.controller('MainCtrl',['$scope', '$state', function($scope, $state){
        
}]);