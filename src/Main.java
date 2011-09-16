import java.util.Random;


public class Main
{
	static double IAT = 0.2;
	
	static long seed;
	static int replications;
	static int queue;
	

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	 	seed = (long)Integer.parseInt(args[0]);
	 	replications = Integer.parseInt(args[1]);
	 	queue = Integer.parseInt(args[2]);
		
		System.out.println("**********************************");
		System.out.println("*     Welcome to Cheap Coffe     *");
		System.out.println("**********************************");
		
		System.out.println("Seed: " + seed);
		System.out.println("Replications: " + replications);
		System.out.println("Queue: " + queue);
	 	
	}

}
