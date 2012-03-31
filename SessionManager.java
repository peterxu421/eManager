import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SessionManager {
	private static Connection connection = null;
	private static Event event = null;
	private static int intMode = 0;
	public static boolean[][] boolMode; 

	//Connection
	public static Connection getConnection() {
		if (!isConnectedToDatabase()) {
			try {
				connect();
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 40000) {
					connection = SQLManager.createDatabase();
				}
			}
		}
		return connection;
	}

	private static boolean isConnectedToDatabase() {
		if (connection == null) {
			return false;
		}
		return true;
	}

	private static void connect() throws SQLException {
		String url = "jdbc:derby:database;";
		connection = DriverManager.getConnection(url);
		return;
	}
	
	
	//Events
	public static Event getCurrentEvent() {
		return event;
	}
	public static void setCurrentEvent(Event newEvent) {
		event = newEvent;
	}
	
	
	//Mode
		public static int getCurrentIntMode() {
			return intMode;
		}
		public static void setCurrentMode(int mode) {
			intMode = mode;
			if(mode == 1)
				boolMode = MACRO.ORGANIZER_MODE;
			if(mode == 2)
				boolMode = MACRO.FACILITATOR_MODE;
			if(mode == 3)
				boolMode = MACRO.PARTICIPANT_MODE;
			if(mode == 4)
				boolMode = MACRO.APPLICANT_MODE;
			if(mode == 5)
				boolMode = MACRO.MANAGER_MODE;
		}

		//Mode
		public static boolean[][] getCurrentBoolMode() {
			return boolMode;
		}
}
