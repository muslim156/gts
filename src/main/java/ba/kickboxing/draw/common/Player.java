package ba.kickboxing.draw.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Player {
	private String nameSurname;
	private String discipline;
	private String weight;
	private String ageCategory;
	private String sex;

	private String clubName;

	public Player(String nameSurname, String discipline, String weight,
			String ageCategory, String sex, String clubName) {
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
		return nameSurname + "|" + discipline + "|" + weight + "|"
				+ ageCategory + "|" + sex + "|" + clubName;
	}

	public Map<String, Object> getDataMap() {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			try {
				Object value = null;

				if (CustomValueDefined.class.isAssignableFrom(field.getType())) {
					value = ((CustomValueDefined) field.get(this)).getCustomValue();
				} else {
					value = field.get(this);
				}

				dataMap.put(field.getName(), value);
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

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getTournamentCard() {
		return getNameSurname() + " - " + getClubName();
	}

}
