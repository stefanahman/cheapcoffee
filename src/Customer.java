
public class Customer extends Entity
{
	private long timeEnterCafe;
	private int queueTime;

	public Customer(long time) {
		super();
		this.timeEnterCafe = time;
	}

	public long getTimeEnterCafe()
	{
		return timeEnterCafe;
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
