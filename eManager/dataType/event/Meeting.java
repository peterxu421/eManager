package eManager.dataType.event;

import eManager.dataType.common.Date;
import eManager.dataType.common.Time;

public class Meeting {
	private int meetingID;
	private String meetingDetails;
	private Date date;
	private Time time;
	private boolean done;

	public Meeting(int meetingID, String meetingDetails, Date date, Time time,
			boolean done) {
		super();
		this.meetingID = meetingID;
		this.meetingDetails = meetingDetails;
		this.date = date;
		this.time = time;
		this.done = done;
	}

	public Meeting(String meetingDetails, Date date, Time time, boolean done) {
		super();
		this.meetingDetails = meetingDetails;
		this.date = date;
		this.time = time;
		this.done = done;
	}

	public int getMeetingID() {
		return meetingID;
	}

	public void setMeetingID(int meetingID) {
		this.meetingID = meetingID;
	}

	public String getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(String meetingDetails) {
		this.meetingDetails = meetingDetails;
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
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
