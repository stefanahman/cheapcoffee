import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class CheapCoffee
{
	final double ARRIVALRATE = 0.2;
	final double SERVICERATE = 0.25;
	private double time;
	private double breakTime;
	private Queue queue = new Queue(Main.maxQueueSize);
	private Customer customer;
	private int numberOfCustomers = 0;
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
		if(ev instanceof Arrival){ // Om det kommer någon så, skapa kund, beräkna när nästa kommer
			numberOfCustomers += 1;
			customer = new Customer(numberOfCustomers,time);
			
			System.out.println("Customer " + customer.getCustomerId() + " arrives at time: " + customer.getArrivalTime());
			
			arrival = new Arrival(time + calculateInterArrivalTime()); // Calculate next customer
			
			fel.add(arrival);
			
			
			if(!service.isBusy()){ // ..kan kunden gå till kassan direkt
				System.out.println("Place customer in service");
				service.setBusy();
				
				double serviceTime = calculateServiceTime();
				customer.setServiceTime(serviceTime);
				departure = new Departure(time + serviceTime);
				customer.setDepartureTime(departure.getTime());
				System.out.println("Customer " + customer.getCustomerId() + " will depart at: " + departure.getTime());
				fel.add(departure);
			} else { // annars måste han/hon ställa sig i kö
				System.out.println("Place customer in queue");
				queue.addLast(customer);
			}
		} else if(ev instanceof Departure){ // När någon kund lämnar så..
			
			customer = queue.takeFirst();
			System.out.println("Customer departs at time: " + customer.getDepartureTime());
			if(queue.isQueueEmpty()){ // vänta till det kommer någon ny kund
				service.setIdle();
				System.out.println("Set idle");
			} else { // ta nästa kund och beräkna när han/hon är klar
				System.out.println("Take next customer in queue");
				
				//TODO: Add queue funtionality
				
				double serviceTime = calculateServiceTime();
				customer.setServiceTime(serviceTime);
				departure = new Departure(time + serviceTime);
				customer.setDepartureTime(departure.getTime());
				System.out.println("Plan queueing customers departure at: " + departure.getTime());
				fel.add(departure);
				
			}
			
		}
		System.out.println(queue.toString());
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
