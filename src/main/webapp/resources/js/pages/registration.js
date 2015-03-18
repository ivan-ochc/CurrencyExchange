function registrationController($scope, $location, $http) {

    $scope.displayValidationError = false;
    $scope.url = "/CurrencyExchange";
    $scope.displayIncorrectPasswordMessage = false;
    $scope.displaySuccessUserCreationMessage = false;

    $scope.user = {}

    var url = "" + $location.$$absUrl;
    $scope.displayUserNameError = (url.indexOf("nameError") >= 0);
    $scope.displayPasswordError = (url.indexOf("passwordError") >= 0);
//    $scope.displaySuccessMessage = (url.indexOf("success") >= 0);

    $scope.createUser = function (registrationForm){
        $scope.displayIncorrectPasswordMessage = false;
        if (!angular.equals($scope.user.password,$scope.confirmPassword)) {
            $scope.displayIncorrectPasswordMessage = true;
            console.log("Incorrect password")
            return;
        }
        if (!registrationForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }
        $scope.lastAction = 'add';
        var url = $scope.url +"/"+ $scope.lastAction;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        $http.post(url, $.param($scope.user), config)
            .success(function (data) {
            //    var url = $location.search('NameError');
                var error = ($location.search().NameError);

                if (error === true){
                    $scope.displayUserNameError = true;
                    return;
                }  else {
                $scope.displayIncorrectPasswordMessage = false;
                $scope.displaySuccessUserCreationMessage = true;
            }
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });

    }

}
