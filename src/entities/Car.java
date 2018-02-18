package entities;




public class Car {


	private int id;
	
	private Customer customer;

	private String model;
	
	private String licencePlate;

	private String fuelType;
	
	private int firstRelease;
	
	private String status;

	private Integer discount;
	
	public Car() {}
	
	public Car(Customer customer, String model, String licencePlate, String fuelType, int firstRelease, String status) {
		super();
		this.customer = customer;
		this.model = model;
		this.licencePlate = licencePlate;
		this.fuelType = fuelType;
		this.firstRelease = firstRelease;
		this.status = status;
		this.discount = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getFirstRelease() {
		return firstRelease;
	}

	public void setFirstRelease(int firstRelease) {
		if (firstRelease < 1900 || firstRelease > 10000) System.out.println("CAR: Problem with firstRelease...");
		this.firstRelease = firstRelease;
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getDiscount() {
		return this.discount;
	}
	
	public void setDiscount(int disc) {
		this.discount = disc;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", customer=" + customer + ", model=" + model + ", licencePlate=" + licencePlate
				+ ", fuelType=" + fuelType + ", firstRelease=" + firstRelease + ", status=" + status + "]";
	}
	
	
}
