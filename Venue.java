
public class Venue {
	private int venueId;
	private String name;
	private String location;
	private String type;
	private int capacity;
	
	public Venue() {
		super();
	}

	public Venue(int venueId, String name, String location, String type, int capacity) {
		this.venueId = venueId;
		this.name = name;
		this.location = location;
		this.type = type;
		this.capacity = capacity;
	}
	
	public Venue(String name, String location, String type, int capacity) {
		this.name = name;
		this.location = location;
		this.type = type;
		this.capacity = capacity;
	}
	public int getVenueId() {
		return venueId;
	}
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
	

