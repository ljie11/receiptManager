(function() {
    'use strict';
    angular
        .module('receiptManagerApp')
        .factory('Receipt', Receipt);

    Receipt.$inject = ['$resource', 'DateUtils'];

    function Receipt ($resource, DateUtils) {
        var resourceUrl =  'api/receipts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.created_date = DateUtils.convertDateTimeFromServer(data.created_date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
