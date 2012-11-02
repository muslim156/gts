package ba.kickboxing.draw;

public enum Discipline {
	//TODO Handle other languages/values better
	FULLCONTACT("FULL CONTACT"), SEMICONTACT("SEMI CONTACT");
	
	private String value;
	
	private Discipline(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Discipline getEnum(String s) {
		if (s != null && s.equals(FULLCONTACT.getValue())) {
			return FULLCONTACT;
		} else if (s != null && s.equals(SEMICONTACT.getValue())) {
			return SEMICONTACT;
		} else {
			throw new IllegalArgumentException("Unknown value for enum.");
		}
	}
}
