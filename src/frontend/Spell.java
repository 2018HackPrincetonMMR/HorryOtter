package frontend;

public abstract class Spell {

	public enum SpellType {
		NULL, /* Always type your nulls! */ LEVITATE, SPARKS, AVADA, LUMOS, EXPECTO
	}

	SpellType type;
	long constructionTime;

	public SpellType getType() {
		return type;
	}

	public long getConstructionTime() {
		return constructionTime;
	}
	
	public static class GestureSpell extends Spell {
		public GestureSpell(SpellType type) {
			this.constructionTime = System.currentTimeMillis();

			if(type == SpellType.NULL)
				this.constructionTime = 0;
			this.type = type;
		}

		
	}
	
	public static class AcousticSpell extends Spell {
		public AcousticSpell(SpellType type) {
			this.type = type;
			this.constructionTime = System.currentTimeMillis();
		}
	}
}
