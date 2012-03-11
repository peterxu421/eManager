
public class VenueBookingInfo {
	private int venueBookingInfoID;
	private Venue venue;
	private VenueApplicant applicant;
	private BookingDateTime dateTime;
	private int status;
	
	public VenueBookingInfo(int venueBookingInfoID, Venue venue,
			VenueApplicant applicant, BookingDateTime dateTime) {
		super();
		this.venueBookingInfoID = venueBookingInfoID;
		this.venue = venue;
		this.applicant = applicant;
		this.dateTime = dateTime;
		this.status = MACRO.PENDING;
	}

	public VenueBookingInfo(Venue venue, VenueApplicant applicant,
			BookingDateTime dateTime) {
		super();
		this.venue = venue;
		this.applicant = applicant;
		this.dateTime = dateTime;
		this.status = MACRO.PENDING;
	}

	public int getVenueBookingInfoID() {
		return venueBookingInfoID;
	}

	public void setVenueBookingInfoID(int venueBookingInfoID) {
		this.venueBookingInfoID = venueBookingInfoID;
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

	public BookingDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(BookingDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
