package Items;

public class PoisonPotion 
{
	private int effect = 20;
	private int uses = 3;
	
	public int Use()
	{
		if (uses > 0)
		{
			uses--;
			return effect;
		}
		else
		{
			return 0;
		}
	}
	
	public int UsesRemaining()
	{
		return uses;
	}
}
