var mainApp = angular.module('mainApp.itemController',['ngAnimate', 'ng-currency', 'mainApp.itemService']);

mainApp.controller('ItemCtrl', ['$scope', '$uibModal', '$log', '$state', 'ItemService', function($scope, $uibModal, $log, $state, ItemService){
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
            $log.info('Modal dismissed at : ' + new Date());
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
    $scope.save = function(){
        var item = new ItemService();
        item.code = $scope.code;
        item.name = $scope.name;
        item.price = $scope.price;
        item.cost = $scope.cost;
        item.stock = $scope.stock;
        item.expiredDate = $scope.expiredDate;
        item.$save();
        $uibModalInstance.close();
    };
    
    $scope.cancel = function (){
        $uibModalInstance.dismiss('cancel');
    };
}]);

// modal edit controller
mainApp.controller('ModalEditCtrl',['$scope', '$uibModalInstance', 'ItemService', 'item', function($scope, $uibModalInstance, ItemService, item){
    $scope.code = item.code;
    $scope.name = item.name;
    $scope.price = item.price;
    $scope.cost = item.cost;
    $scope.stock = item.stock;
    $scope.expiredDate = item.expiredDate;
    
    $scope.save = function(){
        var editItem = ItemService.get({id:item.id}, function(){
            editItem.code = $scope.code;
            editItem.name = $scope.name;
            editItem.price = $scope.price;
            editItem.cost = $scope.cost;
            editItem.stock = $scope.stock;
            editItem.expiredDate = $scope.expiredDate;
            editItem.$save(function(){
                $uibModalInstance.close();
            });
        });
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