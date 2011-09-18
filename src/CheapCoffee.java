import java.util.Iterator;
import java.util.Random;

public class CheapCoffee
{
	final double ARRIVALRATE = 0.2;
	final double SERVICERATE = 0.25;
	private double time;
	private double breakTime;
	private int rejectedCustomers = 0;
	private int totalCustomers = 0;
	private double totalQueueTime = 0;
	private double percentRejected = 0;
	private int customersInQueue = 0;
	private Queue queue = new Queue(Main.maxQueueSize);
	private Customer customer;
	private FutureEventList fel = new FutureEventList();
	private Iterator<Event> felIt = fel.listIterator();
	private Arrival arrival;
	private Departure departure;
	private Service service = new Service();
	private Event currentEvent;

	Random rnd = new Random();

	public CheapCoffee(long seed, int simulationLength) {
		System.out.println("");
		this.breakTime = simulationLength;
		rnd.setSeed(seed);
		run();
	}

	private void run() {
		time = 0;
		arrival = new Arrival(calculateInterArrivalTime()); // räkna ut när första kommer
		fel.insertSorted(arrival); // sätt in eventet i future event list
		do{
			currentEvent = fel.getFirst();
			time = currentEvent.getTime();
			
			eventHandler(currentEvent);

			fel.removeFirst();
			if((time > breakTime) && (fel.size() <= 0))
				break;
		}while (felIt.hasNext());
	}

	private void eventHandler(Event ev){
		if(ev instanceof Arrival){ // Om det kommer någon så, skapa kund, beräkna när nästa kommer
			System.out.println("##################### Arrival ###################");
			System.out.println("");
			totalCustomers += 1;
			customer = new Customer(totalCustomers,time);
			
			System.out.println("  Customer " + customer.getCustomerId() + " arrives at time: " + customer.getArrivalTime());
			
			arrival = new Arrival(time + calculateInterArrivalTime()); // Calculate next customer
			if(breakTime >= arrival.getTime())
				fel.insertSorted(arrival);
			
			
			if(!service.isBusy()){ // ..kan kunden gå till kassan direkt
				System.out.println("  Place Customer " + customer.getCustomerId() + " in service");
				service.setBusy();
				service.setCurrentCustomer(customer);
				double serviceTime = calculateServiceTime();
				customer.setServiceTime(serviceTime);
				departure = new Departure(time + serviceTime);
				customer.setDepartureTime(departure.getTime());
				System.out.println("  Customer " + customer.getCustomerId() + " will depart at: " + departure.getTime());
				fel.insertSorted(departure);
				System.out.println("  Customers in FEL: " + fel.size());
			} else { // annars måste han/hon ställa sig i kö
				System.out.println("  Customer "+ service.getCurrentCustomer().getCustomerId() +" is now beeing served.");
				if(!queue.isQueueFull()){
					queue.addLast(customer);
					customer.setStartQueueTime(time);
					System.out.println("  Place customer in queue");
				} else {
					rejectedCustomers += 1;
				}
			}
		} else if(ev instanceof Departure){ // När någon kund lämnar så..
			System.out.println("#################### Departure ##################");
			System.out.println("");
			System.out.println("  Customer " + service.getCurrentCustomer().getCustomerId() + " departs at time: " + service.getCurrentCustomer().getDepartureTime());
			if(queue.isQueueEmpty()){ // vänta till det kommer någon ny kund
				service.setIdle();
				System.out.println("  Set idle");
			} else { // ta nästa kund och beräkna när han/hon är klar
				customer = queue.takeFirst();
				customer.setEndQueueTime(time);
				service.setCurrentCustomer(customer);
				System.out.println("  Take next customer in queue");
				customersInQueue += 1;
				totalQueueTime += customer.getQueueTime();
				
				System.out.println("  Customer " + customer.getCustomerId() + " queuing time: " + customer.getQueueTime());
				System.out.println("  Customer "+ service.getCurrentCustomer().getCustomerId() +" is now beeing served.");
				service.setCurrentCustomer(customer);
				double serviceTime = calculateServiceTime();
				customer.setServiceTime(serviceTime);
				departure = new Departure(time + serviceTime);
				customer.setDepartureTime(departure.getTime());
				System.out.println("  Customer " + customer.getCustomerId() + " will depart at: " + departure.getTime());
				fel.insertSorted(departure);
				
			}
			
		}
		System.out.println("  Rejected customers: " + rejectedCustomers);
		System.out.println("  Total customers: " + totalCustomers);
		System.out.println("  Total customers in queue: " + customersInQueue);
		System.out.println("  Total queue time: " + totalQueueTime);
		System.out.println("  Average queue time: " + totalQueueTime/customersInQueue);
		percentRejected = (double) rejectedCustomers/totalCustomers;
		System.out.println("  Percent rejected: " + String.format("%.5g", 100*percentRejected) + "%");
		System.out.println("  " + queue.toString(service.getCurrentCustomer()));
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
