package main;
import genetic.*;
import particleSwarm.*;

public class Main {

	// Swarm
	static Swarm swarm = new Swarm();
	static Swarm localBestSwarm = swarm.clone();
	static Particle bestOne = swarm.getBestParticle();
	
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
		Population pop = new Population();
		System.out.print("0. ");
		pop.getBestCitizen().print();
		
		Population newPop;
		
		for(int generation = 0; generation <= Parameters.maxNumOfGenerations; generation++)
		{
			newPop = new Population();
			for(int i = 0; i < Parameters.populationSize; i++)
			{
				pop.getCitizen(i).calculateFitness();
			}
			
			newPop.setCitizen(0, pop.getBestCitizen());
			
			int newPopIndivsCount = 1;
			
			while (newPopIndivsCount < Parameters.populationSize)
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
				
				if(newPopIndivsCount < Parameters.populationSize)
				{
					newPop.setCitizen(newPopIndivsCount, offspring[0]);
					newPopIndivsCount++;
				}
				
				if (newPopIndivsCount < Parameters.populationSize)
				{
					newPop.setCitizen(newPopIndivsCount,  offspring[1]);
					newPopIndivsCount++;
				}
			}
			
			System.out.print(generation + ". ");
			newPop.getBestCitizen().print();
			
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
		for(int partIndex = 0; partIndex < Parameters.swarmSize; partIndex++)
		{				
			for(int bit = 0; bit < Parameters.structureLength; bit++)
			{
				swarm.particles[partIndex].structure[bit] += swarm.particles[partIndex].velocity[bit];
				
				double differenceFromLocalBest = localBestSwarm.particles[partIndex].structure[bit] - swarm.particles[partIndex].structure[bit];
				double localDifference = Parameters.localMultiplier * Math.random() * differenceFromLocalBest;
				
				double differenceFromBestOne = bestOne.structure[bit] - swarm.particles[partIndex].structure[bit];
				double globalDifference = Parameters.globalMultiplier * Math.random() * differenceFromBestOne;
				
				swarm.particles[partIndex].structure[bit] *= Parameters.inertia + localDifference + globalDifference;
			}
			
			checkForHierarchyChanges(partIndex);
		}
	}
	
	private static void engageTheSwarm()
	{	
		for (int gen = 0; gen < Parameters.maxNumOfGenerations; gen++)
		{
			moveTheSwarm();
			
			System.out.println(gen + ", "); 
			bestOne.print();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		engageTheSwarm();
		//engageTheGenetics();
	}

}
