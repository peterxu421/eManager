public class Task {
	int taskID = 0;
	private String taskDesciption;
	private String assignedTo;
	private Date dateDue;
	private boolean done;
	public Task(int taskID, String taskDesciption, String assignedTo,
			Date dateDue, boolean done) {
		this.taskID = taskID;
		this.taskDesciption = taskDesciption;
		this.assignedTo = assignedTo;
		this.dateDue = dateDue;
		this.done = done;
	}
	public Task(String taskDesciption, String assignedTo, Date dateDue,
			boolean done) {
		this.taskDesciption = taskDesciption;
		this.assignedTo = assignedTo;
		this.dateDue = dateDue;
		this.done = done;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getTaskDesciption() {
		return taskDesciption;
	}
	public void setTaskDesciption(String taskDesciption) {
		this.taskDesciption = taskDesciption;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Date getDateDue() {
		return dateDue;
	}
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
