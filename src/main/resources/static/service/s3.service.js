"use strict";

angular.module("s3.service", []).factory("S3Service", [
    "$http",
    "$q",
    function($http, $q) {
        return {
            uploadImage: function(image) {
                var deferred = $q.defer();

                var formData = new FormData();
                formData.append("image", image);

                $http({
                    method: "post",
                    url: "/upload",
                    data: formData,
                    headers: {
                        enctype: "multipart/form-data",
                        "Content-Type": undefined
                    }
                }).then(
                    (result) => {
                        deferred.resolve(result.data);
                    },
                    (err) => {
                        deferred.reject(err);
                    }
                );

                return deferred.promise;
            }
        };
    }
]);
