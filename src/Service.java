
public class Service
{	
	private double serviceTime;
	private boolean serverBusy;
	private Customer currentCustomer = null;
	
	public Service() {
	}

	public double getServiceTime()
	{
		return serviceTime;
	}
	
	public void setServiceTime(double serviceTime)
	{
		this.serviceTime = serviceTime;
	}
	
	public boolean isBusy()
	{
		return serverBusy;
	}
	
	public void setBusy() 
	{
		this.serverBusy = true;
	}
	
	public void setIdle() 
	{
		this.currentCustomer = null;
		this.serverBusy = false;
	}
	
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

}
