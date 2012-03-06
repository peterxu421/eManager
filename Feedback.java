public class Feedback {
	private int feedbackID;
	private String feedbackDetails;
	private Date date;
	private Time time;
	public Feedback(int feedbackID, String feedbackDetails, Date date, Time time) {
		super();
		this.feedbackID = feedbackID;
		this.feedbackDetails = feedbackDetails;
		this.date = date;
		this.time = time;
	}
	public Feedback(String feedbackDetails, Date date, Time time) {
		super();
		this.feedbackDetails = feedbackDetails;
		this.date = date;
		this.time = time;
	}
	public int getFeedbackID() {
		return feedbackID;
	}
	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}
	public String getFeedbackDetails() {
		return feedbackDetails;
	}
	public void setFeedbackDetails(String feedbackDetails) {
		this.feedbackDetails = feedbackDetails;
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
	
}
