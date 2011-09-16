import java.util.Random;

public class Main
{
	
	static long seed;
	static int replications;
	static int maxQueueSize;
	static CheapCoffee[] simulation = new CheapCoffee[replications];
	static Queue queue;
	static private Random rnd = new Random();
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	 	seed = (long)Integer.parseInt(args[0]);
	 	replications = Integer.parseInt(args[1]);
	 	maxQueueSize = Integer.parseInt(args[2]);
		
		System.out.println("##################################");
		System.out.println("#     Welcome to Cheap Coffe     #");
		System.out.println("##################################");
		
		System.out.println("Seed: " + seed);
		System.out.println("Replications: " + replications);
		System.out.println("Queue: " + maxQueueSize);
		
		rnd.setSeed(seed);
		
		queue = new Queue(maxQueueSize);
	 	
		for (int i = 0; i < replications; i++) {
			simulation[i] = new CheapCoffee(rnd.nextLong());
		}
	}

}
