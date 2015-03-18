function myOrdersController($scope, $http) {
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


    $scope.getMyOrderList = function () {

        $scope.lastAction = 'myOrders';

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

    $scope.createOrder = function (newOrderForm) {
        if (!newOrderForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.displaySuccessOrderMessage = false;
        $scope.displaySuccessDeletingMessage = false;

        $scope.lastAction = 'addOrder';

        var url = $scope.url +"/"+ $scope.lastAction;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

          $scope.startDialogAjaxRequest();

        $http.post(url, $.param($scope.order), config)
            .success(function (data) {
                $scope.displaySuccessOrderMessage = true;
                $scope.finishAjaxCallOnSuccess(data, "#addOrdersModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };


    $scope.updateOrder = function (updateOrderForm) {
        if (!updateOrderForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'update';

        var url = $scope.url + "/" + $scope.lastAction

          $scope.startDialogAjaxRequest();

        $http.put(url, $scope.order)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#updateOrdersModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };


    $scope.deleteOrder = function () {
        $scope.displaySuccessOrderMessage = false;
        $scope.displaySuccessDeletingMessage = false;
        $scope.lastAction = 'delete';

        var url = $scope.url +"/"+  $scope.lastAction +"/"+ $scope.order.id;

           $scope.startDialogAjaxRequest();

        $http({
            method: 'DELETE',
            url: url
        }).success(function (data) {
                $scope.displaySuccessDeletingMessage = true;
                $scope.resetOrder();
                $scope.finishAjaxCallOnSuccess(data, "#deleteOrdersModal", false);
            }).error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });

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

    $scope.getMyOrderList();

}
