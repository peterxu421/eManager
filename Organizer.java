public class Organizer extends Member {
	private String position;

	public Organizer(String name, int year, String faculty, String position) {
		this.name = name;
		this.year = year;
		this.faculty = faculty;
		this.position = position;
	}

	public Organizer(String name, String matricNo, String faculty, int year, String contact, String email, String foodType, String allergy, String position) {
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
		this.position = position;
	}
	public Organizer(int ID, String name, String matricNo, String faculty, int year, String contact, String email, String foodType, String allergy, String position) {
		this.ID = ID;
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
