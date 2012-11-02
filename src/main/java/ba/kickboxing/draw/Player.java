package ba.kickboxing.draw;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Player {
	private String nameSurname;
	private Discipline discipline;
	private WeightCategory weight;
	private AgeCategory ageCategory;
	private Sex sex;

	private String clubName;

	public Player(String nameSurname, Discipline discipline, WeightCategory weight, AgeCategory ageCategory, Sex sex, String clubName) {
		super();
		this.nameSurname = nameSurname;
		this.discipline = discipline;
		this.weight = weight;
		this.ageCategory = ageCategory;
		this.sex = sex;
		this.clubName = clubName;
	}
	
	@Override
	public String toString() {	
		return nameSurname + "|" + discipline + "|" + weight + "|" + ageCategory + "|" + sex + "|" + clubName;
	}
	
	public Map<String, Object> getDataMap() {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			try {
				dataMap.put(field.getName(), field.get(this));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dataMap;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public WeightCategory getWeight() {
		return weight;
	}

	public void setWeight(WeightCategory weight) {
		this.weight = weight;
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

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
}
