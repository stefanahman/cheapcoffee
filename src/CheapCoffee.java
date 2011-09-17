import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class CheapCoffee
{
	final double ARRIVALRATE = 0.2;
	final double SERVICERATE = 0.25;
	private double time;
	private LinkedList<Event> fel = new LinkedList<Event>();
	private Iterator<Event> felIt = fel.listIterator();
	private Arrival arrival;
	private Departure departure;
	private Service service = new Service();
	
	Random rnd = new Random();
	
	public CheapCoffee(long seed) {
		System.out.println("Cheap Coffee is alive!");
		rnd.setSeed(seed);
		run();
	}
	
	private void run() {
		System.out.println("Run");
		arrival = new Arrival(calculateInterArrivalTime());
		System.out.println("Time of first arrival: " + arrival.getTime());
		Event event;
		fel.add(arrival);
		time = 0;
		while (felIt.hasNext()){
			System.out.println("Collect event from FEL!");
			event = fel.getFirst();
			fel.removeFirst();
			System.out.println("OK");
			System.out.println("Get time from event");
			time = event.getTime();
			System.out.println("OK " + time);
			eventHandler(event);
		}
		System.out.println("done");
	}
	
	private void eventHandler(Event ev){
		System.out.println("Handle events!!!");
		if(ev instanceof Arrival){
			System.out.println("Customer arrives at time: " + time);
			arrival = new Arrival(time + calculateInterArrivalTime());
			fel.add(arrival);
			if(!service.isBusy()){
				service.setBusy();
				System.out.println("Departure");
				departure = new Departure(time + service.getServiceTime());
				fel.add(departure);
			} else {
				System.out.println("Busy");
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
		double rndSeed = rnd.nextDouble();
		double iat = -Math.log(rndSeed)/ARRIVALRATE;
		return iat;
	}
	public double calculateServiceTime() {
		double rndSeed = rnd.nextDouble();
		double ist = -Math.log(rndSeed)/SERVICERATE;
		return ist;
	}

}
