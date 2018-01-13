var app = angular.module("myApp",['infinite-scroll']);

app.controller('MyController',['$scope', '$http', '$sce', function($scope, $http, $sce){
    
    $scope.page = 0;
    $scope.jobs= [];
	$http({
        method: 'GET',
        url:'https://cors-anywhere.herokuapp.com/https://jobs.github.com/positions.json?page='+ $scope.page
        
    }).success(function(data){

       
        $scope.jobs = data;
        console.log($scope.jobs);
        

    });
  
    
    $scope.loadMore = function(){
    	$scope.page ++;
    	
    	$http({
	        method: 'GET',
	        url:'https://cors-anywhere.herokuapp.com/https://jobs.github.com/positions.json?page='+ $scope.page
	        
	    }).success(function(data){
	
	        console.log(data);
	        $scope.jobs = $scope.jobs.concat(data);
	        console.log($scope.jobs);

	    });
    }

    
}]);