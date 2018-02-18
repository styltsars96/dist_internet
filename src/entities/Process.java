package entities;

public class Process {


	private int id;

	private Car car;
	
	private Shop shop;
	
	private String status;
	      
	
	public Process() {}
	
	public Process( Car car, String status, Shop shop) {
		super();
		this.car = car;
		this.shop = shop;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Process [id=" + id + ", car=" + car + ", shop=" + shop + ", status=" + status + "]";
	}
}