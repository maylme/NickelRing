import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.ToggleSwitch;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        ToggleSwitch button = new ToggleSwitch();
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
        launch(args);
    }
}
