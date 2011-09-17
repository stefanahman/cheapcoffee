
public class Customer
{
	private int id;
	private double arrivalTime;
	private double queueTime;
	private double serviceTime;
	private double departureTime;

	public Customer(int id,double time) {
		super();
		this.id = id;
		this.arrivalTime = time;
	}
	
	public int getId() {
		return id;
	}

	public double getArrivalTime() {
		return arrivalTime;
	}

	public double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public double getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(double departureTime) {
		this.departureTime = departureTime;
	}

	public int getCustomerId() {
		return id;
	}

	public double getQueueTime()
	{
		return queueTime;
	}

	public void setQueueTime(int queueTime)
	{
		this.queueTime = queueTime;
	}

}
