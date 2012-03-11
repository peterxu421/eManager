import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*Helper Class for Database Management
 * Providing additional functionality
 */
public class SQLManager {
	private static String url_create = "jdbc:derby:database;create=true";
	
	private static String createEventDetailsTable = 
			"CREATE TABLE EventDetails(" +
			"EventID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," + 
			"EventName VARCHAR(50)," +
			"EventDescription VARCHAR(255))";
	private static String createTaskDetailsTable = 
			"CREATE TABLE TaskDetails(" +
			"TaskID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"TaskDescription VARCHAR(255)," +
			"AssignedTo VARCHAR(50)," +
			"Date DATE," +
			"Done SMALLINT)";
	private static String createMemberDetailsTable =
			"CREATE TABLE MemberDetails(" +
			"MemberID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"Name VARCHAR(50)," +
			"MatricNo VARCHAR(10)," +
			"Faculty VARCHAR(50)," +
			"Year INTEGER," +
			"Contact VARCHAR(20)," +
			"Email VARCHAR(20)," +
			"FoodType VARCHAR(10)," +
			"Allergy VARCHAR(20)," +
			"Role SMALLINT," +
			"Position VARCHAR(20)," +
			"Organization VARCHAR(20))";
	private static String createMeetingDetailsTable =
			"CREATE TABLE MeetingDetails(" +
			"MeetingID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"MeetingDetails VARCHAR(255)," +
			"Date DATE," +
			"Time TIME," +
			"Done SMALLINT)";
	private static String createFileDetailsTable =
			"CREATE TABLE FileDetails(" +
			"FileID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"FileName VARCHAR(255)," +
			"FileDirectory VARCHAR(255)," +
			"FileDescription VARCHAR(255))";
	private static String createBudgetDetailsTable =
			"CREATE TABLE BudgetDetails(" +
			"BudgetID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"Item VARCHAR(255)," +
			"PersonInCharge VARCHAR(50)," +
			"Cost DOUBLE," +
			"Date DATE)";
	private static String createInflowDetailsTable =
			"CREATE TABLE InflowDetails(" +
			"InflowID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"Sponser VARCHAR(50)," +
			"Amount DOUBLE," +
			"Date DATE," +
			"Remarks VARCHAR(255))";
	private static String createOutflowDetailsTable =
			"CREATE TABLE OutflowDetails(" +
			"OutflowID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"Item VARCHAR(50)," +
			"Quantity INTEGER," +
			"Type VARCHAR(20)," +
			"Date DATE," +
			"Cost DOUBLE)";
	private static String createFeedbackDetailsTable =
			"CREATE TABLE FeedbackDetails(" +
			"FeedbackID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"FeedbackDetails VARCHAR(255)," +
			"Date DATE," +
			"Time TIME)";
	private static String createAllocationDetailsTable =
			"CREATE TABLE AllocationDetails(" +
			"TaskID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"TaskDescription VARCHAR(255)," +
			"AssignedTo VARCHAR(50)," +
			"DateDue DATE," +
			"Done SMALLINT)";
	private static String createItineraryDetailsTable =
			"CREATE TABLE ItineraryDetails(" +
			"ItineraryID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"ItineraryDetails VARCHAR(255)," +
			"Date DATE," +
			"Time TIME," +
			"Done SMALLINT)";
	private static String createPackingDetailsTable = 
			"CREATE TABLE PackingDetails(" +
			"PackingID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"EventID INTEGER NOT NULL REFERENCES EventDetails(EventID)," +
			"Category VARCHAR(50)," +
			"Item VARCHAR(50)," +
			"Quantity INTEGER," +
			"Remarks VARCHAR(50))";
	private static String createVenueDetailsTable = 
			"CREATE TABLE VenueDetails(" +
			"VenueID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"Name VARCHAR(20)," +
			"Location VARCHAR(50)," +
			"Type VARCHAR(20))";
	private static String createVenueBookingDetailsTable = 
			"CREATE TABLE VenueBookingDetails(" +
			"BookingID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"VenueID INTEGER NOT NULL REFERENCES VenueDetails(VenueID)," +
			"ApplicantID INTEGER NOT NULL REFERENCES ApplicantDetails(ApplicantID)," +
			"Date DATE," +
			"TimeStart Time," +
			"TimeEnd Time," +
			"Status SMALLINT)";
	private static String createApplicantDetailsTable =
			"CREATE TABLE ApplicantDetails(" +
			"ApplicantID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
			"Name VARCHAR(20)," +
			"MatricNo VARCHAR(20)," +
			"Contact VARCHAR(20)," +
			"Email VARCHAR(20)," +
			"Organization VARCHAR(30))";
	public static Connection createDatabase(){
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			connection = DriverManager.getConnection(url_create);
			statement = connection.prepareStatement(createEventDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createTaskDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createMemberDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createMeetingDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createFileDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createBudgetDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createInflowDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createOutflowDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createFeedbackDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createAllocationDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createItineraryDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createPackingDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createVenueDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createVenueBookingDetailsTable);
			statement.execute();
			statement = connection.prepareStatement(createApplicantDetailsTable);
			statement.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return connection;
	}
	
