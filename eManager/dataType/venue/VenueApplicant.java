package eManager.dataType.venue;

import eManager.dataType.common.Member;

public class VenueApplicant extends Member {
	private String organization;

	/* constructor for VenueBookingApplication */
	public VenueApplicant(String name, String matricNo, String contact,
			String email, String organization) {
		this.name = name;
		this.matricNo = matricNo;
		this.contact = contact;
		this.email = email;
		this.organization = organization;
	}
	public VenueApplicant(int ID, String name, String matricNo,
			String contact, String email, String organization) {
		this.memberID = ID;
		this.name = name;
		this.matricNo = matricNo;
		this.contact = contact;
		this.email = email;
		this.organization = organization;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
}
