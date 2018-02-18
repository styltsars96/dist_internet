package entities;

public class Shop {
	
	
	private int id;

	private double lon;
	
	private double lat;
	
	private int telephone;
	
	public Shop() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public double getLon() {
		return lon;
	}

	public double getLat() {
		return lat;
	}

	public int getTelephone() {
		return telephone;
	}
}