	/*--------------------------------------------GET---------------------------------------------------------*/
	public static ResultSet getEventDetails(Connection connection){
		String getEventDetails =
				"SELECT * FROM EventDetails";
		Statement stat = null;
		ResultSet rs = null;
		try{
			stat = connection.createStatement();
			rs = stat.executeQuery(getEventDetails);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getTaskDetails(Connection connection, int eventID){
		String getTaskDetails =
				"SELECT * FROM TaskDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getTaskDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getBudgetDetails(Connection connection, int eventID){
		String getBudgetDetails =
				"SELECT * FROM BudgetDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getBudgetDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getInflowDetails(Connection connection, int eventID){
		String getInflowDetails =
				"SELECT * FROM InflowDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getInflowDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getOutflowDetails(Connection connection, int eventID){
		String getOutflowDetails =
				"SELECT * FROM OutflowDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getOutflowDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getAllocationDetails(Connection connection, int eventID){
		String getAllocationDetails =
				"SELECT * FROM AllocationDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getAllocationDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getFeedbackDetails(Connection connection, int eventID){
		String getFeedbackDetails =
				"SELECT * FROM FeedbackDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getFeedbackDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getItineraryDetails(Connection connection, int eventID){
		String getItineraryDetails =
				"SELECT * FROM ItineraryDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getItineraryDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getMeetingDetails(Connection connection, int eventID){
		String getMeetingDetails =
				"SELECT * FROM MeetingDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getMeetingDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getFileDetails(Connection connection, int eventID){
		String getFileDetails =
				"SELECT * FROM FileDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getFileDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getPackingDetails(Connection connection, int eventID){
		String getPackingDetails =
				"SELECT * FROM PackingDetails " +
				"WHERE EventID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getPackingDetails);
			prep.setInt(1, eventID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getVenueDetails(Connection connection){
		String getVenueDetails =
				"SELECT * FROM VenueDetails";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getVenueDetails);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getOrganizerDetails(Connection connection, int eventID){
		String getOrganizerDetails =
				"SELECT * FROM MemberDetails " +
				"WHERE EventID=? AND Role=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getOrganizerDetails);
			prep.setInt(1, eventID);
			prep.setInt(2, MACRO.ORGANIZER);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getFacilitatorDetails(Connection connection, int eventID){
		String getFacilitatorDetails =
				"SELECT * FROM MemberDetails " +
				"WHERE EventID=? AND Role=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getFacilitatorDetails);
			prep.setInt(1, eventID);
			prep.setInt(2, MACRO.FACILITATOR);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getParticipantDetails(Connection connection, int eventID){
		String getParticipantDetails =
				"SELECT * FROM MemberDetails " +
				"WHERE EventID=? AND Role=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getParticipantDetails);
			prep.setInt(1, eventID);
			prep.setInt(2, MACRO.PARTICIPANT);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getVenueBookingDetails(Connection connection, int venueID){
		String getVenueBookingDetails = 
				"SELECT * FROM VenueBookingDetails " +
				"WHERE VenueID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getVenueBookingDetails);
			prep.setInt(1, venueID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getVenueBookingDetailsAll(Connection connection){
		String getVenueBookingDetails = 
				"SELECT * FROM VenueBookingDetails";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(getVenueBookingDetails);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}

	
	/*--------------------------------------------------INSERT-----------------------------------------------------------*/
	public static int insertEventDetails(Connection connection, String eventName, String eventDescription){
		int eventID = 0;
		String insertEventDetails =
				"INSERT INTO EventDetails(EventName, EventDescription)" +
				"VALUES (?,?)";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertEventDetails, Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, eventName);
			prep.setString(2, eventDescription);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				eventID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return eventID;
	}
	public static int insertTaskDetails(Connection connection, int eventID, String taskDescription, String assignedTo, String date, boolean done){
		int taskID = 0;
		String insertTaskDetails =
		"INSERT INTO TaskDetails(EventID, TaskDescription, AssignedTo, Date, Done)" +
		"VALUES (?,?,?,?,?)";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertTaskDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, taskDescription);
			prep.setString(3, assignedTo);
			prep.setString(4, date);
			prep.setBoolean(5, done);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				taskID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return taskID;
	}
	public static int insertBudgetDetails(Connection connection, int eventID, String item, String personInCharge, double cost, String date){
		int budgetID = 0;
		String insertBudgetDetails =
				"INSERT INTO BudgetDetails (EventID,Item,PersonInCharge,Cost,Date) " +
				"VALUES (?,?,?,?,?)";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertBudgetDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, item);
			prep.setString(3, personInCharge);
			prep.setDouble(4, cost);
			prep.setString(5, date);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				budgetID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return budgetID;
	}
	public static int insertMeetingDetails(Connection connection, int eventID, String meetingDetails, String date, String time, boolean isDone){
		String insertMeetingDetails =
				"INSERT INTO MeetingDetails (EventID,MeetingDetails,Date,Time,Done) " +
				"VALUES (?,?,?,?,?)";
		int meetingID=0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertMeetingDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, meetingDetails);
			prep.setString(3, date);
			prep.setString(4, time);
			prep.setBoolean(5, isDone);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				meetingID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return meetingID;
	}	
	public static int insertInflowDetails(Connection connection, int eventID, String sponser, double amount, String date, String remarks){
		int inflowID = 0;
		String insertInflowDetails =
				"INSERT INTO InflowDetails (EventID,Sponser,Amount,Date,Remarks) " +
				"VALUES (?,?,?,?,?)";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertInflowDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, sponser);
			prep.setDouble(3, amount);
			prep.setString(4, date);
			prep.setString(5, remarks);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				inflowID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return inflowID;	
	}
	public static int insertOutflowDetails(Connection connection,int eventID, String item, int quantity, String type, String date, double cost){
		int outflowID = 0;
		String insertOutflowDetails =
				"INSERT INTO OutflowDetails (EventID,Item,Quantity,Type,Date,Cost) " +
				"VALUES (?,?,?,?,?,?)";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertOutflowDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, item);
			prep.setInt(3, quantity);
			prep.setString(4, type);
			prep.setString(5, date);
			prep.setDouble(6, cost);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				outflowID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return outflowID;	
	}
	public static int insertFeedbackDetails(Connection connection, int eventID, String feedbackDetails, String date, String time){
		String insertFeedbackDetails =
				"INSERT INTO FeedbackDetails (EventID,FeedbackDetails,Date,Time) " +
				"VALUES (?,?,?,?)";
		int feedbackID=0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertFeedbackDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, feedbackDetails);
			prep.setString(3, date);
			prep.setString(4, time);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				feedbackID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return feedbackID;
	}
	public static int insertItineraryDetails(Connection connection , int eventID, String itineraryDetails, String date, String time, boolean isDone){
		String insertItineraryDetails =
				"INSERT INTO ItineraryDetails (EventID,ItineraryDetails,Date,Time,Done) " +
				"VALUES (?,?,?,?,?)";
		int itineraryID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertItineraryDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, itineraryDetails);
			prep.setString(3, date);
			prep.setString(4, time);
			prep.setBoolean(5, isDone);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				itineraryID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return itineraryID;
	}
	public static int insertAllocationDetails(Connection connection, int eventID, String taskDescription, String assignedTo, String dateDue, boolean isDone){
		String insertAllocationDetails = 
				"INSERT INTO AllocationDetails (EventID, TaskDescription, AssignedTo, DateDue, Done) " +
				"VALUES (?,?,?,?,?)";
		int allocationID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertAllocationDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, taskDescription);
			prep.setString(3, assignedTo);
			prep.setString(4, dateDue);
			prep.setBoolean(5, isDone);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				allocationID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return allocationID;
	}
	public static int insertFileDetails(Connection connection, int eventID, String fileName, String fileDirectory, String fileDescription){
		String insertFileDetails = 
				"INSERT INTO FileDetails (EventID, FileName, FileDirectory, FileDescription) " +
				"VALUES (?,?,?,?)";
		int fileID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertFileDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, fileName);
			prep.setString(3, fileDirectory);
			prep.setString(4, fileDescription);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				fileID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return fileID;
	}
	public static int insertPackingDetails(Connection connection, int eventID, String category, String item, int quantity, String remarks){
		String insertPackingDetails = 
				"INSERT INTO PackingDetails (EventID, Category, Item, Quantity, Remarks) " +
				"VALUES (?,?,?,?,?)";
		int packingID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertPackingDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, category);
			prep.setString(3, item);
			prep.setInt(4, quantity);
			prep.setString(5, remarks);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				packingID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return packingID;
	}
	public static int insertVenueDetails(Connection connection, String name, String location, String type){
		String insertVenueDetails = 
				"INSERT INTO VenueDetails (Name, Location, Type) " +
				"VALUES (?,?,?)";
		int venueID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertVenueDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, name);
			prep.setString(2, location);
			prep.setString(3, type);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				venueID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return venueID;
	}
	public static int insertOrganizerDetails(Connection connection, int eventID, String name, String matricNo, String faculty, int schoolYear, String contact, String email, String foodType, String allergy, String position){
		String insertOrganizerDetails = 
				"INSERT INTO MemberDetails (EventID, Name, MatricNo, Faculty, SchoolYear, Contact, Email, FoodType, Allergy, Role, Position) " +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		int memberID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertOrganizerDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, name);
			prep.setString(3, matricNo);
			prep.setString(4, faculty);
			prep.setInt(5, schoolYear);
			prep.setString(6, contact);
			prep.setString(7, email);
			prep.setString(8, foodType);
			prep.setString(9, allergy);
			prep.setInt(10, MACRO.ORGANIZER);
			prep.setString(11, position);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				memberID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return memberID;
	}
	public static int insertFacilitatorDetails(Connection connection, int eventID, String name, String matricNo, String faculty, int schoolYear, String contact, String email, String foodType, String allergy, String position){
		String insertFacilitatorDetails = 
				"INSERT INTO MemberDetails (EventID, Name, MatricNo, Faculty, SchoolYear, Contact, Email, FoodType, Allergy, Role, Position) " +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		int memberID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertFacilitatorDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, name);
			prep.setString(3, matricNo);
			prep.setString(4, faculty);
			prep.setInt(5, schoolYear);
			prep.setString(6, contact);
			prep.setString(7,email);
			prep.setString(8, foodType);
			prep.setString(9, allergy);
			prep.setInt(10, MACRO.FACILITATOR);
			prep.setString(11, position);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				memberID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return memberID;
	}
	public static int insertParticipantDetails(Connection connection, int eventID, String name, String matricNo, String faculty, int schoolYear, String contact, String email, String foodType, String allergy){
		String insertParticipantDetails = 
				"INSERT INTO MemberDetails (EventID, Name, MatricNo, Faculty, SchoolYear, Contact, Email, FoodType, Allergy, Role) " +
				"VALUES (?,?,?,?,?,?,?,?,?,?)";
		int memberID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertParticipantDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, eventID);
			prep.setString(2, name);
			prep.setString(3, matricNo);
			prep.setString(4, faculty);
			prep.setInt(5, schoolYear);
			prep.setString(6, contact);
			prep.setString(7,email);
			prep.setString(8, foodType);
			prep.setString(9, allergy);
			prep.setInt(10, MACRO.PARTICIPANT);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				memberID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return memberID;
	}
	public static int insertVenueBookingDetails(Connection connection, int venueID, int applicantID, String date, String timeStart, String timeEnd, int status){
		String insertVenueBookingDetails = 
				"INSERT INTO VenueBookingDetails (VenueID, ApplicantID, Date, TimeStart, TimeEnd, Status) " +
				"VALUES (?,?,?,?,?,?)";
		int bookingID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertVenueBookingDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setInt(1, venueID);
			prep.setInt(2, applicantID);
			prep.setString(3, date);
			prep.setString(4, timeStart);
			prep.setString(5, timeEnd);
			prep.setInt(6, status);
			prep.execute();
			rs = prep.getGeneratedKeys();
			while(rs.next()){
				bookingID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return bookingID;
	}
	public static int insertVenueApplicant(Connection connection, String name, String matricNo, String contact, String email, String organization ){
		String insertFacilitatorDetails = 
				"INSERT INTO ApplicantDetails (Name, MatricNo, Contact, Email, Organization) " +
				"VALUES (?,?,?,?,?)";
		int memberID = 0;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(insertFacilitatorDetails,Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, name);
			prep.setString(2, matricNo);
			prep.setString(3, contact);
			prep.setString(4, email);
			prep.setString(5, organization);
			prep.execute();
			rs=prep.getGeneratedKeys();
			while(rs.next()){
				memberID = rs.getInt(1);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return memberID;
	}
	
	/*-------------------------------------------------------DELETE----------------------------------------------------------------------------------*/
	public static void deleteEventDetails(Connection connection, int eventID){
		String deleteEvent = 
				"DELETE FROM EventDetails " +
				"WHERE EventID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteEvent);
			prep.setInt(1, eventID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteTaskDetails(Connection connection, int taskID){
		String deleteTask = 
				"DELETE FROM TaskDetails " +
				"WHERE TaskID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteTask);
			prep.setInt(1, taskID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteBudgetDetails(Connection connection, int budgetID){
		String deleteBudget = 
				"DELETE FROM BudgetDetails " +
				"WHERE BudgetID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteBudget);
			prep.setInt(1, budgetID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteMeetingDetails(Connection connection, int meetingID){
		String deleteMeeting = 
				"DELETE FROM MeetingDetails " +
				"WHERE MeetingID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteMeeting);
			prep.setInt(1, meetingID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteInflowDetails(Connection connection, int inflowID){
		String deleteInflow = 
				"DELETE FROM InflowDetails " +
				"WHERE InflowID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteInflow);
			prep.setInt(1, inflowID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteOutflowDetails(Connection connection, int outflowID){
		String deleteOutflow = 
				"DELETE FROM OutflowDetails " +
				"WHERE OutflowID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteOutflow);
			prep.setInt(1, outflowID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteFeedbackDetails(Connection connection, int feedbackID){
		String deleteFeedback = 
				"DELETE FROM FeedbackDetails " +
				"WHERE FeedbackID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteFeedback);
			prep.setInt(1, feedbackID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteItineraryDetails(Connection connection, int itineraryID){
		String deleteItineraryDetails = 
				"DELETE FROM ItineraryDetails " +
				"WHERE itineraryID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteItineraryDetails);
			prep.setInt(1, itineraryID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteAllocationDetails(Connection connection, int allocationID){
		String deleteAllocationDetails = 
				"DELETE FROM AllocationDetails " +
				"WHERE taskID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteAllocationDetails);
			prep.setInt(1, allocationID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteFileDetails(Connection connection, int fileID){
		String deleteFileDetails = 
				"DELETE FROM FileDetails " +
				"WHERE fileID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteFileDetails);
			prep.setInt(1, fileID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deletePackingDetails(Connection connection, int packingID){
		String deletePackingDetails = 
				"DELETE FROM PackingDetails " +
				"WHERE packingID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deletePackingDetails);
			prep.setInt(1, packingID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteVenueDetails(Connection connection, int venueID){
		String deleteVenueDetails = 
				"DELETE FROM VenueDetails " +
				"WHERE venueID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deleteVenueDetails);
			prep.setInt(1, venueID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteMemberDetails(Connection connection, int memberID){
		String deletePackingDetails = 
				"DELETE FROM MemberDetails " +
				"WHERE MemberID=?";
		try {
			PreparedStatement prep = connection.prepareStatement(deletePackingDetails);
			prep.setInt(1, memberID);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------UPDATE--------------------------------------------------------------*/
	public static void updateTaskDetails(Connection connection, int taskID, String taskDescription, String assignedTo, String date, boolean done){
		String updateTaskDetails =
		"UPDATE TaskDetails SET TaskDescription=?,AssignedTo=?,Date=?,Done=? " +
		"WHERE TaskID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateTaskDetails);
			prep.setString(1, taskDescription);
			prep.setString(2, assignedTo);
			prep.setString(3, date);
			prep.setBoolean(4, done);
			prep.setInt(5, taskID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateBudgetDetails(Connection connection, int budgetID, String item, String personInCharge, double cost, String date){
		String updateBudgetDetails =
				"UPDATE BudgetDetails SET Item=?,PersonInCharge=?,Cost=?,Date=? " +
				"WHERE BudgetID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateBudgetDetails);
			prep.setString(1, item);
			prep.setString(2, personInCharge);
			prep.setDouble(3, cost);
			prep.setString(4, date);
			prep.setInt(5, budgetID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateMeetingDetails(Connection connection, int meetingID, String meetingDetails, String date, String time, boolean isDone){
		String updateMeetingDetails =
				"UPDATE MeetingDetails SET MeetingDetails=?,Date=?,Time=?,Done=? " +
				"WHERE MeetingID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateMeetingDetails);
			prep.setString(1, meetingDetails);
			prep.setString(2, date);
			prep.setString(3, time);
			prep.setBoolean(4, isDone);
			prep.setInt(5, meetingID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateInflowDetails(Connection connection, int inflowID, String sponser, double amount, String date, String remarks){
		String updateInflowDetails =
				"UPDATE InflowDetails SET Sponser=?,Amount=?,Date=?,Remarks=? " +
				"WHERE InflowID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateInflowDetails);
			prep.setString(1, sponser);
			prep.setDouble(2, amount);
			prep.setString(3, date);
			prep.setString(4, remarks);
			prep.setInt(5, inflowID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateOutflowDetails(Connection connection, int outflowID, String item, int quantity, String type, String date, double cost){
		String updateOutflowDetails =
				"UPDATE OutflowDetails SET Quantity=?,Type=?,Date=?,Cost=?,Item=?" +
				"WHERE OutflowID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateOutflowDetails);
			prep.setInt(1, quantity);
			prep.setString(2, type);
			prep.setString(3, date);
			prep.setDouble(4, cost);
			prep.setString(5,item);
			prep.setInt(6, outflowID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateFeedbackDetails(Connection connection, int feedbackID, String feedbackDetails, String date, String time){
		String updateFeedbackDetails =
				"UPDATE FeedbackDetails SET FeedbackDetails=?,Date=?,Time=? " +
				"WHERE FeedbackID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateFeedbackDetails);
			prep.setString(1, feedbackDetails);
			prep.setString(2, date);
			prep.setString(3, time);
			prep.setInt(4, feedbackID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateItineraryDetails(Connection connection, int itineraryID, String itineraryDetails, String date, String time, boolean isDone){
		String updateItineraryDetails =
				"UPDATE ItineraryDetails SET ItineraryDetails=?,Date=?,Time=?,Done=? " +
				"WHERE ItineraryID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateItineraryDetails);
			prep.setString(1, itineraryDetails);
			prep.setString(2, date);
			prep.setString(3, time);
			prep.setBoolean(4, isDone);
			prep.setInt(5, itineraryID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateAllocationDetails(Connection connection, int taskID, String taskDescription, String assignedTo, String dateDue, boolean isDone){
		String updateAllocationDetails =
				"UPDATE AllocationDetails SET TaskDescription=?,AssignedTo=?,DateDue=?,Done=? " +
				"WHERE TaskID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateAllocationDetails);
			prep.setString(1, taskDescription);
			prep.setString(2, assignedTo);
			prep.setString(3, dateDue);
			prep.setBoolean(4, isDone);
			prep.setInt(5, taskID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateFileDetails(Connection connection, int fileID, String fileName, String fileDirectory, String fileDescription){
		String updateFileDetails =
				"UPDATE FileDetails SET FileName=?,FileDirectory=?,FileDescription=? " +
				"WHERE FileID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateFileDetails);
			prep.setString(1, fileName);
			prep.setString(2, fileDirectory);
			prep.setString(3, fileDescription);
			prep.setInt(4, fileID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updatePackingDetails(Connection connection, int packingID, String category, String item, int quantity, String remarks){
		String updatePackingDetails =
				"UPDATE PackingDetails SET Category=?,Item=?,Quantity=?,Remarks=? " +
				"WHERE PackingID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updatePackingDetails);
			prep.setString(1, category);
			prep.setString(2, item);
			prep.setInt(3, quantity);
			prep.setString(4, remarks);
			prep.setInt(5, packingID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateVenueDetails(Connection connection, int venueID, String name, String location, String type){
		String updateVenueDetails =
				"UPDATE VenueDetails SET Name=?,Location=?,Type=? " +
				"WHERE VenueID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateVenueDetails);
			prep.setString(1, name);
			prep.setString(2, location);
			prep.setString(3, type);
			prep.setInt(4, venueID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static void updateOrganizerFacilitatorDetails(Connection connection, int memberID, String name, String matricNo, String faculty, int schoolYear, String contact, String email, String foodType, String allergy, String position){
		String updateOrganizerFacilitatorDetails =
				"UPDATE MemberDetails SET Name=?,MatricNo=?,Faculty=?,SchoolYear=?,Contact=?,Email=?,FoodType=?,Allergy=?,Position=? " +
						"WHERE MemberID=?";
				PreparedStatement prep = null;
				try{
					prep = connection.prepareStatement(updateOrganizerFacilitatorDetails);
					prep.setString(1, name);
					prep.setString(2, matricNo);
					prep.setString(3, faculty);
					prep.setInt(4, schoolYear);
					prep.setString(5, contact);
					prep.setString(6, email);
					prep.setString(7, foodType);
					prep.setString(8, allergy);
					prep.setString(9, position);
					prep.setInt(10, memberID);
					prep.execute();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
	}
	public static void updateParticipantDetails(Connection connection, int memberID, String name, String matricNo, String faculty, int schoolYear, String contact, String email, String foodType, String allergy){
		String updateFacilitatorDetails =
				"UPDATE MemberDetails SET Name=?,MatricNo=?,Faculty=?,SchoolYear=?,Contact=?,Email=?,FoodType=?,Allergy=?" +
						"WHERE MemberID=?";
				PreparedStatement prep = null;
				try{
					prep = connection.prepareStatement(updateFacilitatorDetails);
					prep.setString(1, name);
					prep.setString(2, matricNo);
					prep.setString(3, faculty);
					prep.setInt(4, schoolYear);
					prep.setString(5, contact);
					prep.setString(6, email);
					prep.setString(7, foodType);
					prep.setString(8, allergy);
					prep.setInt(9, memberID);
					prep.execute();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
	}
	public static void updateVenueBookingDetails(Connection connection, int bookingID, int applicantID, String date, String timeStart, String timeEnd, int status){
		String updateVenueBookingDetails =
				"UPDATE VenueBookingDetails SET ApplicantID=?,Date=?,TimeStart=?,TimeEnd=?,Status=? " +
				"WHERE BookingID=?";
		PreparedStatement prep = null;
		try{
			prep = connection.prepareStatement(updateVenueBookingDetails);
			prep.setInt(1, applicantID);
			prep.setString(2, date);
			prep.setString(3, timeStart);
			prep.setString(4, timeEnd);
			prep.setInt(5, status);
			prep.setInt(6, bookingID);
			prep.execute();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	/*Get Object By ID*/
	public static ResultSet getVenueByID(Connection connection, int venueID){
		String query = 
				"SELECT * FROM VenueDetails " +
				"WHERE VenueID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(query);
			prep.setInt(1, venueID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getVenueApplicantByID(Connection connection, int memberID){
		String query = 
				"SELECT * FROM ApplicantDetails " +
				"WHERE ApplicantID=?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try{
			prep = connection.prepareStatement(query);
			prep.setInt(1, memberID);
			rs = prep.executeQuery();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rs;
	}
	
	
	public static void main(String[] args){
		/*Connection con = ConnectionManager.getConnection();
		insertFeedbackDetails(con, 1, "I am here!!!!!","2011-12-11", "12:11:11");*/
		}
}
