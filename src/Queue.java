import java.util.LinkedList;


public class Queue
{
	
	private int customersInQueue = 0;
	private int maxQueueSize = 0;
	private LinkedList<Customer> queue = new LinkedList<Customer>();
	
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
	 * @param Place customer c in line
	 */
	public void addLast(Customer c){
		queue.addLast(c);
	}
	
	/**
	 * @return the first Customer
	 */
	public Customer takeFirst(){
		Customer c = queue.getFirst();
		queue.removeFirst();
		return c;
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
	
	public boolean isQueueEmpty(){
		return(customersInQueue == 0);
	}
	
	public String toString(){
		String out = "";
		int emptySpaces = Main.maxQueueSize - customersInQueue;
		System.out.println("Custumers in queue: " + queue.size());
		System.out.println("Empty spaces: " + emptySpaces);
		for (int i = 0; i < emptySpaces; i++) {
			out += "-";
		}
		int temp = customersInQueue;
		for (int i = 0; i < customersInQueue; i++) {
			
			out += " " + queue.get(temp).getCustomerId();
			temp--;
		}
		out += "|";
		
		
		return out;
	}
}
