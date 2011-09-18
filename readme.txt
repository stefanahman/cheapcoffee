To compile our java code type the following command in the terminal when standing in the folder with the source code:

"javac -classpath . Main.java"


To run our simulation from a terminal type the following line when standing in the folder with the compiled code: 

“java Main seed replications queue (replication time)”

Required
seed: long to use for random number generation.
replications: integer for the number of replications.
queue: integer for places in the queue.  -1 for infinite places.
Optional:
replication time: integer for the duration of each replication in minutes. If this is not used the time will be set to 720 minutes.

Examples: "java Main 1 1000 -1",  "java Main 1 1000 -1 10000"