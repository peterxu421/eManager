public class MACRO {
	public static int ORGANIZER = 9;
	public static int FACILITATOR = 10;
	public static int PARTICIPANT = 3;
	public static int APPLICANT = 4;
	public static int MANAGER = 5;

	public static int PENDING = 1;
	public static int APPROVED = 2;
	public static int REJECTED = 3;

	public static int TEXT = 1;
	public static int DATE = 2;
	public static int TIME = 3;
	public static int CHECK = 4;
	public static int INT = 5;
	public static int DOUBLE = 6;
	public static int TEXTBIG = 7;
	public static int FACULTY = 8;

	
	public static boolean[][] ORGANIZER_MODE = new boolean[][] {{true, true, true, true}, {true, true, true, true}};
	public static boolean[][] FACILITATOR_MODE = {{true, true, true, true}, {true, true, true, true}};
	public static boolean[][] PARTICIPANT_MODE = {{true, true, true, true}, {true, true, true, true}};
	public static boolean[][] APPLICANT_MODE = {{true, true, true, true}, {true, true, true, true}};
	public static boolean[][] MANAGER_MODE = {{true, true, true, true}, {true, true, true, true}};
	
	private String[][] tabList = new String[][]{
			{"Pre-Event", "Actual-Event", "Meeting", "Budget", "Feedback" },
			{	"Manpower Allocation" },
			{	"Itinerary","Packing List" },
			{	"Event Registration" }
	};
}
