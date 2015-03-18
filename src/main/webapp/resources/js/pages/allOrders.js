function allOrdersController($scope, $http) {
    $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.lastAction = '';

    $scope.url = "/CurrencyExchange/protected/orders";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displayCreateRecordButton = false;

    $scope.order = {}
    $scope.exchangeTransaction = {}



    $scope.getAllOrderList = function () {

        $scope.lastAction = 'allOrders';

        var url = $scope.url +"/"+ $scope.lastAction;

        $scope.startDialogAjaxRequest();

        $http.get(url)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
            });

    }


    $scope.populateTable = function (data) {

        $scope.page = {source: data.orders};

    }

    $scope.resetOrder = function(){
        $scope.order = {};
    };


    $scope.selectedOrder = function (order) {
        var selectedOrder = angular.copy(order);
        $scope.order = selectedOrder;
    }


    $scope.createTransaction = function (addTransactionForm) {
        if (!addTransactionForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }
        $scope.displayTransactionErrorMessage = false;
        $scope.displayTransactionSuccessMessage = false;
        $scope.lastAction = 'addTransaction';

        var url = $scope.url +"/"+ $scope.lastAction +"/"+ $scope.order.id;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        if ($scope.order.amount < $scope.exchangeTransaction.transactionAmount) {
            $scope.displayTransactionErrorMessage = true;
        } else {
        $scope.startDialogAjaxRequest();
        $http.post(url, $.param($scope.exchangeTransaction), config)
            .success(function (data) {
                $scope.displayTransactionSuccessMessage = true;
                $scope.finishAjaxCallOnSuccess(data, "#addTransactionModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
        }
    };


    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
        $scope.populateTable(data);
           $("#loadingModal").modal('hide');

        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }

        $scope.lastAction = '';
    }

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.order = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
    }

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    }
    $scope.getAllOrderList();

}

