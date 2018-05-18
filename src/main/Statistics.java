package main;

import java.util.ArrayList;

public class Statistics {
	private static boolean wantSwarm = false;
	private static ArrayList<ArrayList<Double>> swarmBestFitness = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> geneticBestFitness = new ArrayList<ArrayList<Double>>();
	private static double[] averageBestFitnessSwarm = new double[Parameters.maxNumOfGenerations];
	private static double[] averageBestFitnessGenetic = new double[Parameters.maxNumOfGenerations];
	private static Object[][][] swarmTableData;
	private static Object[][][] geneticTableData;
	
	public static void printABFList()
	{
		double[] averageBestFitness;
		
		if(wantSwarm)
		{
			System.out.println("Swarm Generations -------------");
			averageBestFitness = averageBestFitnessSwarm;
		}
		else
		{
			System.out.println("Genetic Generations -------------");
			averageBestFitness = averageBestFitnessGenetic;
		}
		
		for(int i = 0; i < averageBestFitness.length; i++)
		{
			System.out.println("Gen " + i + ": " + averageBestFitness[i]);
		}
	}
	
	public static void printList()
	{
		ArrayList<ArrayList<Double>> bestFitness;
		
		if(wantSwarm)
		{
			System.out.println("Swarm ------------ ");
			bestFitness = swarmBestFitness;
		}
		else
		{
			System.out.println("Genetic ------------- ");
			bestFitness = geneticBestFitness;
		}
		
		for(int i = 0; i < bestFitness.size(); i++)
		{
			System.out.println("Run: " + i);
			for(int j = 0; j < bestFitness.get(i).size(); j++)
			{
				System.out.print(bestFitness.get(i).get(j) + "\t");
			}
			System.out.println();
		}
	}
	
	public static Object[][][] getTableData()
	{
		if(wantSwarm)
		{
			return swarmTableData;			
		}
		return geneticTableData;
	}
	
	public static double[] getAverageBestFitnessList()
	{
		if(wantSwarm)
		{
			return averageBestFitnessSwarm;
		}
		return averageBestFitnessGenetic;
	}
	
	public static int getListColumnCount()
	{
		if(wantSwarm) {
			return swarmBestFitness.size();
		}
		return geneticBestFitness.size();
	}
	
	public static void setWantSwarm(boolean value)
	{
		wantSwarm = value;
	}
	
	public static void addToList(int runIndex, double value)
	{
		if(wantSwarm)
		{
			swarmBestFitness.get(runIndex).add(value);
		}
		else
		{
			geneticBestFitness.get(runIndex).add(value);
		}
	}
	
	public static void addColumnToList()
	{
		if(wantSwarm)
		{
			swarmBestFitness.add(new ArrayList<Double>());
		}
		else
		{
			geneticBestFitness.add(new ArrayList<Double>());
		}
	}
	
	public static void convertListToTableData()
	{
		Object[][][] tableData;
		ArrayList<ArrayList<Double>> bestFitness;
		double[] averageBestFitness;
		
		if(wantSwarm)
		{
			bestFitness = swarmBestFitness;
			averageBestFitness = averageBestFitnessSwarm;
		}
		else
		{
			bestFitness = geneticBestFitness;
			averageBestFitness = averageBestFitnessGenetic;
		}
		
		tableData = new Object[bestFitness.size()][][];
		for(int run = 0; run < bestFitness.size(); run++)
		{
			tableData[run] = new Object[bestFitness.get(run).size()][];
			for(int gen = 0; gen < bestFitness.get(run).size(); gen++)
			{
//													{ "Generation", "Best Fitness", "Average Best Fitness" };
				tableData[run][gen] = new Object[] { gen, bestFitness.get(run).get(gen), averageBestFitness[gen] };
			}
		}
		
		if(wantSwarm)
		{
			swarmTableData = tableData;
		}
		else
		{
			geneticTableData = tableData;
		}
	}
	
	private static double calcGenerationAverage(int gen) 
	{
		double genAverage = 0;
		ArrayList<ArrayList<Double>> bestFitness;
		
		if(wantSwarm)
		{
			bestFitness = swarmBestFitness;
		}
		else
		{
			bestFitness = geneticBestFitness;
		}
		
		for(int i=0; i < bestFitness.size(); i++)
		{
			genAverage += bestFitness.get(i).get(gen);
		}
		genAverage = genAverage/bestFitness.size();
		
		return genAverage;
	}
	
	public static void calcBestAverageFitness()
	{
		for(int i = 0; i < Parameters.maxNumOfGenerations; i++)
		{
			if(wantSwarm)
			{
				averageBestFitnessSwarm[i] = calcGenerationAverage(i);
			}
			else 
			{
				averageBestFitnessGenetic[i] = calcGenerationAverage(i);
			}
		}
	}
	
	public static double calcVariance()
	{
		double variation = 0;
		double[] averageBestFitness;
		
		if(wantSwarm)
		{
			averageBestFitness = averageBestFitnessSwarm;
		}
		else
		{
			averageBestFitness = averageBestFitnessGenetic;
		}
		
		for(int i = 0; i < averageBestFitness.length; i++)
		{ 
			variation = Math.pow(variation + averageBestFitness[i], 2);
		}
		variation = Math.sqrt(variation/averageBestFitness.length);
		
		return variation;			
	}
}
