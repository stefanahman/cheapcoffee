import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class CheapCoffee
{
	final double ARRIVALRATE = 0.2;
	final double SERVICERATE = 0.25;
	private double time;
	private double breakTime;
	private LinkedList<Event> fel = new LinkedList<Event>();
	private Iterator<Event> felIt = fel.listIterator();
	private Arrival arrival;
	private Departure departure;
	private Service service = new Service();

	Random rnd = new Random();

	public CheapCoffee(long seed, int simulationLength) {
		System.out.println("Cheap Coffee is alive!");
		this.breakTime = simulationLength;
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
		do{
			event = fel.getFirst();
			fel.removeFirst();
			time = event.getTime();
			System.out.println("OK " + time);
			eventHandler(event);
			break;
		}while (felIt.hasNext());
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
		System.out.println("Events done");
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
