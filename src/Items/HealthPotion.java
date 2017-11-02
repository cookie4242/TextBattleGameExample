package Items;
public class HealthPotion 
{
	public static final int Effect = 30;
	private int uses = 1;
	
	public void Use()
	{
		if (uses > 0)
			uses--;
	}
	
	public int UsesRemaining()
	{
		return uses;
	}
}