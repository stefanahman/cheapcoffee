import java.util.LinkedList;


public class FutureEventList extends LinkedList<Event>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FutureEventList() {
		super();
	}
	
	public void insertSorted(Event evt) {
		if(this.isEmpty()){
			this.add(evt);
			System.out.println("aaaaaaaAAAAaaaaaaa");
		}
		else{
			System.out.println("Size: " + (this.size()-1));
			for(int i = this.size()-1; i >= 0; i--) {
				int ans = evt.compareTo(this.get(i));
				System.out.println("Ans: " + ans);
				if(ans >= 0) {
					System.out.println("Add sorted at index:" + i);
					this.add(i+1, evt);
					break;
				}
			}
		}
	}
}