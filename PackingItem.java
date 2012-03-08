public class PackingItem {
	private int ItemID;
	private String Category;
	private String Name;
	private int quantity;
	private String Remarks;

	public PackingItem(String category, String name, int quantity,
			String remarks) {
		Category = category;
		Name = name;
		this.quantity = quantity;
		Remarks = remarks;
	}

	public PackingItem(int itemID, String category, String name, int quantity,
			String remarks) {
		ItemID = itemID;
		Category = category;
		Name = name;
		this.quantity = quantity;
		Remarks = remarks;
	}

	public int getItemID() {
		return ItemID;
	}

	public void setItemID(int itemID) {
		ItemID = itemID;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

}
