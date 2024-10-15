// apiService.js
app.service("ApiService", function ($http, API) {
    this.fetchAccounts = function (keyword, page, pageSize) {
      let url = `${API}account?page=${page - 1}&size=${pageSize}`;
      if (keyword) {
        url += "&search=" + encodeURIComponent(keyword);
      }
      return $http.get(url);
    };
  
    this.fetchContent = function (keyword, page, pageSize) {
      let url = `${API}content?page=${page - 1}&size=${pageSize}`;
      if (keyword) {
        url += "&search=" + encodeURIComponent(keyword);
      }
      return $http.get(url);
    };
  
    this.fetchPostsByYear = function (year) {
      return $http.get(`${API}admin/countpostyear/${year}`);
    };
  
    this.fetchTripsByYear = function (year) {
      return $http.get(`${API}admin/counttripyear/${year}`);
    };
  
    this.fetchUserByUsername = function (username) {
      return $http.get(`${API}profile/${username}`);
    };
  });
  