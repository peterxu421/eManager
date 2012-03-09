
public class VenueApplicant extends Member {
	private String organization;
	public VenueApplicant(String name, String matricNo, String faculty, int year,
			int contact, String email, String foodType, String allergy, String position, String organization) {
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
		this.role = MACRO.APPLICANT;
		this.organization = organization;
	}
	public VenueApplicant(int ID, String name, String matricNo, String faculty, int year,
			int contact, String email, String foodType, String allergy, String position, String organization) {
		this.ID = ID;
		this.name = name;
		this.matricNo = matricNo;
		this.faculty = faculty;
		this.year = year;
		this.contact = contact;
		this.email = email;
		this.foodType = foodType;
		this.allergy = allergy;
		this.role = MACRO.APPLICANT;
		this.organization = organization;
	}
}
