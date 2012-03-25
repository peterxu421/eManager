public class Date {
	private int year;
	private int month;
	private int day;

<<<<<<< HEAD
	public Date() {
		super();
	}

=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public Date(String date) {
		String[] contents = date.split("-");
		this.year = Integer.parseInt(contents[0]);
		this.month = Integer.parseInt(contents[1]);
		this.day = Integer.parseInt(contents[2]);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String toString() {
		return String.format("%02d", year) + "-" + String.format("%02d", month)
				+ "-" + String.format("%02d", day);
	}

	public static Date parseDate(String dateString) {
		String[] dates = dateString.split("-");
		Date date = new Date(Integer.parseInt(dates[0]),
				Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
		return date;
	}
	
<<<<<<< HEAD
	public boolean isEqualTo(Date date){
=======
	boolean isEqualTo(Date date){
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
		if(this.day == date.getDay() &&
		        this.month == date.getMonth() &&
				this.year == date.getYear()){
			return true;	
		}
		else return false;	
	}
<<<<<<< HEAD
	
	public boolean isNotLaterThan(Date date) {
		if (year > date.getYear() ||
				year == date.getYear() && month > date.getMonth() ||
				year == date.getYear() && month == date.getMonth() && day > date.getDay() ||
				year == date.getYear() && month == date.getMonth() && day == date.getDay()){
			return true;
		}
		else return false;
	}
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
}
