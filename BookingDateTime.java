import com.ibm.icu.util.StringTokenizer;


public class BookingDateTime {

	private Date date;
	private Time timeStart;
	private Time timeEnd;
	
	public BookingDateTime(Date date, Time timeStart, Time timeEnd) {
		super();
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	
	public String toString(){
		return date.toString() + "   From " + timeStart.toString() + " to " + timeEnd.toString();
	}
	
	public static BookingDateTime parseBookingDateTime(String dateTimeStr){
		StringTokenizer st = new StringTokenizer(dateTimeStr);
		String dummy;
		String _date = st.nextToken(); // get date
		dummy = st.nextToken(); // skip 'From'
		String _timeStart = st.nextToken(); // get timeStart
		dummy = st.nextToken(); // skip 'to'
		String _timeEnd = st.nextToken();
		BookingDateTime dateTime = new BookingDateTime(Date.parseDate(_date), Time.parseTime(_timeStart), Time.parseTime(_timeEnd));
		return dateTime;
	}
}




