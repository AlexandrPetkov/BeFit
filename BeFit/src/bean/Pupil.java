package bean;

import java.io.Serializable;

public class Pupil extends User implements Serializable {

	private static final long serialVersionUID = -4487987477639926182L;

	private String height_sm;
	private String weight;
	private int idTrainers;
	private String goal;

	public Pupil() {
	}

	public Pupil(User user) {
		id = user.getId();
		login = user.getLogin();
		password = user.getPassword();
		name = user.getName();
		secondName = user.getSecondName();
		birthday = user.getBirthday();
		age = user.getAge();
		photo = user.getPhoto();
		isTrainer = user.getisTrainer();
		isMale = user.isMale();
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getHeight_sm() {
		return height_sm;
	}

	public void setHeight_sm(String height_sm) {
		this.height_sm = height_sm;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getIdTrainers() {
		return idTrainers;
	}

	public void setIdTrainers(int idTrainers) {
		this.idTrainers = idTrainers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		result = prime * result + ((height_sm == null) ? 0 : height_sm.hashCode());
		result = prime * result + idTrainers;
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pupil other = (Pupil) obj;
		if (goal == null) {
			if (other.goal != null)
				return false;
		} else if (!goal.equals(other.goal))
			return false;
		if (height_sm == null) {
			if (other.height_sm != null)
				return false;
		} else if (!height_sm.equals(other.height_sm))
			return false;
		if (idTrainers != other.idTrainers)
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pupil [height_sm=" + height_sm + ", weight=" + weight + ", idTrainers=" + idTrainers + ", goal=" + goal + "]";
	}

}
