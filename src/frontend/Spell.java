package frontend;

public class Spell {

	public enum SpellType {
		NULL /* Always type your nulls! */, LEVITATE, SPARKS
	}

	private static long idCount;
	private SpellType type;
	private long id;

	public Spell(SpellType type) {
		this.type = type;
		this.id = idCount++;
	}

	public SpellType getType() {
		return type;
	}

	public long getID() {
		return id;
	}
}
