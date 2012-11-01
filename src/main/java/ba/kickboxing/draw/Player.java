package ba.kickboxing.draw;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Player {
	private String name;
	private Discipline discipline;
	private int weight;
	private Sex sex;

	public Player(String name, Discipline discipline, int weight, Sex sex) {
		super();
		this.name = name;
		this.discipline = discipline;
		this.weight = weight;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
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
}
