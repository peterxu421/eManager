
public class venueBookingInfo {
	private int venueBookingInfoId;
	private String fullName;
	private String matricNo;
	private String Organization;
	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	
	public venueBookingInfo(int venueBookingInfoId, String fullName,
			String matricNo, String organization, Date dateStart, Date dateEnd,
			Time timeStart, Time timeEnd) {
		this.venueBookingInfoId = venueBookingInfoId;
		this.fullName = fullName;
		this.matricNo = matricNo;
		Organization = organization;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	
	public venueBookingInfo(String fullName,
			String matricNo, String organization, Date dateStart, Date dateEnd,
			Time timeStart, Time timeEnd) {
		this.fullName = fullName;
		this.matricNo = matricNo;
		Organization = organization;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
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

	public String getOrganization() {
		return Organization;
	}

	public void setOrganization(String organization) {
		Organization = organization;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}
}
