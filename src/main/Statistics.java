package main;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
	private static boolean wantSwarm = false;
	private static ArrayList<ArrayList<Double>> swarmBestFitness = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> geneticBestFitness = new ArrayList<ArrayList<Double>>();
	private static double[] averageBestFitnessSwarm;
	private static double[] averageBestFitnessGenetic;
	
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
	public double variance(){
		double variancia=0;
		if(wantSwarm) {
		  double sum=0;
			for(int i=0;i<averageBestFitnessSwarm.length;i++) {
			sum+=averageBestFitnessSwarm[i];
			}
			double mean=sum/averageBestFitnessSwarm.length;
			double sum1=0;
			for(int i=0;i<averageBestFitnessSwarm.length;i++) {
				double difference= (averageBestFitnessSwarm[i] - mean);
				sum1+=Math.pow(difference,2);
			}
			double v= sum1/averageBestFitnessSwarm.length;
	       variancia= Math.sqrt(v);
	      }
		else
		{
			 double sum=0;
				for(int i=0;i<averageBestFitnessGenetic.length;i++) {
				sum+=averageBestFitnessGenetic[i];
				}
				double mean=sum/averageBestFitnessGenetic.length;
				double sum1=0;
				for(int i=0;i<averageBestFitnessSwarm.length;i++) {
					double difference= (averageBestFitnessGenetic[i] - mean);
					sum1+=Math.pow(difference,2);
				}
				double v= sum1/averageBestFitnessGenetic.length;
		        variancia=Math.sqrt(v);
		}
			
			return variancia;
			
		}
	}
	
	
	
	
	
	


