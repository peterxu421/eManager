import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseReader {
	private Connection connection = null;

	public DatabaseReader() {
		connection = SQLManager.getConnection();
	}

	/* EventDetails */
	public ArrayList<Event> getEvents() {
		ResultSet rs = SQLManager.getEventDetails(connection);
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			while (rs.next()) {
				Event event = new Event(rs.getInt("EventID"),
						rs.getString("EventName"), rs.getString("EventDescription"), rs.getString("Organizer_Password"), rs.getString("Facilitator_Password"));
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	public void insertEvent(Event event) {
		String eventName = event.getEventName();
		String eventDescription = event.getEventDescription();
		String organizerPassword = event.getOrganizerPassword();
		String facilitatorPassword = event.getFacilitatorPassword();
		int eventID = SQLManager.insertEventDetails(connection, eventName,
				eventDescription, organizerPassword, facilitatorPassword);
		event.setEventID(eventID);
		return;
	}
	public void deleteEvent(Event event) {
		SQLManager.deleteEventDetails(connection, event.getEventID());
	}
	public void updateEvent(Event event){
		SQLManager.updateEventDetails(connection, event.getEventID(), event.getEventName(),
				event.getEventDescription(), event.getOrganizerPassword(), event.getFacilitatorPassword());
	}
	
	/* TaskDetails */
	public ArrayList<Task> getTasks(Event event) {
		ResultSet rs = SQLManager
				.getTaskDetails(connection, event.getEventID());
		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Task task = new Task(rs.getInt("TaskID"),
						rs.getString("TaskDescription"),
						rs.getString("AssignedTo"), date, rs.getBoolean("Done"));
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	public void insertTask(Event event, Task task) {
		int taskID = SQLManager.insertTaskDetails(connection,
				event.getEventID(), task.getTaskDesciption(),
				task.getAssignedTo(), task.getDateDue().toString(),
				task.isDone());
		task.setTaskID(taskID);
	}
	public void deleteTask(Task task) {
		SQLManager.deleteTaskDetails(connection, task.getTaskID());
	}
	public void updateTask(Task task) {
		SQLManager.updateTaskDetails(connection, task.getTaskID(), task.getTaskDesciption(), task.getAssignedTo(), task.getDateDue()
				.toString(), task.isDone());
	}
	
	/* BudgetAllocationDetails */
	public ArrayList<BudgetAllocation> getBudgetAllocation(Event event) {
		ArrayList<BudgetAllocation> budgets = new ArrayList<BudgetAllocation>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getBudgetDetails(connection, event.getEventID());
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				BudgetAllocation budget = new BudgetAllocation(rs.getInt(1),
						rs.getString(3), rs.getString(4), rs.getDouble(5), date);
				budgets.add(budget);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return budgets;
	}
	public void insertBudgetAllocation(Event event, BudgetAllocation budget) {
		int budgetID = SQLManager.insertBudgetDetails(connection, event
				.getEventID(), budget.getItem(), budget.getPersonInCharge(),
				budget.getCost(), budget.getDate().toString());
		budget.setBudgetID(budgetID);
	}
	public void deleteBudgetAllocation(BudgetAllocation budget) {
		SQLManager.deleteBudgetDetails(connection, budget.getBudgetID());
	}
	public void updateBudgetAllocation(BudgetAllocation budget) {
		SQLManager.updateBudgetDetails(connection, budget.getBudgetID(),
				budget.getItem(), budget.getPersonInCharge(), budget.getCost(),
				budget.getDate().toString());
	}

	/* Inflow */
	public ArrayList<Inflow> getInflow(Event event) {
		ArrayList<Inflow> inflows = new ArrayList<Inflow>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getInflowDetails(connection, event.getEventID());
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Inflow inflow = new Inflow(rs.getInt(1), rs.getString(3),
						rs.getDouble(4), date, rs.getString(6));
				inflows.add(inflow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inflows;
	}
	public void insertInflow(Event event, Inflow inflow) {
		int inflowID = SQLManager.insertInflowDetails(connection,
				event.getEventID(), inflow.getSponsor(), inflow.getAmount(),
				inflow.getDate().toString(), inflow.getRemarks());
		inflow.setInflowID(inflowID);
	}
	public void deleteInflow(Inflow inflow) {
		SQLManager.deleteInflowDetails(connection, inflow.getInflowID());
	}
	public void updateInflow(Inflow inflow) {
		SQLManager.updateInflowDetails(connection, inflow.getInflowID(), inflow
				.getSponsor(), inflow.getAmount(), inflow.getDate().toString(),
				inflow.getRemarks());
	}
	
	/* Meeting */
	public ArrayList<Meeting> getMeetings(Event event) {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		ResultSet rs = null;
		rs = SQLManager.getMeetingDetails(connection, event.getEventID());
		try {
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Time time = Time.parseTime(rs.getTime(5).toString());
				Meeting meeting = new Meeting(rs.getInt(1), rs.getString(3),
						date, time, rs.getBoolean(6));
				meetings.add(meeting);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meetings;
	}
	public void insertMeeting(Event event, Meeting meeting) {
		int meetingID = SQLManager.insertMeetingDetails(connection, event
				.getEventID(), meeting.getMeetingDetails(), meeting.getDate()
				.toString(), meeting.getTime().toString(), meeting.isDone());
		meeting.setMeetingID(meetingID);
	}
	public void deleteMeeting(Meeting meeting) {
		SQLManager.deleteMeetingDetails(connection, meeting.getMeetingID());
	}
	public void updateMeeting(Meeting meeting) {
		SQLManager.updateMeetingDetails(connection, meeting.getMeetingID(),
				meeting.getMeetingDetails(), meeting.getDate().toString(),
				meeting.getTime().toString(), meeting.isDone());
	}
	
	/* Outflow */
	public ArrayList<Outflow> getOutflow(Event event) {
		ArrayList<Outflow> outflows = new ArrayList<Outflow>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getOutflowDetails(connection, event.getEventID());
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Outflow outflow = new Outflow(rs.getInt(1), rs.getString(7),
						rs.getInt(3), rs.getString(4), date, rs.getDouble(6));
				outflows.add(outflow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outflows;
	}
	public void insertOutflow(Event event, Outflow outflow) {
		int outflowID = SQLManager.insertOutflowDetails(connection,
				event.getEventID(), outflow.getItem(), outflow.getQuantity(),
				outflow.getType(), outflow.getDate().toString(),
				outflow.getCost());
		outflow.setOutflowID(outflowID);
	}
	public void deleteOutflow(Outflow outflow) {
		SQLManager.deleteOutflowDetails(connection, outflow.getOutflowID());
	}
	public void updateOutflow(Outflow outflow) {
		SQLManager.updateOutflowDetails(connection, outflow.getOutflowID(),
				outflow.getItem(), outflow.getQuantity(), outflow.getType(),
				outflow.getDate().toString(), outflow.getCost());
	}

	/* Feedback */
	public ArrayList<Feedback> getFeedback(Event event) {
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getFeedbackDetails(connection, event.getEventID());
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Time time = Time.parseTime(rs.getTime("Time").toString());
				Feedback feedback = new Feedback(rs.getInt(1), rs.getString(3),
						date, time);
				feedbacks.add(feedback);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feedbacks;
	}
	public void insertFeedback(Event event, Feedback feedback) {
		int feedbackID = SQLManager.insertFeedbackDetails(connection, event
				.getEventID(), feedback.getFeedbackDetails(), feedback
				.getDate().toString(), feedback.getTime().toString());
		feedback.setFeedbackID(feedbackID);
	}
	public void deleteFeedback(Feedback feedback) {
		SQLManager.deleteFeedbackDetails(connection, feedback.getFeedbackID());
	}
	public void updateFeedback(Feedback feedback) {
		SQLManager.updateFeedbackDetails(connection, feedback.getFeedbackID(),
				feedback.getFeedbackDetails(), feedback.getDate().toString(),
				feedback.getTime().toString());
	}
	
	/* Itinerary */
	public ArrayList<Itinerary> getItinerary(Event event) {
		ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getItineraryDetails(connection, event.getEventID());
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("Date").toString());
				Time time = Time.parseTime(rs.getTime("Time").toString());
				Itinerary itinerary = new Itinerary(rs.getInt(1),
						rs.getString(3), date, time, rs.getBoolean(6));
				itineraries.add(itinerary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itineraries;
	}
	public void insertItinerary(Event event, Itinerary itinerary) {
		int itineraryID = SQLManager.insertItineraryDetails(connection, event
				.getEventID(), itinerary.getItineraryDetails(), itinerary
				.getDate().toString(), itinerary.getTime().toString(),
				itinerary.isDone());
		itinerary.setItineraryID(itineraryID);
	}
	public void deleteItinerary(Itinerary itinerary) {
		SQLManager.deleteItineraryDetails(connection,
				itinerary.getItineraryID());
	}
	public void updateItinerary(Itinerary itinerary) {
		SQLManager.updateItineraryDetails(connection,
				itinerary.getItineraryID(), itinerary.getItineraryDetails(),
				itinerary.getDate().toString(), itinerary.getTime().toString(),
				itinerary.isDone());
	}

	/* ManpowerAllocation */
	public ArrayList<ManpowerAllocation> getManpowerAllocation(Event event) {
		ArrayList<ManpowerAllocation> manpowerAllocations = new ArrayList<ManpowerAllocation>();
		ResultSet rs = null;
		try {
			rs = SQLManager
					.getAllocationDetails(connection, event.getEventID());
			while (rs.next()) {
				Date date = Date.parseDate(rs.getDate("DateDue").toString());
				ManpowerAllocation manpowerAllocation = new ManpowerAllocation(
						rs.getInt(1), rs.getString(3), rs.getString(4), date,
						rs.getBoolean(6));
				manpowerAllocations.add(manpowerAllocation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manpowerAllocations;
	}
	public void insertManpowerAllocation(Event event,
			ManpowerAllocation manpowerAllocation) {
		int id = SQLManager.insertAllocationDetails(connection, event
				.getEventID(), manpowerAllocation.getTaskDescription(),
				manpowerAllocation.getAssignedTo(), manpowerAllocation
						.getDate().toString(), manpowerAllocation.isDone());
		manpowerAllocation.setTaskID(id);
	}
	public void deleteManpowerAllocation(ManpowerAllocation manpowerAllocation) {
		SQLManager.deleteAllocationDetails(connection,
				manpowerAllocation.getTaskID());
	}
	public void updateManpowerAllocation(ManpowerAllocation manpowerAllocation) {
		SQLManager.updateAllocationDetails(connection, manpowerAllocation
				.getTaskID(), manpowerAllocation.getTaskDescription(),
				manpowerAllocation.getAssignedTo(), manpowerAllocation
						.getDate().toString(), manpowerAllocation.isDone());
	}

	/* FileDetails */
	public ArrayList<EventFile> getFiles(Event event) {
		ArrayList<EventFile> files = new ArrayList<EventFile>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getFileDetails(connection, event.getEventID());
			while (rs.next()) {
				EventFile file = new EventFile(rs.getInt(1), rs.getString(3),
						rs.getString(4), rs.getString(5));
				files.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return files;
	}
	public void insertFile(Event event, EventFile eventFile) {
		int id = SQLManager.insertFileDetails(connection, event.getEventID(),
				eventFile.getFileName(), eventFile.getFileDirectory(),
				eventFile.getFileDescription());
		eventFile.setFileID(id);
	}
	public void deleteFile(EventFile file) {
		SQLManager.deleteFileDetails(connection, file.getFileID());
	}
	public void updateFile(EventFile file) {
		SQLManager.updateFileDetails(connection, file.getFileID(),
				file.getFileName(), file.getFileDirectory(),
				file.getFileDescription());
	}

	/* PackingDetails */
	public ArrayList<PackingItem> getPackingItems(Event event) {
		ArrayList<PackingItem> packings = new ArrayList<PackingItem>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getPackingDetails(connection, event.getEventID());
			while (rs.next()) {
				PackingItem packing = new PackingItem(rs.getInt(1),
						rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6));
				packings.add(packing);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return packings;
	}
	public void insertPackingItem(Event event, PackingItem packing) {
		int id = SQLManager.insertPackingDetails(connection,
				event.getEventID(), packing.getCategory(), packing.getName(),
				packing.getQuantity(), packing.getRemarks());
		packing.setItemID(id);
	}
	public void deletePackingItem(PackingItem packing) {
		SQLManager.deletePackingDetails(connection, packing.getItemID());
	}
	public void updatePackingItem(PackingItem packing) {
		SQLManager.updatePackingDetails(connection, packing.getItemID(),
				packing.getCategory(), packing.getName(),
				packing.getQuantity(), packing.getRemarks());
	}

	/* VenueDetails */
	public ArrayList<Venue> getVenues() {
		ArrayList<Venue> venues = new ArrayList<Venue>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getVenueDetails(connection);
			while (rs.next()) {
				Venue venue = new Venue(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5));
				venues.add(venue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venues;
	}
	public void insertVenue(Venue venue) {
		int venueID = SQLManager.insertVenueDetails(connection,
				venue.getName(), venue.getLocation(), venue.getType(), venue.getCapacity());
		venue.setVenueId(venueID);
	}
	public void deleteVenue(Venue venue) {
		deleteVenueBookingInfo(venue);
		SQLManager.deleteVenueDetails(connection, venue.getVenueId());
	}
	public void updateVenue(Venue venue) {
		SQLManager.updateVenueDetails(connection, venue.getVenueId(),
				venue.getName(), venue.getLocation(), venue.getType(), venue.getCapacity());
	}
	public Venue getVenueByID(int venueID){
		ResultSet rs = null;
		Venue venue = null;
		try {
			rs = SQLManager.getVenueByID(connection, venueID);
			while (rs.next()) {
				venue = new Venue(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venue;
	}
	
	/* MemberDetails */
	public ArrayList<Organizer> getOrganizers(Event event) {
		ArrayList<Organizer> organizers = new ArrayList<Organizer>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getOrganizerDetails(connection, event.getEventID());
			while (rs.next()) {
				Organizer organizer = new Organizer(rs.getInt("MemberID"),rs.getString("Name"), rs.getString("MatricNo"),rs.getString("Faculty"), rs.getInt("SchoolYear"),rs.getString("Contact"), rs.getString("Email"),rs.getString("FoodType"), rs.getString("Allergy"),rs.getString("Position"));
				organizers.add(organizer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return organizers;
	}
	public ArrayList<Facilitator> getFacilitators(Event event) {
		ArrayList<Facilitator> facilitators = new ArrayList<Facilitator>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getFacilitatorDetails(connection,
					event.getEventID());
			while (rs.next()) {
				Facilitator facilitator = new Facilitator(
						rs.getInt("MemberID"), rs.getString("Name"),
						rs.getString("MatricNo"), rs.getString("Faculty"),
						rs.getInt("SchoolYear"), rs.getString("Contact"),
						rs.getString("Email"), rs.getString("FoodType"),
						rs.getString("Allergy"), rs.getString("Position"));
				facilitators.add(facilitator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facilitators;
	}
	public ArrayList<Participant> getParticipants(Event event) {
		ArrayList<Participant> participants = new ArrayList<Participant>();
		ResultSet rs = null;
		try {
			rs = SQLManager.getParticipantDetails(connection,
					event.getEventID());
			while (rs.next()) {
				Participant participant = new Participant(
						rs.getInt("MemberID"), rs.getString("Name"),
						rs.getString("MatricNo"), rs.getString("Faculty"),
						rs.getInt("SchoolYear"), rs.getString("Contact"),
						rs.getString("Email"), rs.getString("FoodType"),
						rs.getString("Allergy"));
				participants.add(participant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participants;
	}
	public void insertOrganizer(Event event, Organizer organizer) {
		int organizerID = SQLManager.insertOrganizerDetails(connection,
				event.getEventID(), organizer.getName(),
				organizer.getMatricNo(), organizer.getFaculty(),
				organizer.getYear(), organizer.getContact(),
				organizer.getEmail(), organizer.getFoodType(),
				organizer.getAllergy(), organizer.getPosition());
		organizer.setID(organizerID);
	}
	public void insertFacilitator(Event event, Facilitator facilitator) {
		int facilitatorID = SQLManager.insertFacilitatorDetails(connection,
				event.getEventID(), facilitator.getName(),
				facilitator.getMatricNo(), facilitator.getFaculty(),
				facilitator.getYear(), facilitator.getContact(),
				facilitator.getEmail(), facilitator.getFoodType(),
				facilitator.getAllergy(), facilitator.getPosition());
		facilitator.setID(facilitatorID);
	}
	public void insertParticipant(Event event, Participant participant) {
		int participantID = SQLManager.insertParticipantDetails(connection,
				event.getEventID(), participant.getName(),
				participant.getMatricNo(), participant.getFaculty(),
				participant.getYear(), participant.getContact(),
				participant.getEmail(), participant.getFoodType(),
				participant.getAllergy());
		participant.setID(participantID);
	}
	public void updateOrganizer(Organizer member) {
		SQLManager.updateOrganizerFacilitatorDetails(connection,
				member.getID(), member.getName(), member.getMatricNo(),
				member.getFaculty(), member.getYear(), member.getContact(),
				member.getEmail(), member.getFoodType(), member.getAllergy(),
				member.getPosition());
	}
	public void updateFacilitator(Facilitator member) {
		SQLManager.updateOrganizerFacilitatorDetails(connection,
				member.getID(), member.getName(), member.getMatricNo(),
				member.getFaculty(), member.getYear(), member.getContact(),
				member.getEmail(), member.getFoodType(), member.getAllergy(),
				member.getPosition());
	}
	public void updateParticipant(Participant member) {
		SQLManager.updateParticipantDetails(connection, member.getID(),
				member.getName(), member.getMatricNo(), member.getFaculty(),
				member.getYear(), member.getContact(), member.getEmail(),
				member.getFoodType(), member.getAllergy());
	}
	public void deleteMember(Member member) {
		SQLManager.deleteMemberDetails(connection, member.getID());
	}
	
	/*VenueApplicant*/
	public VenueApplicant getVenueApplicantByID(int applicantID){
		ResultSet rs = null;
		VenueApplicant applicant = null;
		try {
			rs = SQLManager.getVenueApplicantByID(connection, applicantID);
			while (rs.next()) {
				applicant = new VenueApplicant(applicantID, rs.getString("Name"),
						rs.getString("MatricNo"),rs.getString("contact"), rs.getString("email"), rs.getString("organization"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant;
	}
	public ArrayList<VenueApplicant> getVenueApplicantByMatricNo(String matricNo){
		ResultSet rs = null;
		VenueApplicant applicant = null;
		ArrayList<VenueApplicant> venueApplicantList = new ArrayList<VenueApplicant>();
		try {
			rs = SQLManager.getVenueApplicantByMatricNo(connection, matricNo);
			while (rs.next()) {
				applicant = new VenueApplicant(rs.getInt("ApplicantID"), rs.getString("Name"),
						matricNo,rs.getString("contact"), rs.getString("email"), rs.getString("organization"));
				venueApplicantList.add(applicant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venueApplicantList;
	}
	public void insertVenueApplicant(VenueApplicant applicant){
		int ID = SQLManager.insertVenueApplicant(connection, applicant.getName(), applicant.getMatricNo(), applicant.getContact(), applicant.getEmail(), applicant.getOrganization());
		applicant.setID(ID);
	}
	
	/*VenueBookingApplication*/
	public ArrayList<VenueBookingApplication> getVenueBookingInfo(Venue venue){
		ArrayList<VenueBookingApplication> bookings = new ArrayList<VenueBookingApplication>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getVenueBookingDetails(connection, venue.getVenueId());
			while(rs.next()){
				VenueApplicant applicant = getVenueApplicantByID(rs.getInt("ApplicantID"));
				BookedDateTime time = new BookedDateTime(Date.parseDate(rs.getString("Date")), Time.parseTime(rs.getString("TimeStart")), 
						Time.parseTime(rs.getString("TimeEnd")));
				VenueBookingApplication booking = new VenueBookingApplication(rs.getInt("BookingID"), venue, applicant, time, rs.getInt("Status"));
				bookings.add(booking);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return bookings;
	}
	public ArrayList<VenueBookingApplication> getVenueBookingInfo(){
		ArrayList<VenueBookingApplication> bookings = new ArrayList<VenueBookingApplication>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getVenueBookingDetailsAll(connection);
			while(rs.next()){
				Venue venue = getVenueByID(rs.getInt("VenueID"));
				VenueApplicant applicant = getVenueApplicantByID(rs.getInt("ApplicantID"));
				BookedDateTime time = new BookedDateTime(Date.parseDate(rs.getString("Date")), Time.parseTime(rs.getString("TimeStart")), 
						Time.parseTime(rs.getString("TimeEnd")));
				VenueBookingApplication booking = new VenueBookingApplication(rs.getInt("BookingID"), venue , applicant, time, rs.getInt("Status"));
				bookings.add(booking);
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return bookings;
	}
	public ArrayList<VenueBookingApplication> getVenueBookingInfoFromToday(Date today){
		ArrayList<VenueBookingApplication> bookings = new ArrayList<VenueBookingApplication>();
		ResultSet rs = null;
		try{
			rs = SQLManager.getVenueBookingDetailsAll(connection);
			while(rs.next()){
				Venue venue = getVenueByID(rs.getInt("VenueID"));
				VenueApplicant applicant = getVenueApplicantByID(rs.getInt("ApplicantID"));
				BookedDateTime time = new BookedDateTime(Date.parseDate(rs.getString("Date")), Time.parseTime(rs.getString("TimeStart")), 
						Time.parseTime(rs.getString("TimeEnd")));
				VenueBookingApplication booking = new VenueBookingApplication(rs.getInt("BookingID"), venue , applicant, time, rs.getInt("Status"));
				if(booking.getDateTime().getDate().isNotEarlierThan(today)){
					bookings.add(booking);
				}
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return bookings;
	}
	public void insertVenueBookingInfo(VenueBookingApplication booking){
		insertVenueApplicant(booking.getApplicant());
		int id = SQLManager.insertVenueBookingDetails(connection, booking.getVenue().getVenueId(), 
				booking.getApplicant().getID(), booking.getDateTime().getDate().toString(), 
				booking.getDateTime().getTimeStart().toString(), booking.getDateTime().getTimeEnd().toString(), booking.getStatus());
		booking.setVenueBookingInfoID(id);
	}
	public void updateVenueBookingInfo(VenueBookingApplication booking){
		SQLManager.updateVenueBookingDetails(connection, booking.getVenueBookingInfoID(), 
				booking.getApplicant().getID(), booking.getDateTime().getDate().toString(),
				booking.getDateTime().getTimeStart().toString(),
				booking.getDateTime().getTimeEnd().toString(), booking.getStatus());
	}
	public void deleteVenueBookingInfo(Venue venue){
		SQLManager.deleteVenueBookingDetailsByVenue(connection, venue.getVenueId());
	}
	public void deleteVenueBookingInfo(VenueBookingApplication booking){
		SQLManager.deleteVenueBookingDetails(connection, booking.getVenueBookingInfoID());
	}
	public VenueBookingApplication getVenueBookingInfo(VenueApplicant applicant){
		ResultSet rs = null;
		VenueBookingApplication booking = new VenueBookingApplication();
		try{
			rs = SQLManager.getVenueBookingDetailsByApplicant(connection, applicant.getID());
			while(rs.next()){
				Venue venue = getVenueByID(rs.getInt("VenueID"));
				BookedDateTime time = new BookedDateTime(Date.parseDate(rs.getString("Date")), Time.parseTime(rs.getString("TimeStart")), 
						Time.parseTime(rs.getString("TimeEnd")));
				booking = new VenueBookingApplication(rs.getInt("BookingID"), venue, applicant, time, rs.getInt("Status"));
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return booking;
	} 
	public static void main(String[] args){
		DatabaseReader db = new DatabaseReader();
		Event event = db.getEvents().get(0);
		int size = db.getFacilitators(event).size();
		System.out.println(size);
	}
}