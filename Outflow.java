
public class Outflow{
	private int outflowID;
	private String item;
	private int quantity;
	private String type;
	private Date date;
	private double cost;
	public Outflow(int outflowID, String item, int quantity, String type,
			Date date, double cost) {
		super();
		this.outflowID = outflowID;
		this.item = item;
		this.quantity = quantity;
		this.type = type;
		this.date = date;
		this.cost = cost;
	}
	public Outflow(String item, int quantity, String type, Date date,
			double cost) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.type = type;
		this.date = date;
		this.cost = cost;
	}
	public int getOutflowID() {
		return outflowID;
	}
	public void setOutflowID(int outflowID) {
		this.outflowID = outflowID;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}