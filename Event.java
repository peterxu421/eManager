public class Event {
	private int eventID;
	private String eventName;
	private String eventDescription;
	
	
	public Event(int eventID, String eventName, String eventDescription) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
	}

	public Event(String eventName, String eventDescription) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
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
}
