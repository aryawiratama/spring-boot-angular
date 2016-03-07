var itemApp = angular.module('itemApp',[]);

itemApp.controller('itemListController', function($scope){
    $scope.items = [
        {'code':'F-00000001', 'name':'Jus Buah Naga', 'price':'10000', 'cost':'6000', 'stock':'10', 'expiredDate':'2016-03-30'}
    ];
});