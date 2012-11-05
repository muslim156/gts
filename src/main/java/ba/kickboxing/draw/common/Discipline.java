package ba.kickboxing.draw.common;

public enum Discipline implements CustomValueDefined {
    //TODO Handle other languages/values better
	K1_RULES("K1 RULES"), LOW_KICK("LOW KICK"), FULL_CONTACT("FULL CONTACT"), SEMI_CONTACT("SEMI CONTACT"), 
	LIGHT_CONTACT("LIGHT CONTACT"), MUSICAL_FORMS("MUZICKE FORME"), KICK_LIGHT("KICK LIGHT");
	
    private String value;

    private Discipline(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

	public static Discipline getEnum(String s) {
		if (s != null && s.equals(K1_RULES.getValue())) {
			return K1_RULES;
		} else if (s != null && s.equals(LOW_KICK.getValue())) {
			return LOW_KICK;
		} else if (s != null && s.equals(FULL_CONTACT.getValue())) {
			return FULL_CONTACT;
		} else if (s != null && s.equals(SEMI_CONTACT.getValue())) {
			return SEMI_CONTACT;
		} else if (s != null && s.equals(LIGHT_CONTACT.getValue())) {
			return LIGHT_CONTACT;
		} else if (s != null && s.equals(MUSICAL_FORMS.getValue())) {
			return MUSICAL_FORMS;
		} else if (s != null && s.equals(KICK_LIGHT.getValue())) {
			return KICK_LIGHT;
		} else {
			throw new IllegalArgumentException("Unknown value for enum.");
		}
	}

    @Override
    public Object getCustomValue() {
        return value;
    }
}
