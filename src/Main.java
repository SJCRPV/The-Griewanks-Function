import genetic.*;
import particleSwarm.*;
import particleSwarm.Parameters;

public class Main {

	
	private void runTheSwarm()
	{
		Swarm swarm = new Swarm();
		try 
		{
			Swarm localBestSwarm = (Swarm)swarm.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Swarm localBestSwarm = new Swarm();
			for (int i = 0; i < Parameters.swarmSize; i++)
			{
				for(int j = 0; j < Parameters.structureLength; j++)
				{
					localBestSwarm.particles[i].structure[j] = swarm.particles[i].structure[j];
				}
			}
		}
		
		Particle bestOne = swarm.getBestParticle();
		
		for (int gen = 0; gen < Parameters.maxNumOfGenerations; gen++)
		{
			for(int partIndex = 0; partIndex < Parameters.swarmSize; partIndex++)
			{				
				for(int bit = 0; bit < Parameters.structureLength; bit++)
				{
					swarm.particles[partIndex].structure[bit] += swarm.particles[partIndex].velocity[bit];
					
					double differenceFromLocalBest = localBestSwarm.particles[partIndex].structure[bit] - swarm.particles[partIndex].structure[bit];
					double cognition = Parameters.cognitive * Math.random() * differenceFromLocalBest;
					double differenceFromBestOne = bestOne.structure[bit] - swarm.particles[partIndex].structure[bit];
					double sociability = Parameters.social * Math.random() * differenceFromBestOne;
					
					swarm.particles[partIndex].structure[bit] *= Parameters.inertia + cognition + sociability;
				}
				
				if (swarm.particles[partIndex].better(localBestSwarm.particles[partIndex]))
				{
					localBestSwarm.particles[partIndex] = swarm.particles[partIndex].clone();
				}
				
				if (swarm.particles[partIndex].better(bestOne))
				{
					bestOne = swarm.particles[partIndex].clone();
				}
			}
			
			System.out.println(gen + ", "); 
			bestOne.print();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
