package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{


       /* Stage secondStage = new Stage();
        // load the FXML resource
        BorderPane root1 = (BorderPane) FXMLLoader.load(getClass().getResource("view/ObjRecognition.fxml"));
        // set a whitesmoke background
        root1.setStyle("-fx-background-color: whitesmoke;");
        // create and style a scene
        Scene scene = new Scene(root1, 1200,1000);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        // create the stage with the given title and the previously created
        // scene
        secondStage.setTitle("Object Recognition");
        secondStage.setScene(scene);
        // show the GUI
        secondStage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //ToggleSwitch button = new ToggleSwitch();
        primaryStage.setTitle("NyckelRing");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();





        /*BorderPane root = new BorderPane();
        ToggleSwitch button = new ToggleSwitch();
        root.setCenter(button);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();*/

    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
}
