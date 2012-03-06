import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection connection = null;
	
	public static Connection getConnection(){
		if(!isConnectedToDatabase()){
			try{
				connect();
			}catch(SQLException sqle){
				if(sqle.getErrorCode()==40000){
					connection = SQLManager.createDatabase();
				}
			}
		}
		return connection;
	}
	private static boolean isConnectedToDatabase(){
		if(connection == null){
			return false;
		}
		return true;
	}

	private static void connect() throws SQLException{
		String url = "jdbc:derby:database;";
		connection = DriverManager.getConnection(url);
		return;
	}

	//currently not used, it seems the driver can automatically be loaded through DriverManager
	private static boolean loadDriver()
	{
		boolean isLoaded = true;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			isLoaded = false;
			e.printStackTrace();
		}
		return isLoaded;
	}
	
}
