package sample;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.*;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.opencv.highgui.Highgui.imencode;

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
    // a timer for acquiring the video stream
    private ScheduledExecutorService timer;
    // the OpenCV object that performs the video capture
    private VideoCapture capture = new VideoCapture();
    // a flag to change the button behavior
    private boolean cameraActive;

    // property for object binding
    private ObjectProperty<String> hsvValuesProp;

    private int centerX = 0;
    private int centerXold = 0;
    private int count = 8;
    private boolean first = true;


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
            startCamera();
        }else{
            System.out.println("cameraOff");
            cameraButtonImgView.setImage(cameraButtonOffImg);
            startCamera();
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

    private void startCamera()
    {
        // bind a text property with the string containing the current range of
        // HSV values for object detection
        hsvValuesProp = new SimpleObjectProperty<>();

        if (!this.cameraActive)
        {
            // start the video capture
            this.capture.open(0);

            // is the video stream available?
            if (this.capture.isOpened())
            {
                this.cameraActive = true;

                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run()
                    {
                        String direction = grabFrame();
                        if("left".equals(direction)){
                            nextPage();
                        }else if("right".equals(direction)){
                            previousPage();
                        }
                    }
                };

                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 20, TimeUnit.MILLISECONDS);
                // update the button content
            }
            else
            {
                // log the error
                System.err.println("Failed to open the camera connection...");
            }
        }
        else
        {
            // the camera is not active at this point
            this.cameraActive = false;
            // update again the button content

            // stop the timer
            try
            {
                this.timer.shutdown();
                this.timer.awaitTermination(20, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                // log the exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }

            // release the camera
            this.capture.release();
        }
    }

    /**
     * Get a frame from the opened video stream (if any)
     *
     * @return the {@link Image} to show
     */
    private String grabFrame()
    {
        // init everything
        Image imageToShow = null;
        Mat frame = new Mat();
        String direction = "";



        // check if the capture is open
        if (this.capture.isOpened())
        {
            try
            {
                // read the current frame
                this.capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty())
                {
                    // init
                    Mat blurredImage = new Mat();
                    Mat hsvImage = new Mat();
                    Mat mask = new Mat();
                    Mat morphOutput = new Mat();

                    // remove some noise
                    Imgproc.blur(frame, blurredImage, new Size(7, 7));

                    // convert the frame to HSV
                    Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);

                    // get thresholding values from the UI
                    // remember: H ranges 0-180, S and V range 0-255
                    Scalar minValues = new Scalar(53,74,160);
                    Scalar maxValues = new Scalar(90,147,255);

                    // show the current selected HSV range
                    String valuesToPrint = "Hue range: " + minValues.val[0] + "-" + maxValues.val[0]
                            + "\tSaturation range: " + minValues.val[1] + "-" + maxValues.val[1] + "\tValue range: "
                            + minValues.val[2] + "-" + maxValues.val[2];
                    this.onFXThread(this.hsvValuesProp, valuesToPrint);

                    // threshold HSV image to select tennis balls
                    Core.inRange(hsvImage, minValues, maxValues, mask);


                    // morphological operators
                    // dilate with large element, erode with small ones
                    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
                    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));

                    Imgproc.erode(mask, morphOutput, erodeElement);
                    Imgproc.erode(mask, morphOutput, erodeElement);

                    Imgproc.dilate(mask, morphOutput, dilateElement);
                    Imgproc.dilate(mask, morphOutput, dilateElement);

                    // show the partial output


                    // find the tennis ball(s) contours and show them
                    frame = this.findAndDrawBalls(morphOutput, frame);
                    //System.out.println(morphOutput.col(1));
                    //old = centerX;
                    //System.out.println(old);
                    if(centerX - centerXold > 5){
                        count++;
                    }else if (centerXold - centerX > 5){
                        count--;
                    }
                    System.out.println(count);
                    if(count==0){
                        System.out.println("left");
                        count = 8;
                        direction =  "left";
                    }else if(count==16){
                        System.out.println("right");
                        count = 8;
                        direction =  "right";
                    }
                    if(!first){
                        centerXold = centerX;
                    }
                    // convert the Mat object (OpenCV) to Image (JavaFX)
                    imageToShow = mat2Image(frame);
                }

            }
            catch (Exception e)
            {
                // log the (full) error
                System.err.print("ERROR");
                e.printStackTrace();
            }
        }
        return direction;
    }

    /**
     * Given a binary image containing one or more closed surfaces, use it as a
     * mask to find and highlight the objects contours
     *
     * @param maskedImage
     *            the binary image to be used as a mask
     * @param frame
     *            the original {@link Mat} image to be used for drawing the
     *            objects contours
     * @return the {@link Mat} image with the objects contours framed
     */
    private Mat findAndDrawBalls(Mat maskedImage, Mat frame)
    {
        // init
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

        // find contours
        Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

        // if any contour exist...
        if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
        {
            // for each contour, display it in blue
            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
            {
                Imgproc.drawContours(frame, contours, idx, new Scalar(250, 0, 0));
            }
        }

        //List<Moments> mu = new ArrayList<Moments>(contours.size());
        List<Moments> mu = new ArrayList<Moments>(1);
        //for (int i = 0; i < contours.size(); i++) {
        if(contours.size() != 0) {
            mu.add(0, Imgproc.moments(contours.get(0), false));
            Moments p = mu.get(0);
            int x = (int) (p.get_m10() / p.get_m00());
            int y = (int) (p.get_m01() / p.get_m00());
            centerX = x;
            if (first) {
                centerXold = x;
                first = false;
            }
            Core.circle(frame, new Point(x, y), 4, new Scalar(0, 0, 255));
        }

        //}

        return frame;
    }

    /**
     * Set typical {@link ImageView} properties: a fixed width and the
     * information to preserve the original image ration
     *
     * @param image
     *            the {@link ImageView} to use
     * @param dimension
     *            the width of the image to set
     */
    private void imageViewProperties(ImageView image, int dimension)
    {
        // set a fixed width for the given ImageView
        image.setFitWidth(dimension);
        // preserve the image ratio
        image.setPreserveRatio(true);
    }

    /**
     * Convert a {@link Mat} object (OpenCV) in the corresponding {@link Image}
     * for JavaFX
     *
     * @param frame
     *            the {@link Mat} representing the current frame
     * @return the {@link Image} to show
     */
    private Image mat2Image(Mat frame)
    {
        // create a temporary buffer
        MatOfByte buffer = new MatOfByte();
        // encode the frame in the buffer, according to the PNG format
        imencode(".png", frame, buffer);
        // build and return an Image created from the image encoded in the
        // buffer
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    /**
     * Generic method for putting element running on a non-JavaFX thread on the
     * JavaFX thread, to properly update the UI
     *
     * @param property
     *            a {@link ObjectProperty}
     * @param value
     *            the value to set for the given {@link ObjectProperty}
     */
    private <T> void onFXThread(final ObjectProperty<T> property, final T value)
    {
        Platform.runLater(new Runnable() {

            @Override
            public void run()
            {
                property.set(value);
            }
        });
    }





}
