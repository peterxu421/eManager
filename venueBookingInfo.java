
public class VenueBookingInfo {
	private int venueBookingInfoId;
	private Venue venue;
	private VenueApplicant applicant;
	private BookingDateTime dateTime;
	public VenueBookingInfo(int venueBookingInfoId, Venue venue,
			VenueApplicant applicant, BookingDateTime dateTime) {
		super();
		this.venueBookingInfoId = venueBookingInfoId;
		this.venue = venue;
		this.applicant = applicant;
		this.dateTime = dateTime;
	}
	public VenueBookingInfo(Venue venue, VenueApplicant applicant,
			BookingDateTime dateTime) {
		super();
		this.venue = venue;
		this.applicant = applicant;
		this.dateTime = dateTime;
	}
	public int getVenueBookingInfoId() {
		return venueBookingInfoId;
	}
	public void setVenueBookingInfoId(int venueBookingInfoId) {
		this.venueBookingInfoId = venueBookingInfoId;
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
	
	
	
}
