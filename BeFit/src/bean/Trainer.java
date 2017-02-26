package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trainer extends User implements Serializable {

	private static final long serialVersionUID = 1464281334676982063L;

	private String experience_years;
	private String specialization;
	private String price;
	private List<Pupil> pupils = new ArrayList<>();
	private String about;

	public Trainer() {
	}

	public Trainer(User user) {
		id = user.getId();
		login = user.getLogin();
		password = user.getPassword();
		name = user.getName();
		secondName = user.getSecondName();
		birthday = user.getBirthday();
		age = user.getAge();
		photo = user.getPhoto();
		isTrainer = true;
		isMale = user.isMale();
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getExperience_years() {
		return experience_years;
	}

	public void setExperience_years(String experience_years) {
		this.experience_years = experience_years;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Pupil> getPupils() {
		return pupils;
	}

	public void setPupils(List<Pupil> pupils) {
		this.pupils = pupils;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result + ((experience_years == null) ? 0 : experience_years.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((pupils == null) ? 0 : pupils.hashCode());
		result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
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
		Trainer other = (Trainer) obj;
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (experience_years == null) {
			if (other.experience_years != null)
				return false;
		} else if (!experience_years.equals(other.experience_years))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (pupils == null) {
			if (other.pupils != null)
				return false;
		} else if (!pupils.equals(other.pupils))
			return false;
		if (specialization == null) {
			if (other.specialization != null)
				return false;
		} else if (!specialization.equals(other.specialization))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trainer [experience_years=" + experience_years + ", specialization=" + specialization + ", price=" + price + ", pupils=" + pupils + ", about=" + about + "]";
	}

}
