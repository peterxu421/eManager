
public class ManpowerAllocation {
	private int taskID;
	private String taskDescription;
	private String assignedTo;
	private Date date;
	private Date dueDate;
	private boolean done;
	public ManpowerAllocation(int taskID, String taskDescription,
			String assignedTo, Date date, Date dueDate, boolean done) {
		super();
		this.taskID = taskID;
		this.taskDescription = taskDescription;
		this.assignedTo = assignedTo;
		this.date = date;
		this.dueDate = dueDate;
		this.done = done;
	}

	public ManpowerAllocation(String taskDescription, String assignedTo,
			Date date, Date dueDate, boolean done) {
		super();
		this.taskDescription = taskDescription;
		this.assignedTo = assignedTo;
		this.date = date;
		this.dueDate = dueDate;
		this.done = done;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
