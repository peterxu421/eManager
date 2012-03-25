
public class Time {
	private int hour;
	private int minute;
	private int second;
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public Time(String time){
		String[] contents = time.split(":");
		this.hour = Integer.parseInt(contents[0]);
		this.minute = Integer.parseInt(contents[1]);
		this.second = Integer.parseInt(contents[2]);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public String toString(){
		return String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
	}
	
	public static Time parseTime(String timeString){
		String[] contents = timeString.split(":");
		Time time = new Time(Integer.parseInt(contents[0]), Integer.parseInt(contents[1]), Integer.parseInt(contents[2]));
		return time;
	}
	
	public static int parseHour(String timeString){
		String[] contents = timeString.split(":");
		int hour = Integer.parseInt(contents[0]);
		return hour;
	}
	
<<<<<<< HEAD
	public boolean isEqualTo(Time time){
=======
	boolean isEqualTo(Time time){
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
		if (hour == time.getHour() &&
				minute == time.getMinute() &&
				second == time.getSecond() ){
			return true;
		}
		else return false;
	}
}
