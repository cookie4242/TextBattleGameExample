public class Player 
{
	public final String Name;
	private int Health;
	
	public Player(String name)
	{
		Name = name;
		Health = 100;
	}
	
	public int Health()
	{
		return Health;
	}
}