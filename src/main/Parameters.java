package main;


public class Parameters {
	
	//Common
	public static final int MAX = 600;
	public static final int MIN = -600;
	public static int maxNumOfGenerations = 100;
	public static boolean maximisation = false;
	public static int structureLength = 10;
	public static int groupSize = 10;
	
	//Genetic
	public static double mutationRate = 0.1;
	public static int tournamentSize = 5;
	public static double crossoverRate = 0.9;
	
	//Swarm
	public static double localInertia = 0.25;
	public static double globalIntertia = 2;
	public static double innateInertia = 0.125;
}
