function myTransactionsController($scope, $http) {
    $scope.state = 'busy';

    $scope.lastAction = '';

    $scope.url = "/currencyexchange/protected/orders";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displayCreateRecordButton = false;

    $scope.order = {}
    $scope.exchangeTransaction = {}



    $scope.getMyTransactionsList = function () {

        $scope.lastAction = 'myTransactions';

        var url = $scope.url +"/"+ $scope.lastAction;

        $scope.startDialogAjaxRequest();

        //    var config = {params: {page: $scope.pageToGet}};

        $http.get(url)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
            });
    }

    $scope.declineTransaction = function() {
        $scope.displaySuccessDeclineMessage = false;
        $scope.displaySuccessAcceptMessage = false;
        $scope.lastAction = 'declineTransaction';

        var url = $scope.url +"/"+ $scope.lastAction +"/"+ $scope.exchangeTransaction.transactionId;

        $scope.startDialogAjaxRequest();

        $http.get(url)
            .success(function (data) {
                $scope.displaySuccessDeclineMessage = true;
                $scope.finishAjaxCallOnSuccess(data, "#declineTransactionModal", false);
            })
            .error(function () {
                $scope.state = 'error';
            });

    }

    $scope.acceptTransaction = function() {
        $scope.displaySuccessAcceptMessage = false;
        $scope.displaySuccessDeclineMessage = false;
        $scope.lastAction = 'acceptTransaction';

        var url = $scope.url +"/"+ $scope.lastAction +"/"+ $scope.exchangeTransaction.transactionId;

        $scope.startDialogAjaxRequest();

        $http.get(url)
            .success(function (data) {
                $scope.displaySuccessAcceptMessage = true;
                $scope.finishAjaxCallOnSuccess(data, "#acceptTransactionModal", false);
            })
            .error(function () {
                $scope.state = 'error';
            });

    }

    $scope.populateTable = function (data) {

        $scope.page = {source: data.transactions};

    }

    $scope.selectedTransaction = function (exchangeTransaction) {
        var selectedTransaction = angular.copy(exchangeTransaction);
        $scope.exchangeTransaction = selectedTransaction;
    }

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
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    }
    $scope.getMyTransactionsList();

}
