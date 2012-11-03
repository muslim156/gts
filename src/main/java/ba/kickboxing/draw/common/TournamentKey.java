package ba.kickboxing.draw.common;

public class TournamentKey {

	private Discipline discipline;
	private WeightCategory weightCategory;
	private AgeCategory ageCategory;
	private Sex sex;

	public TournamentKey(Discipline discipline, AgeCategory ageCategory, WeightCategory weight, Sex sex) {
		super();
		this.discipline = discipline;
		this.weightCategory = weight;
		this.ageCategory = ageCategory;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return this.discipline.getCustomValue() + " | " 
				+ this.ageCategory.getCustomValue() + " | "
				+ this.weightCategory.getCustomValue() + " | " 
				+ this.sex;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TournamentKey) {
			TournamentKey otherKey = (TournamentKey) obj;

			if (this.discipline.equals(otherKey.getDiscipline())
					&& this.weightCategory.equals(otherKey.getWeightCategory())
					&& this.ageCategory.equals(otherKey.getAgeCategory())
					&& this.sex.equals(otherKey.getSex())) {

				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		return (discipline.toString() + weightCategory.toString() + sex
				.toString()).hashCode();
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public WeightCategory getWeightCategory() {
		return weightCategory;
	}

	public void setWeightCategory(WeightCategory weightCategory) {
		this.weightCategory = weightCategory;
	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
