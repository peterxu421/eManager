package eManager.dataType.event;

import eManager.dataType.common.Date;
import eManager.dataType.common.Time;

public class Itinerary {
	private int itineraryID;
	private String itineraryDetails;
	private Date date;
	private Time time;
	private boolean Done;

	public Itinerary(String itineraryDetails, Date date, Time time, boolean done) {
		this.itineraryDetails = itineraryDetails;
		this.date = date;
		this.time = time;
		Done = done;
	}

	public Itinerary(int itineraryID, String itineraryDetails, Date date,
			Time time, boolean done) {
		this.itineraryID = itineraryID;
		this.itineraryDetails = itineraryDetails;
		this.date = date;
		this.time = time;
		Done = done;
	}

	public int getItineraryID() {
		return itineraryID;
	}

	public void setItineraryID(int itineraryID) {
		this.itineraryID = itineraryID;
	}

	public String getItineraryDetails() {
		return itineraryDetails;
	}

	public void setItineraryDetails(String itineraryDetails) {
		this.itineraryDetails = itineraryDetails;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public boolean isDone() {
		return Done;
	}

	public void setDone(boolean done) {
		Done = done;
	}
}
