"use strict";

angular.module("s3.controller", []).controller("S3Controller", [
    "$scope",
    "$rootScope",
    "S3Service",
    function($scope, $rootScope, UploadService) {
        $scope.image = "";
        $scope.message = { message: "", status: true };

        $scope.upload = function() {
            $scope.reset();

            var fileType = $scope.image.type;
            if (!(fileType == "image/png" || fileType == "image/jpg" || fileType == "image/jpeg")) {
                $scope.message = {
                    message: "Not an Image File!",
                    status: false
                };

                return;
            }

            UploadService.uploadImage($scope.image).then(
                (data) => {
                    $scope.image = "";
                    $scope.message = {
                        message: "Uploaded Successfully",
                        status: true
                    };
                },
                (err) => {
                    $scope.image = "";
                    $scope.message = {
                        message: "Something went wrong!",
                        status: false
                    };
                }
            );
        };

        $scope.clear = function() {
            $scope.image = "";
            $scope.reset();
        };

        $scope.reset = function() {
            $scope.message = {};
        };
    }
]);
