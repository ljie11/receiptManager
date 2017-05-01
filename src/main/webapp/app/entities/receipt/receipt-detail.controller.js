(function() {
    'use strict';

    angular
        .module('receiptManagerApp')
        .controller('ReceiptDetailController', ReceiptDetailController);

    ReceiptDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Receipt'];

    function ReceiptDetailController($scope, $rootScope, $stateParams, previousState, entity, Receipt) {
        var vm = this;

        vm.receipt = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('receiptManagerApp:receiptUpdate', function(event, result) {
            vm.receipt = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
