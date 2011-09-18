import java.util.Random;

public class Main
{
	
	static long seed = 0;
	static int replications = 0;
	static int maxQueueSize = 0;
	static int modelTime = 0;
	static CheapCoffee[] simulation;
	static private Random rnd = new Random();
	static private int sumTotalCustomers = 0;
	static private double sumAverageQueueTime = 0;
	static private double sumPercentRejected = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	 	seed = (long)Integer.parseInt(args[0]);
	 	replications = Integer.parseInt(args[1]);
	 	maxQueueSize = Integer.parseInt(args[2]);
	 	modelTime = Integer.parseInt(args[3]);
		
		System.out.println("#################################################");
		System.out.println("#						#");
		System.out.println("# Welcome to Cheap Coffee!			#");
		System.out.println("#						#");
		System.out.println("#################################################");
		
		System.out.println("# Seed:					" + seed + "	#");
		System.out.println("# Replications:				" + replications + "	#");
		System.out.println("# Queue:				" + maxQueueSize + "	#");
		System.out.println("# Simulation length:			" + modelTime + "	#");
		
		simulation = new CheapCoffee[replications];
		
		rnd.setSeed(seed);
		
		for (int i = 0; i < replications; i++) {
//			System.out.println("#################################################");
//			System.out.println("#						#");
//			System.out.println("# Replication " + (i + 1) + "					#");
//			System.out.println("#						#");
//			System.out.println("#################################################");
			long rndSeed = rnd.nextLong();
			simulation[i] = new CheapCoffee(rndSeed, modelTime);
			sumAverageQueueTime += simulation[i].getAverageQueueTime();
			sumPercentRejected += simulation[i].getPercentRejected();
			sumTotalCustomers += simulation[i].getTotalCustomers();
		}
		System.out.println("#################################################");
		System.out.println("#						#");
		System.out.println("# Summary					#");
		System.out.println("#						#");
		System.out.println("#################################################");
		System.out.println("# Total average average queue time:	" + String.format("%.5g",(double) sumAverageQueueTime/replications) + "	#");
		System.out.println("# Total average percent rejected:	" +  String.format("%.5g",(double) 100*sumPercentRejected/replications) + "%	#");
		System.out.println("# Total average customers: 		" + (double) sumTotalCustomers/replications + "	#");
		System.out.println("#################################################");
	}
}
