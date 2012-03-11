
public class Done {
	private boolean isDone;
	public Done(String string){
		if(string.equals("Done")){
			isDone=true;
		}
		else {
			isDone=false;
		}
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
