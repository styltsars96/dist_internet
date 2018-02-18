package entities;




public class Delivery {
	

	private int id;
	
	private Process process;
	
	private double lat;
	
	private double lon;

	private int distance;
	
	private String status;
	
	public Delivery() {}

	
	public Delivery(Process process, double lat, double lon) {
		this.process = process;
		this.lat = lat;
		this.lon = lon;
	}

	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Delivery [process=" + process + ", lat=" + lat + ", lon=" + lon + ", status=" + status + "]";
	}
	
	
}
