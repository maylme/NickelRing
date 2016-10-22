app.controller("mainCtrl", ["$rootScope", "$scope", (mainCtrl)])
    .directive("mainView", (mainView));

function mainCtrl($rootScope, $scope){
    $scope.currentPage = 1;
    $scope.maxImage = 4;
    $scope.camBtn = false;
    $scope.micBtn = false;

    $scope.prevPage = function(){
        console.log("previous");
        if($scope.currentPage > 1){
            $scope.currentPage--;
        }
    }

    $scope.nextPage = function(){
        console.log("next");
        if($scope.currentPage < $scope.maxImage){
            $scope.currentPage++;
        }
    }

    $scope.toggleMic = function(){
        $scope.micBtn = !$scope.micBtn;
        //window.alert("the audio interaction is not yet implemented");
        if (annyang) {
            if($scope.micBtn){
                var commands = {
                    'back': function() { $scope.$apply($scope.prevPage()); },
                    'next': function() { $scope.$apply($scope.nextPage()); },
                    'help': function() { alert("say 'next' or 'previous' to navigate"); }
                };
                annyang.addCommands(commands);
                annyang.start();
            } else {
                annyang.removeCommands();
                annyang.abort();
            }

        }
    }

    $scope.toggleCam = function(){
        //$scope.camBtn = !$scope.camBtn;
        window.alert("the video interaction is not yet implemented");
    }

}

function mainView(){
    return {
        templateUrl: "/views/mainView.html",
        controller: "mainCtrl"
    }
}