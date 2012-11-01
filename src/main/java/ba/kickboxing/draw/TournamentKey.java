package ba.kickboxing.draw;

public class TournamentKey {

	private Discipline discipline;
	private int weight;
	private Sex sex;

	public TournamentKey(Discipline discipline, int weight, Sex sex) {
		super();
		this.discipline = discipline;
		this.weight = weight;
		this.sex = sex;
	}

	@Override
	public String toString() {	
		return this.discipline + " - " + this.weight + " - " + this.sex;
	}
	
	@Override
	public boolean equals(Object obj) {	
		if (obj instanceof TournamentKey) {
			TournamentKey otherKey = (TournamentKey) obj;
			
			if (this.discipline.equals(otherKey.getDiscipline()) 
					&& this.weight == otherKey.getWeight()
					&& this.sex.equals(otherKey.getSex())) {
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return (discipline.toString() + weight + sex.toString()).hashCode();
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
}
