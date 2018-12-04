
var myapp = angular.module('app',[]);
var url='mongodb://user1:password1@ds119113.mlab.com:119113/cs490';
myapp.run(function ($http) {
    // Sends this header with any AJAX request
    $http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
    // Send this header only in post requests. Specifies you are sending a JSON object
    $http.defaults.headers.post['dataType'] = 'json'
});

myapp.controller('homeController',function($scope,$http){

    $scope.getData=function(){
        var req = $http.get('http://127.0.0.1:8081/get');
        req.success(function(data, status, headers, config) {
            $scope.matchList = data;
            console.log(data);
        });
        req.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
        });

    };

});


