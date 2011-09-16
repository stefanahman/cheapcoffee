
public class Queue
{		
	
	private int customersInQueue = 0;
	private int maxQueueSize = 0;
	
	public Queue(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}

	/**
	 * @return the customersInQueue
	 */
	public int getCustomersInQueue() {
		return customersInQueue;
	}
	
	/**
	 * @return the maxQueueSize
	 */
	public int getMaxQueueSize() {
		return maxQueueSize;
	}
	
	/**
	 * @param customersInQueue the customersInQueue to set
	 */
	public void setCustomersInQueue(int customersInQueue) {
		this.customersInQueue = customersInQueue;
	}
	
	/**
	 * @param maxQueueSize the maxQueueSize to set
	 */
	public void setMaxQueueSize(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}
	
	public boolean isQueueInf(){
		return(this.maxQueueSize == -1);
	}
	
	public boolean isQueueFull(){
		if(isQueueInf())
			return false;
		else
			return(this.customersInQueue >= this.maxQueueSize);
	}
}
