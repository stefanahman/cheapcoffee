
public class Customer extends Entity
{
	private long arrivalTime;
	private int queueTime;

	public Customer(long time) {
		super();
		this.arrivalTime = time;
	}

	public long getTimeEnterCafe()
	{
		return arrivalTime;
	}

	public int getQueueTime()
	{
		return queueTime;
	}

	public void setQueueTime(int queueTime)
	{
		this.queueTime = queueTime;
	}

}
