package Items;

public class PoisonPotion 
{
	public static final int Effect = 20;
	private int uses = 3;
	
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
