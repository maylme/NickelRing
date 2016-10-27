app.controller("mainCtrl", ["$rootScope", "$scope", (mainCtrl)])
    .directive("mainView", (mainView));

function mainCtrl($rootScope, $scope){
    $scope.currentPage = 1;
    $scope.maxImage = 4;
    $scope.camBtn = false;
    $scope.micBtn = false;

    //go back to previous page
    $scope.prevPage = function(){
        //test if the current page is not the first
        if($scope.currentPage > 1){
            $scope.currentPage--;
        }
    }

    //switch to next page
    $scope.nextPage = function(){
        //test if the current page is not the last one
        if($scope.currentPage < $scope.maxImage){
            $scope.currentPage++;
        }
    }

    $scope.toggleMic = function(){
        //toggle the var
        $scope.micBtn = !$scope.micBtn;
        //if annyang lib correctly imported
        if (annyang) {
            //if micro activate
            if($scope.micBtn){
                //define some commands
                var commands = {
                    'previous': function() { $scope.$apply($scope.prevPage()); },
                    'next': function() { $scope.$apply($scope.nextPage()); },
                    'help': function() { alert("say 'next' or 'previous' to navigate"); }
                };
                //add the command and activate the micro
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