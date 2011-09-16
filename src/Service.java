
public class Service
{	
	private double serviceTime;
	private boolean serverBusy;
	
	public Service(double serviceTime) {
		this.serviceTime = serviceTime;
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
		this.serverBusy = false;
	}
	
}
