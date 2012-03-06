import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseReader{
	private Connection connection = null;
	
	public DatabaseReader()
	{
		connection = ConnectionManager.getConnection();
	}
	
	/*EventDetails*/
	public ArrayList<Event> getEvents(){
		ResultSet rs = SQLManager.getEventDetails(connection);
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			while(rs.next()){
				Event event = new Event(rs.getInt("EventID"), rs.getString("EventName"),rs.getString(3));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	public void insertEvent(Event event){
		String eventName = event.getEventName();
		String eventDescription = event.getEventDescription();
		int eventID = SQLManager.insertEventDetails(connection, eventName, eventDescription);
		event.setEventID(eventID);
		return;
	}
	public void deleteEvent(Event event){
		SQLManager.deleteEventDetails(connection, event.getEventID());
	}
	
	/*TaskDetails*/
	public ArrayList<Task> getTasks(Event event){
		ResultSet rs = SQLManager.getTaskDetails(connection, event.getEventID());
		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Task task = new Task(rs.getInt("TaskID"), rs.getString("TaskDescription"), rs.getString("AssignedTo"), date, rs.getBoolean("Done"));
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}//check
	public void insertTask(Event event, Task task){
		int taskID = SQLManager.insertTaskDetails(connection, event.getEventID(), task.getTaskDesciption(), task.getAssignedTo(), task.getDateDue().toString(), task.isDone());
		task.setTaskID(taskID);
	}
	public void deleteTask(Task task){
		SQLManager.deleteTaskDetails(connection, task.getTaskID());
	}
	public void updateTask(Task task){
		SQLManager.updateTaskDetails(connection, task.getTaskID(), task.getTaskDesciption(), task.getAssignedTo(), task.getDateDue().toString(), task.isDone());
	}
	
	/*BudgetAllocationDetails*/
	public ArrayList<BudgetAllocation> getBudgetAllocation(Event event){
		ArrayList<BudgetAllocation> budgets = new ArrayList<BudgetAllocation>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getBudgetDetails(connection, event.getEventID());
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				BudgetAllocation budget = new BudgetAllocation(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDouble(5), date);
				budgets.add(budget);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return budgets;
	}
	public void insertBudgetAllocation(Event event, BudgetAllocation budget){
		int budgetID = SQLManager.insertBudgetDetails(connection, event.getEventID(), budget.getItem(), budget.getPersonInCharge(), budget.getCost(), budget.getDate().toString());
		budget.setBudgetID(budgetID);
	}
	public void deleteBudgetAllocation(BudgetAllocation budget){
		SQLManager.deleteBudgetDetails(connection, budget.getBudgetID());
	}
	public void updateBudgetAllocation(BudgetAllocation budget){
		SQLManager.updateBudgetDetails(connection, budget.getBudgetID(), budget.getItem(), budget.getPersonInCharge(), budget.getCost(), budget.getDate().toString());
	}
	
	/*Inflow*/
	public ArrayList<Inflow> getInflow(Event event){
		ArrayList<Inflow> inflows = new ArrayList<Inflow>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getInflowDetails(connection, event.getEventID());
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Inflow inflow = new Inflow(rs.getInt(1), rs.getString(3), rs.getDouble(4), date, rs.getString(6));
				inflows.add(inflow);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return inflows;
	}
	public void insertInflow(Event event, Inflow inflow){
		int inflowID = SQLManager.insertInflowDetails(connection, event.getEventID(), inflow.getSponsor(), inflow.getAmount(), inflow.getDate().toString(), inflow.getRemarks());
		inflow.setInflowID(inflowID);
	}
	public void deleteInflow(Inflow inflow){
		SQLManager.deleteInflowDetails(connection, inflow.getInflowID());
	}
	public void updateInflow(Inflow inflow){
		SQLManager.updateInflowDetails(connection, inflow.getInflowID(), inflow.getSponsor(), inflow.getAmount(), inflow.getDate().toString(), inflow.getRemarks());
	}
	
	/*Meeting*/
	public ArrayList<Meeting> getMeetings(Event event){
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		ResultSet rs = null;
		rs = SQLManager.getMeetingDetails(connection, event.getEventID());
		try {
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Time time = Time.parseTime(rs.getTime(5).toString());
				Meeting meeting = new Meeting(rs.getInt(1), rs.getString(3), date, time, rs.getBoolean(6));
				meetings.add(meeting);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meetings;
	}
	public void insertMeeting(Event event, Meeting meeting){
		int meetingID = SQLManager.insertMeetingDetails(connection, event.getEventID(), meeting.getMeetingDetails(), meeting.getDate().toString(), meeting.getTime().toString(), meeting.isDone());
		meeting.setMeetingID(meetingID);
	}
	public void deleteMeeting(Meeting meeting){
		SQLManager.deleteMeetingDetails(connection, meeting.getMeetingID());
	}
	public void updateMeeting(Meeting meeting){
		SQLManager.updateMeetingDetails(connection, meeting.getMeetingID(), meeting.getMeetingDetails(), meeting.getDate().toString(), meeting.getTime().toString(), meeting.isDone());
	}  
	
	/*Outflow*/
	public ArrayList<Outflow> getOutflow(Event event){
		ArrayList<Outflow> outflows = new ArrayList<Outflow>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getOutflowDetails(connection, event.getEventID());
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Outflow outflow = new Outflow(rs.getInt(1), rs.getString(7), rs.getInt(3), rs.getString(4), date, rs.getDouble(6));
				outflows.add(outflow);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return outflows;
	}
	public void insertOutflow(Event event, Outflow outflow){
		int outflowID = SQLManager.insertOutflowDetails(connection, event.getEventID(), outflow.getItem(), outflow.getQuantity(), outflow.getType(), outflow.getDate().toString(),outflow.getCost());
		outflow.setOutflowID(outflowID);
	}
	public void deleteOutflow(Outflow outflow){
		SQLManager.deleteOutflowDetails(connection, outflow.getOutflowID());
	}
	public void updateOutflow(Outflow outflow){
		SQLManager.updateOutflowDetails(connection, outflow.getOutflowID(), outflow.getItem(), outflow.getQuantity(), outflow.getType(), outflow.getDate().toString(), outflow.getCost());
	}
	
	/*Feedback*/
	public ArrayList<Feedback> getFeedback(Event event){
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getFeedbackDetails(connection, event.getEventID());
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Time time = Time.parseTime(rs.getTime("Time").toString());
				Feedback feedback = new Feedback(rs.getInt(1), rs.getString(3), date, time);
				feedbacks.add(feedback);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return feedbacks;
	}
	public void insertFeedback(Event event, Feedback feedback){
		int feedbackID = SQLManager.insertFeedbackDetails(connection, event.getEventID(), feedback.getFeedbackDetails(), feedback.getDate().toString(), feedback.getTime().toString());
		feedback.setFeedbackID(feedbackID);
	}
	public void deleteFeedback(Feedback feedback){
		SQLManager.deleteFeedbackDetails(connection, feedback.getFeedbackID());
	}
	public void updateFeedback(Feedback feedback){
		SQLManager.updateFeedbackDetails(connection, feedback.getFeedbackID(), feedback.getFeedbackDetails(), feedback.getDate().toString(), feedback.getTime().toString());
	}
	
	/*Itinerary*/
	public ArrayList<Itinerary> getItinerary(Event event){
		ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getItineraryDetails(connection, event.getEventID());
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Time time = Time.parseTime(rs.getTime("Time").toString());
				Itinerary itinerary = new Itinerary(rs.getInt(1), rs.getString(3), date, time,rs.getBoolean(6));
				itineraries.add(itinerary);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return itineraries;
	}
	public void insertItinerary(Event event, Itinerary itinerary){
		int itineraryID = SQLManager.insertItineraryDetails(connection, event.getEventID(), itinerary.getItineraryDetails(), itinerary.getDate().toString(), itinerary.getTime().toString(), itinerary.isDone());
		itinerary.setItineraryID(itineraryID);
	}
	public void deleteItinerary(Itinerary itinerary){
		SQLManager.deleteItineraryDetails(connection, itinerary.getItineraryID());
	}
	public void updateItinerary(Itinerary itinerary){
		SQLManager.updateItineraryDetails(connection, itinerary.getItineraryID(), itinerary.getItineraryDetails(), itinerary.getDate().toString(), itinerary.getTime().toString(), itinerary.isDone());
	}
	
	/*ManpowerAllocation*/
	public ArrayList<ManpowerAllocation> getManpowerAllocation(Event event){
		ArrayList<ManpowerAllocation> manpowerAllocations = new ArrayList<ManpowerAllocation>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getAllocationDetails(connection, event.getEventID());
			while(rs.next()){
				Date date = Date.parseDate(rs.getDate("DateDue").toString());
				ManpowerAllocation manpowerAllocation = new ManpowerAllocation(rs.getInt(1), rs.getString(3), rs.getString(4), date, rs.getBoolean(6));
				manpowerAllocations.add(manpowerAllocation);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return manpowerAllocations;
	}
	public void insertManpowerAllocation(Event event, ManpowerAllocation manpowerAllocation){
		int id = SQLManager.insertAllocationDetails(connection, event.getEventID(), manpowerAllocation.getTaskDescription(), manpowerAllocation.getAssignedTo(), manpowerAllocation.getDate().toString(), manpowerAllocation.isDone());
		manpowerAllocation.setTaskID(id);
	}
	public void deleteManpowerAllocation(ManpowerAllocation manpowerAllocation){
		SQLManager.deleteAllocationDetails(connection, manpowerAllocation.getTaskID());
	}
	public void updateManpowerAllocation(ManpowerAllocation manpowerAllocation){
		SQLManager.updateAllocationDetails(connection, manpowerAllocation.getTaskID(), manpowerAllocation.getTaskDescription(), manpowerAllocation.getAssignedTo(), manpowerAllocation.getDate().toString(), manpowerAllocation.isDone());
	}
	
	public static void main(String[] args){
		DatabaseReader db = new DatabaseReader();
		Event event = db.getEvents().get(0);
		ManpowerAllocation manpowerAllocation = new ManpowerAllocation("BACK AGAIN", "GUAN YILUN", new Date("2012-03-03"),false);
		db.insertManpowerAllocation(event, manpowerAllocation);
		manpowerAllocation = db.getManpowerAllocation(event).get(0);
		int size = db.getManpowerAllocation(event).size();
		System.out.println(size + manpowerAllocation.getTaskDescription());
		//System.out.println(itinerary.getItineraryDetails());
		//itinerary.setItineraryDetails("UPDATE IS SUCCESSFUL");
		//db.updateFeedback(itinerary);
	}
}