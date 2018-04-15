package genetic;
import main.Parameters;

public class Population {
	
	private Citizen[] citizens;
	
	public Citizen getBestCitizen()
	{
		Citizen best = citizens[0].cloneIndividual();
		
		for(int i = 0; i < Parameters.populationSize; i++)
		{
			if(citizens[i].better(best))
			{
				best = citizens[i].cloneIndividual();
			}
		}
		
		return best;
	}
	
	public void setCitizen(int position, Citizen individual)
	{
		citizens[position] = individual;
	}
	
	private Citizen getRandomCitizen()
	{
		return citizens[(int)(Math.random() * Parameters.populationSize)];
	}
	
	public Citizen getCitizen(int i)
	{
		return citizens[i];
	}
	
	private Citizen tournamentSelection()
	{
		Citizen currentIndiv = getRandomCitizen();
		Citizen best = currentIndiv.cloneIndividual();
		
		for(int i = 1; i < Parameters.tournamentSize; i++)
		{
			currentIndiv = getRandomCitizen();
			if(currentIndiv.better(best))
			{
				best = currentIndiv.cloneIndividual();
			}
		}
		return best;
	}
	
	public Citizen select()
	{
		//In theory, you could pass a parameter to this and have it select which method to use. Tournament's probably the best though
		return tournamentSelection();
	}
	
	public Population()
	{
		citizens = new Citizen[Parameters.populationSize];
		for(int i = 0; i < citizens.length; i++)
		{
			citizens[i] = new Citizen();
		}
	}
}
