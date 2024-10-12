var app = angular.module("myApp", ["ngRoute"]);
var API = "http://localhost:8080/api/";
app.config(function ($routeProvider) {
  $routeProvider
    .when("/", {
      templateUrl: "dashboard.html",
      controller: "dashboardCtrl",
    })
    .when("/dashboard", {
      templateUrl: "dashboard.html",
      controller: "dashboardCtrl",
    })
    .when("/account", {
      templateUrl: "account.html",
      controller: "accountCtrl",
    })
    .when("/content", {
      templateUrl: "contenpost.html",
      controller: "contentPostCtrl",
    })
    .when("/profile/:username", {
      templateUrl: "app-profile.html",
      controller: "profileCtrl",
    });
});
app.controller("accountCtrl", function ($scope, $http) {
  var socket = new SockJS("http://localhost:8080/ws");
  var stompClient = Stomp.over(socket);
  $scope.accounts = [];
  $scope.toggleStatus = {};
  $scope.currentPage = 1;
  $scope.pageSize = 7;
  $scope.totalItems = 0;
  $scope.pages = [];
  $scope.selectedAccount = null;
  $scope.statusText = "";
  $scope.username = "";

  $scope.fetchAccounts = function (keyword, page) {
    if (isNaN(page) || page < 1) {
      page = 1;
    }
    let url = `${API + "account"}?page=${page - 1}&size=${$scope.pageSize}`;
    if (keyword) {
      url += "&search=" + encodeURIComponent(keyword);
    }

    $http({
      method: "GET",
      url: url,
    }).then(
      function successCallback(response) {
        $scope.accounts = response.data.content;
        $scope.totalItems = response.data.totalElements;
        $scope.currentPage = page;
        $scope.generatePages();
        $scope.accounts.forEach(function (account) {
          $scope.toggleStatus[account.username] = account.active;
        });
      },
      function errorCallback(error) {
        console.error("Lỗi khi lấy dữ liệu tài khoản:", error);
      }
    );
  };

  $scope.changePage = function (newPage) {
    if (newPage > 0 && newPage <= $scope.getPageCount()) {
      $scope.fetchAccounts($scope.searchKeyword, newPage);
    }
  };

  $scope.getPageCount = function () {
    return Math.ceil($scope.totalItems / $scope.pageSize);
  };

  $scope.generatePages = function () {
    var pageCount = $scope.getPageCount();
    var currentPage = $scope.currentPage;
    var visiblePages = 2;
    $scope.pages = [];
    $scope.pages.push(1);
    var start = Math.max(currentPage - visiblePages, 2);
    var end = Math.min(currentPage + visiblePages, pageCount - 1);
    if (start > 2) {
      $scope.pages.push("...");
    }
    for (var i = start; i <= end; i++) {
      $scope.pages.push(i);
    }
    if (end < pageCount - 1) {
      $scope.pages.push("...");
    }
    if (pageCount > 1) {
      $scope.pages.push(pageCount);
    }
  };

  $scope.searchAccounts = function () {
    $scope.fetchAccounts($scope.searchKeyword, 1);
  };

  $scope.fetchAccounts(null, 1);

  stompClient.connect({}, function (frame) {
    stompClient.subscribe("/topic/account-status", function (message) {
      var updatedUser = JSON.parse(message.body);
      var index = $scope.accounts.findIndex(
        (account) => account.username === updatedUser.username
      );
      if (index !== -1) {
        $scope.accounts[index].active = updatedUser.active;
        $scope.toggleStatus[updatedUser.username] = updatedUser.active;
        $scope.$apply();
      }
    });
  });
  $scope.originalStatus = {};

  $scope.toggleChanged = function (username) {
    $scope.selectedAccount = username;
    const newStatus = $scope.toggleStatus[username];
    $scope.statusText = newStatus ? "kích hoạt" : "vô hiệu hóa";
    $scope.username = username;

    $scope.originalStatus[username] = !newStatus;
    $("#confirmModal").modal("show");
  };

  $scope.cancelToggle = function () {
    $scope.toggleStatus[$scope.selectedAccount] =
      $scope.originalStatus[$scope.selectedAccount];
    $("#confirmModal").modal("hide");
  };

  $scope.confirmToggle = function () {
    const newStatus = $scope.toggleStatus[$scope.selectedAccount];

    $http({
      method: "PUT",
      url: API + "account/" + $scope.selectedAccount,
      data: { active: newStatus },
    }).then(
      function successCallback(response) {
        console.log(
          "Cập nhật trạng thái thành công cho " + $scope.selectedAccount
        );

        delete $scope.originalStatus[$scope.selectedAccount];
      },
      function errorCallback(error) {
        console.error("Lỗi khi cập nhật trạng thái:", error);
      }
    );

    $("#confirmModal").modal("hide");
  };
});

