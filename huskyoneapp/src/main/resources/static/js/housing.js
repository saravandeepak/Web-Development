var app = angular.module("myApp",[]);

app.controller('MyController',['$scope', '$http', '$sce', function($scope, $http, $sce){
    
    var req ={
        
    }
    $http({
        method: 'GET',
        url:'https://cors-anywhere.herokuapp.com/https://jobs.github.com/positions.json',
        
    }).success(function(data){

        console.log(data);
        $scope.data = data;
        

    });
    //https://jobs.github.com/positions.json?page=2
    //http://ip-api.com/json
    //https://www.googleapis.com/books/v1/volumes?q=isbn:0747532699
    
}]);