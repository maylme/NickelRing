package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;

public class Controller {
    @FXML
    private Button next, previous;

    @FXML
    private HBox toggleButton1,toggleButton2;

    @FXML
    private ImageView slide, previousImgView, nextImgView;

    @FXML
    private Label indexSlide;

    private Image[] img;
    private Image previousImg, previousClickImg, nextImg, nextClickImg;
    private int index;

    public void initialize() {
        toggleButton1 = new ToggleSwitch();
        toggleButton2 = new ToggleSwitch();
        img = new Image[]{new Image("@../../resources/1.png"),new Image("@../../resources/2.png"),new Image("@../../resources/3.png"),new Image("@../../resources/4.png")};
        previousImg = new Image("@../../resources/previous.png");
        previousClickImg = new Image("@../../resources/previousclick.png");
        nextImg = new Image("@../../resources/next.png");
        nextClickImg = new Image("@../../resources/nextclick.png");
        previousImgView.setImage(previousImg);
        nextImgView.setImage(nextImg);
        slide.setImage(img[0]);
        index = 0;
        indexSlide.setText(""+(index+1)+"/"+img.length);
    }

    @FXML
    private void handleButtonNextonAction(ActionEvent event) {
        System.out.println("next");
        if (index < 3) {
            index++;
            slide.setImage(img[index]);
            indexSlide.setText(""+(index+1)+"/"+img.length);
        }
    }

    @FXML
    private void handleButtonPreviousonAction(ActionEvent event) {
        System.out.println("previous");
        if (index > 0) {
            index--;
            slide.setImage(img[index]);
            indexSlide.setText(""+(index+1)+"/"+img.length);
        }
    }

    @FXML
    private void handleButtonNextonMouseClicked(ActionEvent event) {
        nextImgView.setImage(nextClickImg);
    }

    @FXML
    private void handleButtonPreviousonMouseClicked(ActionEvent event) {
        previousImgView.setImage(previousClickImg);
    }





}
