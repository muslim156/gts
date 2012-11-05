package ba.kickboxing.draw.common;

public enum WeightCategory implements CustomValueDefined {

    LESS_THAN_78("-78"), LESS_THAN_81("-81"), GREATER_THAN_81("+81");
    private String value;

    private WeightCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WeightCategory getEnum(String s) {
        if (s != null && s.equals(LESS_THAN_81.getValue())) {
            return LESS_THAN_81;
        } else if (s != null && s.equals(GREATER_THAN_81.getValue())) {
            return GREATER_THAN_81;
        } else if (s != null && s.equals(LESS_THAN_78.getValue())) {
            return LESS_THAN_78;
        } else {
            throw new IllegalArgumentException("Unknown value for enum.");
        }
    }

    @Override
    public Object getCustomValue() {
        return value;
    }
}
