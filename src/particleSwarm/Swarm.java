package particleSwarm;

public class Swarm implements Cloneable {
	
	public Particle[] particles;
	
	public Swarm clone()
	{  
		Swarm retSwarm = new Swarm();
		for(int i = 0; i < Parameters.swarmSize; i++)
		{
			retSwarm.particles[i] = particles[i].clone();
		}
		
		return retSwarm;
	}  
	
	public Particle getBestParticle()
	{
		Particle best = particles[0];
		
		for(int i = 0; i < Parameters.swarmSize; i++)
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
		particles = new Particle[Parameters.swarmSize];
		for(int i = 0; i < Parameters.swarmSize; i++)
		{
			particles[i] = new Particle();
		}
	}
}
