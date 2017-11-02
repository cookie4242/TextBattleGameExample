package Items;
public class HealthPotion 
{
	private int effect = 30;
	private int uses = 1;
	
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