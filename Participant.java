
public class Participant extends Member {
	public Participant(String name, int age, String faculty, String foodType){
		this.name = name;
		this.year = year;
		this.faculty= faculty;
		this.foodType=foodType;
	}
	public Participant(String name, String matricNo, String faculty, int year,
			int contact, String email, String foodType, String allergy) {
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
		this.role = MACRO.PARTICIPANT;
	}
	public Participant(int ID, String name, String matricNo, String faculty, int year,
			int contact, String email, String foodType, String allergy) {
		this.ID = ID;
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
		this.role = MACRO.PARTICIPANT;
	}
}
