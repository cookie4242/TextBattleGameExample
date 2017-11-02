package Items;

public class StrengthPotion 
{
	public static final int Effect = 10;
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
