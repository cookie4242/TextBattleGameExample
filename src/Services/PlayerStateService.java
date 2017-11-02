package Services;
import Game.Constants.GameConstants;
import Items.PoisonPotion;
import Items.StrengthPotion;
import Player.Player;
import Player.PlayerState;

public class PlayerStateService 
{
	public Player ResolvePlayerState(Player attacker, Player defender)
	{
		int attackerDamageModifier = 0;
		int defenderDamageMitigation = 0;
		
		if (attacker.ActiveStrengthPotion != null)
			attackerDamageModifier += StrengthPotion.Effect;
		
		if (defender.State == PlayerState.BLOCKING)
			defenderDamageMitigation += GameConstants.BlockingEffect;
			
		if (attacker.State == PlayerState.SWORD_ATTACK)
		{
			defender.Health -= (GameConstants.SwordAttackDamage + attackerDamageModifier) - defenderDamageMitigation;
		}
		else if (attacker.State == PlayerState.BOW_ATTACK)
		{
			defender.Health -= (GameConstants.BowAttackDamage + attackerDamageModifier) - defenderDamageMitigation;
		}
		else if (attacker.State == PlayerState.USING_HEALTH_POTION)
		{
			attacker.UseHealthPotion();
		}
		else if (attacker.State == PlayerState.USING_POISON_POTION)
		{
			PoisonPotion potion = attacker.GetPoisonPotion();
			defender.ActivePoisonPotion = potion;
		}
		else if (attacker.State == PlayerState.USING_STRENGTH_POTION)
		{
			attacker.UseStrengthPotion();
		}
		
		return attacker;
	}
	
	public PlayerState GetPlayerState(int playerInput)
	{
		if (playerInput == GameConstants.SwordAttackMove)
			return PlayerState.SWORD_ATTACK;
		else if (playerInput == GameConstants.BowAttackMove)
			return PlayerState.BOW_ATTACK;
		else if (playerInput == GameConstants.BlockMove)
			return PlayerState.BLOCKING;
		else if (playerInput == GameConstants.HealthPotionMove)
			return PlayerState.USING_HEALTH_POTION;
		else if (playerInput == GameConstants.PoisonPotionMove)
			return PlayerState.USING_POISON_POTION;
		else if (playerInput == GameConstants.StrengthPotionMove)
			return PlayerState.USING_STRENGTH_POTION;
		
		return PlayerState.NONE;
	}
}
