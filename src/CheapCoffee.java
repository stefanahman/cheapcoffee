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
		arrival = new Arrival(calculateInterArrivalTime());
		Event event;
		fel.add(arrival);
		time = 0;
		do{
			event = fel.getFirst();
			fel.removeFirst();
			time = event.getTime();
			eventHandler(event);
			if(time > breakTime)
				break;
		}while (felIt.hasNext());
	}

	private void eventHandler(Event ev){

		System.out.println("------------------- Events Start ----------------------");
		System.out.println(service.isBusy() ? "Server is busy" : "Server is idle");
		if(ev instanceof Arrival){
			System.out.println("Customer arrives at time: " + time);
			arrival = new Arrival(time + calculateInterArrivalTime());
			fel.add(arrival);
			if(!service.isBusy()){
				service.setBusy();
				System.out.println("Calculate and add Departure");
				departure = new Departure(time + calculateServiceTime());
				System.out.println("Customer will depart at: " + departure.getTime());
				fel.add(departure);
			} else {
				System.out.println("Place customer in queue");
				//TODO: Add queue funtionality and rejection.
				Main.queue.setCustomersInQueue(Main.queue.getCustomersInQueue() + 1);
			}


		} else if(ev instanceof Departure){
			System.out.println("Customer departs at time: " + time);
			if(!Main.queue.isQueueFull()){
				service.setIdle();
			} else {
				System.out.println("Remove customer from queue");
				//TODO: Add queue funtionality.
				Main.queue.setCustomersInQueue(Main.queue.getCustomersInQueue() - 1);
				System.out.println(Main.queue.toString());
				departure.setTime(time+calculateServiceTime());
				System.out.println("Plan queueing customers departure at: " + departure.getTime());
				fel.add(departure);
			}
		}
		System.out.println(Main.queue.toString());
		System.out.println("------------------- Events done -----------------------");
		System.out.println("");
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
