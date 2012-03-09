import java.util.ArrayList;


public class venueBookingInfo {
	private int venueBookingInfoId;
	private String fullName;
	private String matricNo;
	private String contactNo;
	private String email;
	private String organization;
	private ArrayList<BookingDateTime> dateTimeList; // dates and time slots for each booking is stored in an ArrayList
	
	public venueBookingInfo(int venueBookingInfoId, String fullName,
			String matricNo, String contactNo, String email,
			String organization, ArrayList<BookingDateTime> dateTimeList) {
		this.venueBookingInfoId = venueBookingInfoId;
		this.fullName = fullName;
		this.matricNo = matricNo;
		this.contactNo = contactNo;
		this.email = email;
		this.organization = organization;
		this.dateTimeList = dateTimeList;
	}
	public venueBookingInfo(String fullName,
			String matricNo, String contactNo, String email,
			String organization, ArrayList<BookingDateTime> dateTimeList) {
		this.fullName = fullName;
		this.matricNo = matricNo;
		this.contactNo = contactNo;
		this.email = email;
		this.organization = organization;
		this.dateTimeList = dateTimeList;
	}
	public int getVenueBookingInfoId() {
		return venueBookingInfoId;
	}
	public void setVenueBookingInfoId(int venueBookingInfoId) {
		this.venueBookingInfoId = venueBookingInfoId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMatricNo() {
		return matricNo;
	}
	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public ArrayList<BookingDateTime> getDateTimeList() {
		return dateTimeList;
	}
	public void setDateTimeList(ArrayList<BookingDateTime> dateTimeList) {
		this.dateTimeList = dateTimeList;
	}
}
