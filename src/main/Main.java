package main;
import javax.swing.JFrame;

import genetic.*;
import particleSwarm.*;

public class Main {

	static UserInterface userInterface;
	static JFrame frame;
	
	// Swarm
	static Swarm swarm;
	static Swarm localBestSwarm;
	static Particle bestOne;
	
	//Genetic
	public static Citizen[] crossover(Citizen father, Citizen mother)
	{
		Citizen[] offspring = new Citizen[2];
		
		int crossoverPoint = (int)(Math.random() * (Parameters.structureLength - 1));
		
		offspring[0] = new Citizen();
		offspring[1] = new Citizen();
		
		for(int i = 0; i < Parameters.structureLength; i++)
		{
			if(i <= crossoverPoint)
			{
				offspring[0].setGene(i, father.getGene(i));
				offspring[1].setGene(i, mother.getGene(i));
			}
			else
			{
				offspring[0].setGene(i, mother.getGene(i));
				offspring[1].setGene(i, father.getGene(i));
			}
		}
		
		return offspring;
	}
	
	public static void engageTheGenetics()
	{
		Statistics.setWantSwarm(false);
		Statistics.addColumnToList();
		Population pop = new Population();
		
		Population newPop;
		
		for(int gen = 0; gen < Parameters.maxNumOfGenerations; gen++)
		{
			newPop = new Population();
			for(int i = 0; i < Parameters.groupSize; i++)
			{
				pop.getCitizen(i).calculateFitness();
			}
			
			newPop.setCitizen(0, pop.getBestCitizen());
			
			int newPopIndivsCount = 1;
			
			while (newPopIndivsCount < Parameters.groupSize)
			{
				Citizen father = pop.select();
				Citizen mother = pop.select();
				
				Citizen[] offspring = new Citizen[2];
				
				if(Math.random() < Parameters.crossoverRate)
				{
					offspring = crossover(father, mother);
				}
				else
				{
					offspring[0] = father;
					offspring[1] = mother;
				}
				
				offspring[0].mutate();
				offspring[1].mutate();
				
				if(newPopIndivsCount < Parameters.groupSize)
				{
					newPop.setCitizen(newPopIndivsCount, offspring[0]);
					newPopIndivsCount++;
				}
				
				if (newPopIndivsCount < Parameters.groupSize)
				{
					newPop.setCitizen(newPopIndivsCount,  offspring[1]);
					newPopIndivsCount++;
				}
			}
			
			Statistics.addToList(Statistics.getListColumnCount() - 1, newPop.getBestCitizen().calculateFitness());
			
			pop = newPop;
		}
	}
	
	private static void checkForHierarchyChanges(int partIndex)
	{
		if (swarm.particles[partIndex].better(localBestSwarm.particles[partIndex]))
		{
			localBestSwarm.particles[partIndex] = swarm.particles[partIndex].clone();
		}
		
		if (swarm.particles[partIndex].better(bestOne))
		{
			bestOne = swarm.particles[partIndex].clone();
		}
	}
	
	private static void moveTheSwarm()
	{
		for(int partIndex = 0; partIndex < Parameters.groupSize; partIndex++)
		{				
			for(int bit = 0; bit < Parameters.structureLength; bit++)
			{
				double distanceFromLocalBest = localBestSwarm.particles[partIndex].structure[bit] - swarm.particles[partIndex].structure[bit];
				double localDifference = Parameters.localInertia * Math.random() * distanceFromLocalBest;
				
				double distanceFromBestOne = bestOne.structure[bit] - swarm.particles[partIndex].structure[bit];
				double globalDifference = Parameters.globalIntertia * Math.random() * distanceFromBestOne;
				
				swarm.particles[partIndex].velocity[bit] = swarm.particles[partIndex].velocity[bit] * Parameters.innateInertia + localDifference + globalDifference;
				
				swarm.particles[partIndex].structure[bit] += swarm.particles[partIndex].velocity[bit];
			}
			
			checkForHierarchyChanges(partIndex);
		}
	}
	
	private static void initialiseTheSwarm()
	{
		swarm = new Swarm();
		localBestSwarm = swarm.clone();
		bestOne = swarm.getBestParticle();
	}
	
	private static void engageTheSwarm()
	{	
		Statistics.setWantSwarm(true);
		Statistics.addColumnToList();
		initialiseTheSwarm();
		for (int gen = 0; gen < Parameters.maxNumOfGenerations; gen++)
		{
			moveTheSwarm();
			
			Statistics.addToList(Statistics.getListColumnCount() - 1, bestOne.calculateFitness());
		}
	}
	
	public static void engageTheAlgorithms()
	{
		swarm = new Swarm();
		localBestSwarm = swarm.clone();
		bestOne = swarm.getBestParticle();
		
		for(int i = 0; i < Parameters.numberOfRuns; i++)
		{
			System.out.println("Run: " + i);
			engageTheSwarm();
			engageTheGenetics();
		}	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		userInterface = new UserInterface();
	}

}
