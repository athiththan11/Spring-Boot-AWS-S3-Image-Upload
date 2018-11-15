"use strict";

angular.module("app.routes", []).config([
    "$routeProvider",
    "$locationProvider",
    function($routeProvider, $locationProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "view/home.html",
                controller: "S3Controller"
            })
            .otherwise({
                redirectTo: "/"
            });

        $locationProvider.html5Mode(true);
    }
]);
