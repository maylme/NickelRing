package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;

public class Controller {
    @FXML
    private Button next, previous;

    @FXML
    private ToggleButton cameraButton, microButton;

    @FXML
    private ImageView slide, previousImgView, nextImgView, cameraButtonImgView, microButtonImgView, cameraLogoImgView, microLogoImgView;

    @FXML
    private Label indexSlide;

    private Image[] img;
    private Image previousImg, previousClickImg, nextImg, nextClickImg, cameraButtonOnImg, cameraButtonOffImg, microButtonOnImg, microButtonOffImg,cameraLogoImg,microLogoImg;
    private int index;

    public void initialize() {
        img = new Image[]{new Image("@../../resources/1.png"),new Image("@../../resources/2.png"),new Image("@../../resources/3.png"),new Image("@../../resources/4.png")};
        previousImg = new Image("@../../resources/previous.png");
        previousClickImg = new Image("@../../resources/previousClick.png");
        nextImg = new Image("@../../resources/next.png");
        nextClickImg = new Image("@../../resources/nextClick.png");
        cameraButtonOnImg = new Image("@../../resources/on.png");
        cameraButtonOffImg = new Image("@../../resources/off.png");
        microButtonOnImg = new Image("@../../resources/on.png");
        microButtonOffImg = new Image("@../../resources/off.png");
        cameraLogoImg = new Image("@../../resources/camera.png");
        microLogoImg = new Image("@../../resources/micro.png");

        previousImgView.setImage(previousImg);
        nextImgView.setImage(nextImg);
        slide.setImage(img[0]);
        cameraButtonImgView.setImage(cameraButtonOffImg);
        microButtonImgView.setImage(microButtonOffImg);
        cameraLogoImgView.setImage(cameraLogoImg);
        microLogoImgView.setImage(microLogoImg);
        index = 0;
        indexSlide.setText(""+(index+1)+"/"+img.length);
        previous.setDisable(true);
    }

    @FXML
    private void handleButtonNextonAction(ActionEvent event) {
        System.out.println("next");
        if (index < 2) {
            previous.setDisable(false);
            index++;
            slide.setImage(img[index]);
            indexSlide.setText(""+(index+1)+"/"+img.length);
            nextImgView.setImage(nextClickImg);
            previousImgView.setImage(previousImg);
        }else if (index == 2){
            index++;
            slide.setImage(img[index]);
            indexSlide.setText(""+(index+1)+"/"+img.length);
            nextImgView.setImage(nextClickImg);
            previousImgView.setImage(previousImg);
            next.setDisable(true);
        }
    }

    @FXML
    private void handleButtonPreviousonAction(ActionEvent event) {
        System.out.println("previous");
        if (index > 1) {
            next.setDisable(false);
            index--;
            slide.setImage(img[index]);
            indexSlide.setText(""+(index+1)+"/"+img.length);
            nextImgView.setImage(nextImg);
            previousImgView.setImage(previousClickImg);
        }else if (index == 1){
            index--;
            slide.setImage(img[index]);
            indexSlide.setText(""+(index+1)+"/"+img.length);
            nextImgView.setImage(nextImg);
            previousImgView.setImage(previousClickImg);
            previous.setDisable(true);
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

    @FXML
    private void handleButtonCameraOnAction(ActionEvent event){
        if(cameraButton.isSelected()){
            System.out.println("cameraOn");
            cameraButtonImgView.setImage(cameraButtonOnImg);
        }else{
            System.out.println("cameraOff");
            cameraButtonImgView.setImage(cameraButtonOffImg);
        }
    }

    @FXML
    private void handleButtonMicroOnAction(ActionEvent event){
        if(microButton.isSelected()){
            System.out.println("microOn");
            microButtonImgView.setImage(microButtonOnImg);
        }else{
            System.out.println("microOff");
            microButtonImgView.setImage(microButtonOffImg);
        }
    }





}
