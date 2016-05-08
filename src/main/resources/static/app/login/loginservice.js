'use strict';
var mainApp = angular.module('mainApp.loginService',['ngResource']);

mainApp.factory("LoginService", ['$resource', function($resource){
    var service = {
        getUser : function(headerParam){
            var user = $resource('api/user',{},{
                get:{
                    method : 'GET',
                    headers : headerParam
                } 
            });
            return user;
        },
        user : function(){
            var logout = $resource('logout',{},{
                logout:{
                    method: 'POST'
                }
            });
            return logout;
        }
    };
    return service;
}]);