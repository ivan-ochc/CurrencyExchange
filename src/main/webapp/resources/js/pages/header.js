function LocationController($scope, $location) {
    if($location.$$absUrl.lastIndexOf('/protected/orders/myOrders') > 0 || $location.$$absUrl.lastIndexOf('/protected/orders/allOrders') > 0 || $location.$$absUrl.lastIndexOf('/protected/orders/myTransactions') > 0){
        $scope.activeURL = 'order';
    } else if ($location.$$absUrl.lastIndexOf('/protected/adminPage') > 0){
        $scope.activeURL = 'adminPage';
    } else {
        $scope.activeURL = 'home';
    }
}