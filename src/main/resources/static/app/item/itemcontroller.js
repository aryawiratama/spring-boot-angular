'use strict';
var mainApp = angular.module('mainApp.itemController',['ngAnimate', 'ng-currency', 'mainApp.itemService']);

mainApp.controller('ItemCtrl', ['$scope', '$uibModal', '$state', 'ItemService', function($scope, $uibModal, $state, ItemService){
    $scope.items = ItemService.query();
    
    $scope.animationsEnabled = true;
    
    // open Add Modal
    $scope.openAddModal = function(){
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'modalContent.html',
            controller: 'ModalAddCtrl'
        });
        
        modalInstance.result.then(function(){
            // oke
            $state.reload();
        }, function(){
            // cancel modal
        });
    };
    // open edit modal
    $scope.openEditModal = function(item){
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'modalContent.html',
            controller: 'ModalEditCtrl',
            resolve: {
                item: function(){
                    return item;
                }
            }
        });
        
        modalInstance.result.then(function(){
            //oke
            $state.reload();
        }, function (){
                    //cancel modal
        });
    };
    
    // show delete confirm modal
    $scope.deleteConfirm = function(item){
        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'deleteConfirm.html',
            controller: 'ModalDeleteCtrl',
            resolve:{
                item: function(){
                    return item;
                }
            }
        });
        
        modalInstance.result.then(function(){
            //oke
            $state.reload();
        }, function (){
                    //cancel modal
        });
    };
    
    $scope.toggleAnimation = function(){
        $scope.animationsEnabled = !$scope.animationsEnabled;
    };
}]);

// modal add item controller
mainApp.controller('ModalAddCtrl', ['$scope', '$uibModalInstance', 'ItemService', function($scope, $uibModalInstance, ItemService){        
    
    $scope.clear = $scope.expiredDate = null;
    
    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 12, 31),
        minDate: new Date(),
        startingDay: 1
    };
    // disabled weekend selection
    function disabled(data){
        var date = data.date, mode = data.mode;
        return mode === 'day' && (date.getDay()===0 || date.getDay()===6);
    };
    
    $scope.open = function(){
        $scope.popup.opened = true;
    };
    $scope.popup = {
        opened: false
    };
    
    $scope.altInputFormats = ['d!/M!/yyyy!'];
    
    $scope.save = function(){
        if($scope.form.$valid){
            var item = new ItemService();
            item.code = null;
            item.name = $scope.name;
            item.price = $scope.price;
            item.cost = $scope.cost;
            item.stock = $scope.stock;
            item.expiredDate = $scope.expiredDate;
            item.$save(function(){
                $uibModalInstance.close();
            });
        }
    };
    
    $scope.cancel = function (){
        $uibModalInstance.dismiss('cancel');
    };
}]);

// modal edit controller
mainApp.controller('ModalEditCtrl',['$scope', '$uibModalInstance', 'ItemService', 'item', function($scope, $uibModalInstance, ItemService, item){
    $scope.clear = $scope.expiredDate = null;
    
    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 12, 31),
        minDate: new Date(),
        startingDay: 1
    };
    // disabled weekend selection
    function disabled(data){
        var date = data.date, mode = data.mode;
        return mode === 'day' && (date.getDay()===0 || date.getDay()===6);
    };
    
    $scope.open = function(){
        $scope.popup.opened = true;
    };
    $scope.popup = {
        opened: false
    };
    
    $scope.altInputFormats = ['d!/M!/yyyy!'];
    
    $scope.code = item.code;
    $scope.name = item.name;
    $scope.price = item.price;
    $scope.cost = item.cost;
    $scope.stock = item.stock;
    $scope.expiredDate = item.expiredDate;
    
    $scope.save = function(){
        if($scope.form.$valid){
            var editItem = ItemService.get({id:item.id});
            editItem.code = $scope.code;
            editItem.name = $scope.name;
            editItem.price = $scope.price;
            editItem.cost = $scope.cost;
            editItem.stock = $scope.stock;
            editItem.expiredDate = $scope.expiredDate;
            ItemService.update({id:item.id}, editItem)
                    .$promise.then(function(){
                        $uibModalInstance.close();
                    });
        }
    };
    
    $scope.cancel = function (){
        $uibModalInstance.dismiss('cancel');
    };
}]);

// delete modal controler
mainApp.controller('ModalDeleteCtrl',['$scope', '$uibModalInstance', 'ItemService', 'item', function($scope, $uibModalInstance, ItemService, item){
    $scope.itemCode = item.code;
    $scope.yes = function(){
        ItemService.delete({id:item.id}, function(){
            $uibModalInstance.close();
        });
    };
    $scope.no = function(){
        $uibModalInstance.dismiss('no');
    };
}]);