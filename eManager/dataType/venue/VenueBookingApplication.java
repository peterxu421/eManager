package eManager.dataType.venue;

import eManager.macro.MACRO;

public class VenueBookingApplication {
	private int venueBookingApplicationID;
	private Venue venue;
	private VenueApplicant applicant;
	private BookedDateTime dateTime;
	private int status;
	
	public VenueBookingApplication(int venueBookingApplicationID, Venue venue,
			VenueApplicant applicant, BookedDateTime dateTime, int status) {
		super();
		this.venueBookingApplicationID = venueBookingApplicationID;
		this.venue = venue;
		this.applicant = applicant;
		this.dateTime = dateTime;
		this.status = status;
	}

	public VenueBookingApplication(Venue venue, VenueApplicant applicant,
			BookedDateTime dateTime) {
		super();
		this.venue = venue;
		this.applicant = applicant;
		this.dateTime = dateTime;
		this.status = MACRO.PENDING;
	}
	
	

	public VenueBookingApplication() {
		super();
	}

	public int getVenueBookingApplicationID() {
		return venueBookingApplicationID;
	}

	public void setVenueBookingApplicationID(int venueBookingApplicationID) {
		this.venueBookingApplicationID = venueBookingApplicationID;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public VenueApplicant getApplicant() {
		return applicant;
	}

	public void setApplicant(VenueApplicant applicant) {
		this.applicant = applicant;
	}

	public BookedDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(BookedDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
