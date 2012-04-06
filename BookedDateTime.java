import com.ibm.icu.util.StringTokenizer;

public class BookedDateTime implements Comparable<BookedDateTime> {

	private Date date;
	private Time timeStart;
	private Time timeEnd;

	public BookedDateTime() {
		super();
	}

	public BookedDateTime(Date date, Time timeStart, Time timeEnd) {
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

	public String toString() {
		return date.toString() + "      " + timeStart.toString() + " - "
				+ timeEnd.toString();
	}

	public static BookedDateTime parseBookingDateTime(String dateTimeStr) {
		StringTokenizer st = new StringTokenizer(dateTimeStr);
		String _date = st.nextToken(); // get date
		String _timeStart = st.nextToken(); // get timeStart
		st.nextToken(); // skip '-'
		String _timeEnd = st.nextToken();
		BookedDateTime dateTime = new BookedDateTime(Date.parseDate(_date),
				Time.parseTime(_timeStart), Time.parseTime(_timeEnd));
		return dateTime;
	}

	public int compareTo(BookedDateTime dateTime) {
		if (date.getYear() > dateTime.getDate().getYear()
				|| date.getYear() == dateTime.getDate().getYear()
				&& date.getMonth() > dateTime.getDate().getMonth()
				|| date.getYear() == dateTime.getDate().getYear()
				&& date.getMonth() == dateTime.getDate().getMonth()
				&& date.getDay() > dateTime.getDate().getDay()
				|| date.getYear() == dateTime.getDate().getYear()
				&& date.getMonth() == dateTime.getDate().getMonth()
				&& date.getDay() == dateTime.getDate().getDay()
				&& timeStart.getHour() > dateTime.getTimeStart().getHour()) {
			return 1; // greater than
		}

		else if (date.getYear() == dateTime.getDate().getYear()
				&& date.getMonth() == dateTime.getDate().getMonth()
				&& date.getDay() == dateTime.getDate().getDay()
				&& timeStart.getHour() == dateTime.getTimeStart().getHour()) {
			return 0; // equal
		} else
			return -1; // less than
	}

	public boolean isEqualTo(BookedDateTime dateTime) {
		if (date.isEqualTo(dateTime.getDate())
				&& timeStart.isEqualTo(dateTime.getTimeStart())
				&& timeEnd.isEqualTo(dateTime.getTimeEnd())) {
			return true;
		} else
			return false;
	}
}
