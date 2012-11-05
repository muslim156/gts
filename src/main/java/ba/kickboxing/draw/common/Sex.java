package ba.kickboxing.draw.common;

public enum Sex implements CustomValueDefined {
	M("MUSKI"), F("ZENSKI");

	private String value;

	private Sex(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Sex getEnum(String s) {
		if (s != null && s.equals(M.getValue())) {
			return M;
		} else if (s != null && s.equals(F.getValue())) {
			return F;
		} else {
			throw new IllegalArgumentException("Unknown value for enum.");
		}
	}
	
	@Override
	public Object getCustomValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
