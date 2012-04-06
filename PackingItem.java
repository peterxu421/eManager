public class PackingItem {
	private int itemID;
	private String category;
	private String name;
	private int quantity;
	private String remarks;

	public PackingItem(String category, String name, int quantity,
			String remarks) {
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.remarks = remarks;
	}

	public PackingItem(int itemID, String category, String name, int quantity,
			String remarks) {
		this.itemID = itemID;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.remarks = remarks;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
