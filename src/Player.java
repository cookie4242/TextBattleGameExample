import java.util.ArrayList;

public class Player 
{
	public final String Name;
	public int Health;
	public PlayerState State;
	public HealthPotion ActiveHealthPotion;
	public PoisonPotion ActivePoisonPotion;
	public StrengthPotion ActiveStrengthPotion;
	private final ArrayList<HealthPotion> healthPotions;
	private final ArrayList<PoisonPotion> poisonPotions;
	private final ArrayList<StrengthPotion> strengthPotions;
	
	public Player(String name)
	{
		Name = name;
		State = PlayerState.NONE;
		Health = 100;
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
	
	public int HealthPotionCount()
	{
		return healthPotions.size();
	}
	
	public void UseHealthPotion()
	{
		ActiveHealthPotion = healthPotions.remove(0);
	}
	
	public int PoisonPotionCount()
	{
		return poisonPotions.size();
	}
	
	public PoisonPotion GetPoisonPotion()
	{
		return poisonPotions.remove(0);
	}
	
	public int StrengthPotionCount()
	{
		return strengthPotions.size();
	}
	
	public void UseStrengthPotion()
	{
		ActiveStrengthPotion = strengthPotions.remove(0);
	}
	
	public void ResolveEffects()
	{
		if (ActiveHealthPotion != null)
		{
			Health += GameConstants.HealthPotionEffect;
			ActiveHealthPotion = null;
		}
		
		if (ActivePoisonPotion != null)
		{
			Health -= GameConstants.PoisonPotionDamage;
		}
		
		if (ActiveStrengthPotion != null)
		{
			
		}
	}
}