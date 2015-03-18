function adminController($scope, $http) {

    $scope.state = "";
    $scope.url = "/CurrencyExchange/protected/adminPage";


    $scope.getUserList = function(){
        var url = $scope.url;
        $scope.startDialogAjaxRequest();
        $http.get(url)
            .success (function (data){
            $scope.finishAjaxCallOnSuccess(data, null);
            })
            .error(function () {
            $scope.state = 'error';
            });
    }

    $scope.populateTable = function (data) {

        $scope.page = {source: data.users};

    }

    $scope.selectedUser = function (user) {
        var selectedUser = angular.copy(user);
        $scope.user = selectedUser;
    }

    $scope.deleteUser = function () {
        $scope.lastAction = 'delete';

        var url = $scope.url +"/"+  $scope.lastAction +"/"+ $scope.user.id;

        $scope.startDialogAjaxRequest();

        $http({
            method: 'DELETE',
            url: url
        }).success(function (data) {
           //     $scope.resetRecord();
                $scope.finishAjaxCallOnSuccess(data, "#deleteUsersModal");
            }).error(function(data, status) {
                $scope.handleErrorInDialogs(status);
            });

    };

    $scope.finishAjaxCallOnSuccess = function (data, modalId) {
        $scope.populateTable(data);
        $("#loadingModal").modal('hide');

            if(modalId){
                $scope.exit(modalId);
            }

        $scope.lastAction = '';
    }

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');
    }

 $scope.getUserList();

}
