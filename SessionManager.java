import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SessionManager {
	private static Connection connection = null;
	private static Event event = null;
	private static int intMode = 0;
	public static boolean[][] boolMode;
	public static Shell[] shellList;
	private static HashMap<String, Shell> shellMap = new HashMap<String, Shell>();

	// Dispose any shells except root shell
	public static void disposeShells(Display display) {
		shellList = display.getShells();
		for (int i = 1; i < shellList.length; i++) {
			shellList[i].dispose();
		}
	}

	// Dipose all the shell except the root shell and the shell that we
	// indicate.
	public static void disposeShells(Display display, Shell shell) {
		shellList = display.getShells();
		for (int i = 1; i < shellList.length; i++) {
			System.out.println(shellList[i]);
			if (shellList[i].equals(shell)) {
				// do nothing
			} else
				shellList[i].dispose();
		}
	}

	public static void disposeShells(Display display, Shell shell1, Shell shell2) {
		shellList = display.getShells();
		for (int i = 1; i < shellList.length; i++) {
			if (shellList[i].equals(shell1) || shellList[i].equals(shell2)) {
				// do nothing
			} else
				shellList[i].dispose();
		}
	}

	// Connection
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

	// Events
	public static Event getCurrentEvent() {
		return event;
	}

	public static void setCurrentEvent(Event newEvent) {
		event = newEvent;
	}

	// Mode
	public static int getCurrentIntMode() {
		return intMode;
	}

	public static void setCurrentMode(int mode) {
		intMode = mode;
		if (mode == 1)
			boolMode = MACRO.ORGANIZER_MODE;
		if (mode == 2)
			boolMode = MACRO.FACILITATOR_MODE;
		if (mode == 3)
			boolMode = MACRO.PARTICIPANT_MODE;
		if (mode == 4)
			boolMode = MACRO.APPLICANT_MODE;
		if (mode == 5)
			boolMode = MACRO.MANAGER_MODE;
	}

	// Mode
	public static boolean[][] getCurrentBoolMode() {
		return boolMode;
	}

	public static void addShell(Shell shell) {
		shellMap.put(shell.getText(), shell);
	}

	public static Shell getShell(String key) {
		Shell shell = shellMap.get(key);
		return shell;
	}
}
