hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
    $http({url: 'http://localhost:8080/app/mvc/departments/all', method: 'GET'}).
        success(function (data) {
            $scope.departments = data;
        });
    $http({url: 'http://localhost:8080/app/mvc/employee/all', method: 'GET'}).
        success(function (data) {
            $scope.managers = data;
        });

    $http({url: 'http://localhost:8282/datamodel/jobs/findAll', method: 'GET'}).
        success(function (data) {
            $scope.jobs = data;
        });

    $http({url: 'http://localhost:8080/app/mvc/employee/one?idEmployee='+$routeParams.idEmployee, method: 'GET'}).
        success(function (data) {
            $scope.employee = data;
        });
}]);