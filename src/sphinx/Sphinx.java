package sphinx;

import java.util.Arrays;
import java.util.HashSet;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class Sphinx {      
	private static final String ACOUSTIC_MODEL =
	        "resource:/edu/cmu/sphinx/models/en-us/en-us";

	public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath(ACOUSTIC_MODEL);
        configuration.setDictionaryPath("file:resources/spells.dic");
        configuration.setLanguageModelPath("file:resources/spells.lm");

        LiveSpeechRecognizer recognizer =
            new LiveSpeechRecognizer(configuration);
        
        HashSet<String> valid = new HashSet<String>(Arrays.asList("ALOHOMORA", "AVADA KEDAVRA", "EXPECTO PATRONUM", "EXPELLIARMUS", "LUMOS", "OBLIVIATE", "RIDDIKULUS", "WINGARDIUM LEVIOSA"));

        recognizer.startRecognition(true);
        while (true) {
            String utterance = recognizer.getResult().getHypothesis();
            
            if (utterance.startsWith("EXIT"))
                break;
            
            if (valid.contains(utterance))
            	System.out.println(utterance);

        }

        recognizer.stopRecognition();
    }
}
