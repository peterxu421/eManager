public class Participant extends Member {
	public Participant() {

	}

	public Participant(String name, int year, String faculty, String foodType) {
		this.name = name;
		this.year = year;
		this.faculty = faculty;
		this.foodType = foodType;
	}

	public Participant(String name, String matricNo, String faculty, int year,
			String contact, String email, String foodType, String allergy) {
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
	}
<<<<<<< HEAD
	public Participant(int ID, String name, String matricNo, String faculty, int year,
			String contact, String email, String foodType, String allergy) {
=======

	public Participant(int ID, String name, String matricNo, String faculty,
			int year, String contact, String email, String foodType,
			String allergy) {
>>>>>>> 4bafc5eca8f50f40537fe9b82028dc57cadf896a
		this.ID = ID;
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
	}
}
