import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class CheapCoffee
{
	final double ARRIVALRATE = 0.2;
	final double SERVICERATE = 0.25;
	private double time;
	private LinkedList<Event> fel = new LinkedList<Event>();
	private Iterator<Event> felIt = fel.iterator();
	private Arrival arrival;
	private Departure departure;
	private Service service = new Service(calculateServiceTime());
	
	Random rnd = new Random();
	
	public CheapCoffee(long seed) {
		rnd.setSeed(seed);
		run();
	}
	
	private void run() {
		arrival = new Arrival(calculateInterArrivalTime());
		Event event = new Event();
		fel.add(arrival);
		time = 0;
		do {
			event = felIt.next();
			time = event.getTime();
			eventHandler(event);
		} while (felIt.hasNext());
	}
	
	private void eventHandler(Event ev){
		if(ev instanceof Arrival){
			arrival = new Arrival(time + calculateInterArrivalTime());
			fel.add(arrival);
			if(!service.isBusy()){
				service.setBusy();
				departure = new Departure(time + service.getServiceTime());
				fel.add(departure);
			} else {
				Main.queue.setCustomersInQueue(Main.queue.getCustomersInQueue() + 1);
			}
		} else if(ev instanceof Departure){
			if(!Main.queue.isQueueFull()){
				service.setIdle();
			} else {
				Main.queue.setCustomersInQueue(Main.queue.getCustomersInQueue() - 1);
				departure.setTime(time+service.getServiceTime());
				fel.add(departure);
				time = time + service.getServiceTime();
			}
		}
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
