package genetic;

import main.Parameters;

public class Citizen {
	private double[] structure;
	private double fitness;
	private boolean fitnessAlreadyCalculated;
	
	public void print()
	{
		for(int i = 0; i < structure.length; i++)
		{
			System.out.print(structure[i]);
		}
		System.out.println("\t" + calculateFitness());
	}
	
	public void setGene(int index, double value)
	{
		structure[index] = value;
	}
	
	public double getGene(int index)
	{
		return structure[index];
	}
	
	private double generateRandomNumber()
	{
		return Math.random() * (Math.abs(Parameters.MAX) + Math.abs(Parameters.MIN)) - Math.abs(Parameters.MIN);
	}
	
	public void mutate()
	{
		for(int i = 0; i < Parameters.structureLength; i++)
		{
			if(Math.random() < Parameters.mutationRate)
			{
				structure[i] = generateRandomNumber();
			}
		}
		
		fitnessAlreadyCalculated = false;
	}
	
	public boolean better(Citizen other)
	{
		if (Parameters.maximisation)
		{
			return this.calculateFitness() > other.calculateFitness();
		}
		else
		{
			return this.calculateFitness() < other.calculateFitness();
		}
	}
	
	public Citizen cloneIndividual()
	{
		Citizen returnIndiv = new Citizen();
		for(int i = 0; i < Parameters.structureLength; i++)
		{
			returnIndiv.structure[i] = this.structure[i];
		}
		returnIndiv.fitness = this.fitness;
		returnIndiv.fitnessAlreadyCalculated = this.fitnessAlreadyCalculated;
		
		return returnIndiv;
	}
	
	private double product()
	{
		double prod = structure[0];
		
		for(int i = 1; i < structure.length; i++)
		{
			prod *= Math.cos(structure[i]/Math.sqrt(structure[i])) + 1;
		}
		return prod;
	}
	
	private double summation()
	{
		double sum = structure[0];
		for(int i = 1; i < structure.length; i++)
		{
			sum += Math.pow(structure[i], 2);
		}
		return sum;
	}
	
	public double calculateFitness()
	{
		if(!fitnessAlreadyCalculated)
		{
			fitness = 1/4000 * summation() - product();
			fitnessAlreadyCalculated = true;
		}
		return fitness;
	}
	
	public Citizen()
	{
		fitnessAlreadyCalculated = false;
		fitness = -1;
		structure = new double[Parameters.structureLength];
		for(int i = 0; i < structure.length; i++)
		{
			structure[i] = generateRandomNumber();
			System.out.println(structure[i]);
		}
	}
}
