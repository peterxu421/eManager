package eManager.dataType.event;
public class Event {
	private int eventID;
	private String eventName;
	private String eventDescription;
	private String organizerPassword;
	private String facilitatorPassword;

	public Event(int eventID, String eventName, String eventDescription,
			String organizerPassword, String facilitatorPassword) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.organizerPassword = organizerPassword;
		this.facilitatorPassword = facilitatorPassword;
	}

	public Event(String eventName, String eventDescription,
			String organizerPassword, String facilitatorPassword) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.organizerPassword = organizerPassword;
		this.facilitatorPassword = facilitatorPassword;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getOrganizerPassword() {
		return organizerPassword;
	}

	public void setOrganizerPassword(String organizerPassword) {
		this.organizerPassword = organizerPassword;
	}

	public String getFacilitatorPassword() {
		return facilitatorPassword;
	}

	public void setFacilitatorPassword(String facilitatorPassword) {
		this.facilitatorPassword = facilitatorPassword;
	}

}
