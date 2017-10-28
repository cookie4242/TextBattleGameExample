import java.util.ArrayList;

public class Player 
{
	public final String Name;
	private final ArrayList<HealthPotion> healthPotions;
	private final ArrayList<PoisonPotion> poisonPotions;
	private final ArrayList<StrengthPotion> strengthPotions;
	private int health;
	private PlayerState state;
	
	public Player(String name)
	{
		Name = name;
		state = PlayerState.NONE;
		health = 100;
		healthPotions = new ArrayList<HealthPotion>();
		healthPotions.add(new HealthPotion());
		healthPotions.add(new HealthPotion());
		healthPotions.add(new HealthPotion());
		
		poisonPotions = new ArrayList<PoisonPotion>();
		poisonPotions.add(new PoisonPotion());
		poisonPotions.add(new PoisonPotion());
		poisonPotions.add(new PoisonPotion());
		
		strengthPotions = new ArrayList<StrengthPotion>();
		strengthPotions.add(new StrengthPotion());
		strengthPotions.add(new StrengthPotion());
		strengthPotions.add(new StrengthPotion());
	}
	
	public int Health()
	{
		return health;
	}
	
	public PlayerState State()
	{
		return state;
	}
	
	public boolean HasHealthPotions()
	{
		return healthPotions.isEmpty() ? false : true;
	}
	
	public HealthPotion GetHealthPotion()
	{
		return healthPotions.remove(0);
	}
	
	public boolean HasPoisonPotions()
	{
		return poisonPotions.isEmpty() ? false : true;
	}
	
	public PoisonPotion GetPoisonPotion()
	{
		return poisonPotions.remove(0);
	}
	
	public boolean HasStrengthPotions()
	{
		return strengthPotions.isEmpty() ? false : true;
	}
	
	public StrengthPotion GetStrengthPotion()
	{
		return strengthPotions.remove(0);
	}
}