(function() {
    'use strict';

    angular
        .module('receiptManagerApp')
        .controller('ReceiptDialogController', ReceiptDialogController);

    ReceiptDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Receipt'];

    function ReceiptDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Receipt) {
        var vm = this;

        vm.receipt = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.receipt.id !== null) {
                Receipt.update(vm.receipt, onSaveSuccess, onSaveError);
            } else {
                Receipt.save(vm.receipt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('receiptManagerApp:receiptUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.created_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
