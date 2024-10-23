// app.js
angular.module('placeApp', [])
.controller('PlaceController', ['$scope', '$http', function($scope, $http) {
    // Hàm để lấy dữ liệu từ PlaceController
    $scope.getPlaces = function() {
        $http.get('http://127.0.0.1:8080/api/place') // URL của PlaceController
        .then(function(response) {
            $scope.places = response.data.content; // Giả sử dữ liệu ở trong 'content'
        }, function(error) {
            console.error('Lỗi khi gọi API:', error);
        });
    };

    // Gọi hàm để lấy dữ liệu khi khởi tạo controller
    $scope.getPlaces();
}]);
