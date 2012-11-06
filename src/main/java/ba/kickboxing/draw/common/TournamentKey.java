package ba.kickboxing.draw.common;

public class TournamentKey {

	private String discipline;
	private String weightCategory;
	private String ageCategory;
	private String sex;

	public TournamentKey(String discipline, String ageCategory, String weight, String sex) {
		super();
		this.discipline = discipline.toUpperCase();
		this.weightCategory = weight.toUpperCase();
		this.ageCategory = ageCategory.toUpperCase();
		this.sex = sex.toUpperCase();
	}

	@Override
	public String toString() {
		return this.discipline + " - " 
				+ this.ageCategory + " - "
				+ this.weightCategory + " - " 
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

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getWeightCategory() {
		return weightCategory;
	}

	public void setWeightCategory(String weightCategory) {
		this.weightCategory = weightCategory;
	}

	public String getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
