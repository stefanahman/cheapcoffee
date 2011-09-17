import java.util.Iterator;
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
	private FutureEventList fel = new FutureEventList();
	private Iterator<Event> felIt = fel.listIterator();
	private Arrival arrival;
	private Departure departure;
	private Service service = new Service();

	Random rnd = new Random();

	public CheapCoffee(long seed, int simulationLength) {
		System.out.println("#						#");
		this.breakTime = simulationLength;
		rnd.setSeed(seed);
		run();
	}

	private void run() {
		arrival = new Arrival(calculateInterArrivalTime());
		Event event;
		fel.insertSorted(arrival);
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

		
		if(ev instanceof Arrival){ // Om det kommer någon så, skapa kund, beräkna när nästa kommer
			System.out.println("##################### Arrival ###################");
			System.out.println("#						#");
			numberOfCustomers += 1;
			customer = new Customer(numberOfCustomers,time);
			
			System.out.println("# Customer " + customer.getCustomerId() + " arrives at time: " + customer.getArrivalTime());
			
			arrival = new Arrival(time + calculateInterArrivalTime()); // Calculate next customer
			
			fel.insertSorted(arrival);
			
			
			if(!service.isBusy()){ // ..kan kunden gå till kassan direkt
				System.out.println("# Place Customer " + customer.getCustomerId() + " in service");
				service.setBusy();
				service.setCurrentCustomer(customer);
				double serviceTime = calculateServiceTime();
				customer.setServiceTime(serviceTime);
				departure = new Departure(time + serviceTime);
				customer.setDepartureTime(departure.getTime());
				System.out.println("# Customer " + customer.getCustomerId() + " will depart at: " + departure.getTime());
				fel.insertSorted(departure);
			} else { // annars måste han/hon ställa sig i kö
				System.out.println("# Customer "+ service.getCurrentCustomer().getCustomerId() +" is now beeing served.");
				queue.addLast(customer);
				System.out.println("# Place customer in queue");
			}
		} else if(ev instanceof Departure){ // När någon kund lämnar så..
			System.out.println("#################### Departure ##################");
			System.out.println("#						#");
			System.out.println("# Customer " + service.getCurrentCustomer().getCustomerId() + " departs at time: " + service.getCurrentCustomer().getDepartureTime());
			
			if(queue.isQueueEmpty()){ // vänta till det kommer någon ny kund
				service.setIdle();
				System.out.println("# Set idle");
			} else { // ta nästa kund och beräkna när han/hon är klar
				customer = queue.takeFirst();
				service.setCurrentCustomer(customer);
				System.out.println("# Take next customer in queue");
				System.out.println("# Customer "+ service.getCurrentCustomer().getCustomerId() +" is now beeing served.");
				//TODO: Add queue funtionality
				service.setCurrentCustomer(customer);
				double serviceTime = calculateServiceTime();
				customer.setServiceTime(serviceTime);
				departure = new Departure(time + serviceTime);
				customer.setDepartureTime(departure.getTime());
				System.out.println("# Customer " + customer.getCustomerId() + " will depart at: " + departure.getTime());
				fel.insertSorted(departure);
				
			}
			
		}
		System.out.println("# " + queue.toString());
		System.out.println("#						#");
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