app.controller("contentPostCtrl", function ($scope, $http) {
  $scope.currentPage = 1;
  $scope.pageSize = 7;
  $scope.totalItems = 0;
  $scope.pages = [];

  $scope.fetchAccounts = function (keyword, page) {
    if (isNaN(page) || page < 1) {
      page = 1;
    }
    let url = `${API + "content"}?page=${page - 1}&size=${$scope.pageSize}`;

    if (keyword) {
      url += "&search=" + encodeURIComponent(keyword);
    }

    $http({
      method: "GET",
      url: url,
    }).then(
      function successCallback(response) {
        $scope.content = response.data.content;
        $scope.totalItems = response.data.totalElements;
        $scope.currentPage = page;
        $scope.generatePages();
        console.log("Lấy dữ liệu thành công");
      },
      function errorCallback(error) {
        console.error("Lỗi khi lấy dữ liệu tài khoản:", error);
      }
    );
  };

  $scope.changePage = function (newPage) {
    if (newPage > 0 && newPage <= $scope.getPageCount()) {
      $scope.fetchAccounts($scope.searchKeyword, newPage);
    }
  };

  $scope.getPageCount = function () {
    return Math.ceil($scope.totalItems / $scope.pageSize);
  };

  $scope.generatePages = function () {
    var pageCount = $scope.getPageCount();
    var currentPage = $scope.currentPage;
    var visiblePages = 2;
    $scope.pages = [];

    $scope.pages.push(1);

    var start = Math.max(currentPage - visiblePages, 2);
    var end = Math.min(currentPage + visiblePages, pageCount - 1);

    if (start > 2) {
      $scope.pages.push("...");
    }

    for (var i = start; i <= end; i++) {
      $scope.pages.push(i);
    }

    if (end < pageCount - 1) {
      $scope.pages.push("...");
    }

    if (pageCount > 1) {
      $scope.pages.push(pageCount);
    }
  };

  $scope.searchAccounts = function () {
    $scope.fetchAccounts($scope.searchKeyword, 1);
  };

  $scope.fetchAccounts(null, 1);
});

app.controller("dashboardCtrl", function ($scope, $http) {
  $scope.postCount = 0;
  $scope.tripCount = 0;
  $scope.selectedPostYear = null;
  $scope.selectedTripYear = null;
  $scope.years = [];

  for (let year = 2023; year <= 2100; year++) {
    $scope.years.push(year);
  }

  $scope.fetchPostsByYear = function (year) {
    if (year) {
      $http({
        method: "GET",
        url: API + `admin/countpostyear/` + year,
      }).then(
        function successCallback(response) {
          $scope.postCount = response.data.totalPosts;
          console.log("Tổng số bài đăng trong năm: " + $scope.postCount);
        },
        function errorCallback(error) {
          console.error("Lỗi khi lấy số bài đăng: ", error);
        }
      );
    }
  };

  $scope.fetchTripsByYear = function (year) {
    if (year) {
      $http({
        method: "GET",
        url: API + `admin/counttripyear/` + year,
      }).then(
        function successCallback(response) {
          $scope.tripCount = response.data.completedTrips;
          console.log("Tổng số chuyến đi trong năm: " + $scope.tripCount);
        },
        function errorCallback(error) {
          console.error("Lỗi khi lấy số chuyến đi: ", error);
        }
      );
    }
  };
});
app.controller("profileCtrl", function ($scope, $http, $routeParams) {
  $scope.user = {};
  $scope.error = null;

  $scope.fetchUserByUsername = function (username) {
    $http({
      method: "GET",
      url: API + `profile/` + username,
    }).then(
      function successCallback(response) {
        $scope.user = response.data;
      },
      function errorCallback(error) {
        console.log(error);
      }
    );
  };

  // const username = $routeParams.username;
  // if (username) {
  $scope.fetchUserByUsername("thuan");
  // console.log($routeParams.username);
  // }
});
