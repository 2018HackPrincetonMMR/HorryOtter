package frontend;

public abstract class Spell {

	public enum SpellType {
		NULL /* Always type your nulls! */, LEVITATE, SPARKS
	}

	static long idCount;
	SpellType type;
	long id;

	public SpellType getType() {
		return type;
	}

	public long getID() {
		return id;
	}
	
	public static class GestureSpell extends Spell {
		public GestureSpell(SpellType type) {
			this.type = type;
			this.id = System.currentTimeMillis();
		}

		
	}
	
	public static class AcousticSpell extends Spell {
		public AcousticSpell(SpellType type) {
			this.type = type;
			this.id = System.currentTimeMillis();
		}
	}
}
