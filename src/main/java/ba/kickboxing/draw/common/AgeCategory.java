package ba.kickboxing.draw.common;

public enum AgeCategory implements CustomValueDefined {
	//TODO Handle other languages/values better
	CADET("KADET"), JUNIOR("JUNIOR"), SENIOR("SENIOR");

	private String value;

	private AgeCategory(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static AgeCategory getEnum(String s) {
		if (s != null && s.equals(CADET.getValue())) {
			return CADET;
		} else if (s != null && s.equals(JUNIOR.getValue())) {
			return JUNIOR;
		} else if (s != null && s.equals(SENIOR.getValue())) {
			return SENIOR;
		} else {
			throw new IllegalArgumentException("Unknown value for enum.");
		}
	}
	
	@Override
	public Object getCustomValue() {
		return value;
	}
}
