import java.util.LinkedList;


public class Queue
{
	private int maxQueueSize = 0;
	private LinkedList<Customer> queue = new LinkedList<Customer>();
	
	public Queue(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
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
		Customer c = queue.removeFirst();
		return c;
	}
	
	/**
	 * @return the maxQueueSize
	 */
	public int getMaxQueueSize() {
		return maxQueueSize;
	}
	
	/**
	 * @param maxQueueSize the maxQueueSize to set
	 */
	public void setMaxQueueSize(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}
	
	public boolean isQueueInf(){
		return(maxQueueSize == -1);
	}
	
	public boolean isQueueFull(){
		if(isQueueInf())
			return false;
		else
			return(queue.size() >= maxQueueSize);
	}
	
	public boolean isQueueEmpty(){
		return(queue.size() == 0);
	}
	
	public String toString(Customer c){
		String out = "";
		int emptySpaces = Main.maxQueueSize - queue.size();
		for (int i = 0; i < emptySpaces; i++) {
			out += "- ";
		}
		int temp = queue.size();
		for (int i = 0; i < queue.size(); i++) {
			out += queue.get(temp-1).getCustomerId() + " ";
			temp--;
		}
		out += "| ";
		if(c != null)
			out += c.getCustomerId();
		else
			out += "Idle";
		
		return out;
	}
}
