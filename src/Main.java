import genetic.*;
import particleSwarm.*;
import particleSwarm.Parameters;

public class Main {

	static Swarm swarm = new Swarm();
	static Swarm localBestSwarm = swarm.clone();
	
	static Particle bestOne = swarm.getBestParticle();
	
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
	}

}
