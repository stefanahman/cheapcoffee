import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

public class CheapCoffee
{
	final double ARRIVALRATE = 0.2;
	final double SERVICERATE = 0.25;
	private double time;
	private LinkedList<Event> fel = new LinkedList<Event>();
	private Iterator<Event> felIt = fel.iterator();
	
	Random rnd = new Random();
	
	public CheapCoffee(long seed) {
		rnd.setSeed(seed);
	}
	
	private void run() {
		Arrival arrival = new Arrival(calculateInterArrivalTime());
		Event event = new Event();
		fel.add(arrival);
		time = 0;
		do {
			event = felIt.next();
			time = event.getTime();
			eventHandler(event);
		} while (felIt.hasNext());
	}
	
	private void eventHandler(Event ev) {
		
	}
	
	public double calculateInterArrivalTime() {
		double iat = -Math.log(rnd.nextDouble())/ARRIVALRATE;
		return iat;
	}
	public double calculateServiceTime() {
		double ist = -Math.log(rnd.nextDouble())/SERVICERATE;
		return ist;
	}

}
