package ba.kickboxing.draw.common;

public enum Discipline implements CustomValueDefined {
    //TODO Handle other languages/values better

    FULLCONTACT("FULL CONTACT"), SEMICONTACT("SEMI CONTACT"), K1_RULES("K1 RULES"),
    LOW_KICK("LOW KICK");
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
        } else if (s != null && s.equals(K1_RULES.getValue())) {
            return K1_RULES;
        } else if (s != null && s.equals(LOW_KICK.getValue())) {
            return LOW_KICK;
        } else {
            throw new IllegalArgumentException("Unknown value for enum.");
        }
    }

    @Override
    public Object getCustomValue() {
        return value;
    }
}
