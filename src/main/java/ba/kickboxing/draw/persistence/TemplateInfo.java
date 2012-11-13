package ba.kickboxing.draw.persistence;

public class TemplateInfo {
	private String name;

	private Index index;
	private Index step;

	public TemplateInfo(String name, Index index, Index step) {
		super();

		this.name = name;
		this.index = index;
		this.step = step;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Index getIndex() {
		return index;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

	public Index getStep() {
		return step;
	}

	public void setStep(Index step) {
		this.step = step;
	}

}
