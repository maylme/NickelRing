package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        img = new Image[]{new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/1.png"),new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/2.png"),new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/3.png"),new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/4.png")};
        previousImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/previous.png");
        previousClickImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/previousClick.png");
        nextImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/next.png");
        nextClickImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/nextClick.png");
        cameraButtonOnImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/on.png");
        cameraButtonOffImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/off.png");
        microButtonOnImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/on.png");
        microButtonOffImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/off.png");
        cameraLogoImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/camera.png");
        microLogoImg = new Image("file:/home/ding/Documents/adaptation_des_interfaces/nyckelRing/src/resources/micro.png");

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
        nextPage();
    }

    @FXML
    private void handleButtonPreviousonAction(ActionEvent event) {
        System.out.println("previous");
        previousPage();
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

    Thread speech = null;
    SpeechRecognize speechRecognize = null;

    @FXML
    private void handleButtonMicroOnAction(ActionEvent event){



        if(microButton.isSelected()){
            System.out.println("microOn");
            microButtonImgView.setImage(microButtonOnImg);
            speechRecognize = new SpeechRecognize();
            speech = new Thread(speechRecognize);
            speech.start();
        }else{
            System.out.println("microOff");
            microButtonImgView.setImage(microButtonOffImg);

            if(speech != null){
                speechRecognize.terminate();
                speech.stop();
            }
        }
    }

    private class SpeechRecognize implements Runnable{
        private SpeechToText speechToText = new SpeechToText();
        private volatile boolean running = true;

        public void terminate() {
            running = false;
            System.out.println(running);
        }
        @Override
        public void run() {
            while(running){
                String text = speechToText.recognization();
                if("next".equals(text)){
                    nextPage();
                }else if("previous".equals(text)){
                    previousPage();
                }
            }
        }
    }

    private void nextPage(){
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

    private void previousPage(){
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





}
