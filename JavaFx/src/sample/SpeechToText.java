package sample;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;


/**
 * A simple HelloWorld demo showing a simple speech application built using Sphinx-4. This application uses the Sphinx-4
 * endpointer, which automatically segments incoming audio into utterances and silences.
 */
public class SpeechToText {
	
	private ConfigurationManager cm;
	private Recognizer recognizer;
	private Microphone microphone;
	
	public SpeechToText(){
		 

//	        if (args.length > 0) {
//	            cm = new ConfigurationManager(args[0]);
//	        } else {
	            cm = new ConfigurationManager(SpeechToText.class.getResource("../controlPage.config.xml"));
//	        }

	        recognizer = (Recognizer) cm.lookup("recognizer");
	        recognizer.allocate();

	        // start the microphone or exit if the programm if this is not possible
	        microphone = (Microphone) cm.lookup("microphone");
	        if (!microphone.startRecording()) {
	            System.out.println("Cannot start microphone.");
	            recognizer.deallocate();
	            System.exit(1);
	        }
	        System.out.println("Microphone is ready");
		
	}
	
	public String recognization(){
		//while (true) {
            //System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();
			String resultText = "";
            if (result != null) {
                resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText + '\n');
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        //}
		return resultText;
	}

    //public static void main(String[] args) {
    //	SpeechToText speechToText = new SpeechToText();
    //	speechToText.recognization();
       

       // System.out.println("Say: (Good morning | Hello) ( Bhiksha | Evandro | Paul | Philip | Rita | Will )");

        // loop the recognition until the programm exits.
//        while (true) {
//            //System.out.println("Start speaking. Press Ctrl-C to quit.\n");
//
//            Result result = recognizer.recognize();
//
//            if (result != null) {
//                String resultText = result.getBestFinalResultNoFiller();
//                System.out.println("You said: " + resultText + '\n');
//            } else {
//                System.out.println("I can't hear what you said.\n");
//            }
//        }
    //}
}