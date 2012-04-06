public class MACRO {
	public static int ORGANIZER = 1;
	public static int FACILITATOR = 2;
	public static int PARTICIPANT = 3;
	public static int APPLICANT = 4;
	public static int MANAGER = 5;

	public static int PENDING = 6;
	public static int APPROVED = 7;
	public static int REJECTED = 8;

	public static int TEXT = 9;
	public static int DATE = 10;
	public static int TIME = 11;
	public static int CHECK = 12;
	public static int INT = 13;
	public static int DOUBLE = 14;
	public static int TEXTBIG = 15;
	public static int FACULTY = 16;
	public static int VENUETYPE = 17;
	public static int VENUELOCATION = 18;
	public static int PASSWORD = 19;
	public static int READONLY = 20;
	public static int ROLES = 21;

	public static boolean[][] ORGANIZER_MODE = new boolean[][] {
			{ true, true, true, true }, { true, true, true, true, true },
			{ true }, { true, true }, { true }, { true } };
	public static boolean[][] FACILITATOR_MODE = new boolean[][] {
			{ false, true, true, true }, { false, false, false, false, false },
			{ true }, { true, true }, { true }, { false } };
	public static boolean[][] PARTICIPANT_MODE = new boolean[][] {
			{ false, false, true, true },
			{ false, false, false, false, false }, { false }, { true, true },
			{ true }, { false } };
	public static boolean[][] APPLICANT_MODE = new boolean[][] {
			{ false, true }, { false, false }, { true, true, true } };
	public static boolean[][] MANAGER_MODE = new boolean[][] { { true, true },
			{ true, true }, { true, true, true } };
}
