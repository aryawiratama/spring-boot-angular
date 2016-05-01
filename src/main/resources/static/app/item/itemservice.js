var mainApp = angular.module('mainApp.itemService',['ngResource']);

mainApp.factory('ItemService',['$resource', function($resource){
        return $resource('api/items/:id',{}, {
            'update': {method:'PUT'}
        });
}]);