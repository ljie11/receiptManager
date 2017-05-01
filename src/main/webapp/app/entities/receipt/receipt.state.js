(function() {
    'use strict';

    angular
        .module('receiptManagerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('receipt', {
            parent: 'entity',
            url: '/receipt?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Receipts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/receipt/receipts.html',
                    controller: 'ReceiptController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('receipt-detail', {
            parent: 'entity',
            url: '/receipt/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Receipt'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/receipt/receipt-detail.html',
                    controller: 'ReceiptDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Receipt', function($stateParams, Receipt) {
                    return Receipt.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'receipt',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('receipt-detail.edit', {
            parent: 'receipt-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receipt/receipt-dialog.html',
                    controller: 'ReceiptDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Receipt', function(Receipt) {
                            return Receipt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('receipt.new', {
            parent: 'receipt',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receipt/receipt-dialog.html',
                    controller: 'ReceiptDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                category: null,
                                created_by: null,
                                created_date: null,
                                total: null,
                                claimed_amount: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('receipt', null, { reload: 'receipt' });
                }, function() {
                    $state.go('receipt');
                });
            }]
        })
        .state('receipt.edit', {
            parent: 'receipt',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receipt/receipt-dialog.html',
                    controller: 'ReceiptDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Receipt', function(Receipt) {
                            return Receipt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('receipt', null, { reload: 'receipt' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('receipt.delete', {
            parent: 'receipt',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receipt/receipt-delete-dialog.html',
                    controller: 'ReceiptDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Receipt', function(Receipt) {
                            return Receipt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('receipt', null, { reload: 'receipt' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
