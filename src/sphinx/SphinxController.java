package sphinx;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import frontend.Spell;
import frontend.Spell.AcousticSpell;
import frontend.Spell.SpellType;

public class SphinxController {

	private Spell latestSpell = new Spell.AcousticSpell(SpellType.NULL);
	private String utterance = "";

	public SphinxController() {

	}

	public void start() {
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("file:resources/spells.dic");
		configuration.setLanguageModelPath("file:resources/spells.lm");

		LiveSpeechRecognizer recognizer;
		try {
			recognizer = new LiveSpeechRecognizer(configuration);
			HashSet<String> valid = new HashSet<String>(Arrays.asList("ALOHOMORA", "AVADA KEDAVRA", "EXPECTO PATRONUM",
					"EXPELLIARMUS", "LUMOS", "OBLIVIATE", "REDUCIO", "RIDDIKULUS", "WINGARDIUM LEVIOSA"));

			recognizer.startRecognition(true);
			while (true) {
				String utterance = recognizer.getResult().getHypothesis();

				if (valid.contains(utterance)) {
					this.utterance = utterance;
					System.out.println(utterance);
					onUpdate();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Spell getLatestSpell() {
		return latestSpell;
	}

	public void onUpdate() {
		switch (utterance) {
		case "ALOHOMORA":
			break;
		case "AVADA KEDAVRA":
			latestSpell = new Spell.AcousticSpell(SpellType.AVADA);
			break;
		case "EXPECTO PATRONUM":
			latestSpell = new Spell.AcousticSpell(SpellType.EXPECTO);
			break;
		case "EXPELLIARMUS":
			break;
		case "LUMOS":
			latestSpell = new Spell.AcousticSpell(SpellType.LUMOS);
			break;
		case "OBLIVIATE":
			break;
		case "REDUCIO":
			latestSpell = new Spell.AcousticSpell(SpellType.SPARKS);
			break;
		case "RIDDIKULUS":
			latestSpell = new Spell.AcousticSpell(SpellType.EXPECTO);
			break;
		case "WINGARDIUM LEVIOSA":
			latestSpell = new Spell.AcousticSpell(SpellType.LEVITATE);
			break;
		}

	}

	public void setLatestSpell(AcousticSpell acousticSpell) {
		latestSpell = acousticSpell;
	}

}
