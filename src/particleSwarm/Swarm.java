package particleSwarm;

import main.Parameters;

public class Swarm implements Cloneable {
	
	public Particle[] particles;
	
	public Swarm clone()
	{  
		Swarm retSwarm = new Swarm();
		for(int i = 0; i < Parameters.groupSize; i++)
		{
			retSwarm.particles[i] = particles[i].clone();
		}
		
		return retSwarm;
	}  
	
	public Particle getBestParticle()
	{
		Particle best = particles[0];
		
		for(int i = 0; i < Parameters.groupSize; i++)
		{
			if(particles[i].better(best))
			{
				best = particles[i];
			}
		}
		
		return best;
	}
		
	public Swarm()
	{
		particles = new Particle[Parameters.groupSize];
		for(int i = 0; i < Parameters.groupSize; i++)
		{
			particles[i] = new Particle();
		}
	}
}
