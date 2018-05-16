package main;

import java.util.ArrayList;

public class Statistics {
	private static boolean wantSwarm = false;
	private static ArrayList<ArrayList<Double>> swarmBestFitness = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> geneticBestFitness = new ArrayList<ArrayList<Double>>();
	private static double[] averageBestFitnessSwarm = new double[Parameters.maxNumOfGenerations];
	private static double[] averageBestFitnessGenetic = new double[Parameters.maxNumOfGenerations];
	
	public static void printABFList()
	{
		if(wantSwarm)
		{
			for(int i = 0; i < averageBestFitnessSwarm.length; i++)
			{
				System.out.println("Swarm Gen " + i + ": " + averageBestFitnessSwarm[i]);
			}
		}
		else
		{
			for(int i = 0; i < averageBestFitnessGenetic.length; i++)
			{
				System.out.println("Genetic Gen " + i + ": " + averageBestFitnessGenetic[i]);
			}
		}
	}
	
	public static Object[][] convertListToTableData()
	{
		Object[][] returnData = new Object[swarmBestFitness.size()][];
		if(wantSwarm)
		{
			for(int i = 0; i < swarmBestFitness.size(); i++)
			{
				//TODO format it in an array of objects formatted like what the table needs 
			}
		}
		
		return returnData;
	}
	
	public static void printList()
	{
		if(wantSwarm)
		{
			for(int i = 0; i < swarmBestFitness.size(); i++)
			{
				System.out.println("Swarm Run: " + i);
				for(int j = 0; j < swarmBestFitness.get(i).size(); j++)
				{
					System.out.print(swarmBestFitness.get(i).get(j) + "\t");
				}
				System.out.println();
			}
		}
		else
		{
			for(int i = 0; i < geneticBestFitness.size(); i++)
			{
				System.out.println("Genetic Run: " + i);
				for(int j = 0; j < geneticBestFitness.get(i).size(); j++)
				{
					System.out.print(geneticBestFitness.get(i).get(j) + "\t");
				}
				System.out.println();
			}
		}
	}
	
	public static double[] getAverageBestFitnessList()
	{
		if(wantSwarm)
		{
			return averageBestFitnessSwarm;
		}
		return averageBestFitnessGenetic;
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
	public static int getListColumnCount()
	{
		if(wantSwarm) {
			return swarmBestFitness.size();
		}
		return geneticBestFitness.size();
	}
	
	private static double calcGenerationAverage(int gen) 
	{
		double genAverage = 0;
		if(wantSwarm) 
		{
			for(int i=0; i < swarmBestFitness.size(); i++)
			{
				genAverage += swarmBestFitness.get(i).get(gen);
			}
			genAverage = genAverage/swarmBestFitness.size();
		}
		else {
			for(int i=0; i < geneticBestFitness.size(); i++)
			{
				genAverage += geneticBestFitness.get(i).get(gen);
			}
			genAverage = genAverage/ geneticBestFitness.size();
		}
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
	public double variance()
	{
		double variation = 0;
		if(wantSwarm)
		{
			for(int i = 0; i < averageBestFitnessSwarm.length; i++)
			{
				variation = Math.pow(variation + averageBestFitnessSwarm[i], 2);
			}
			variation = Math.sqrt(variation/averageBestFitnessSwarm.length);
		}
		else
		{
			for(int i = 0; i < averageBestFitnessGenetic.length; i++)
			{
				variation = Math.pow(variation + averageBestFitnessGenetic[i], 2);
			}
			variation = Math.sqrt(variation/averageBestFitnessGenetic.length);
		}
		return variation;			
	}
}
	
	
	
	
	
	


