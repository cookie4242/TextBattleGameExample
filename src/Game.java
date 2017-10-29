import java.io.IOException;
import java.util.ArrayList;

public class Game 
{
	private OutputService output;
	private InputService input;
	
	public Game(OutputService output, InputService input) 
	{
		this.output = output;
		this.input = input;
	}
	
	public void Play() 
	{
		DisplayStartGameScreen();
		
		ArrayList<String> invalidPlayerNames = new ArrayList<String>();
		invalidPlayerNames.add("");
		String playerOneName = GetPlayerName("Enter Player One's Name: ", invalidPlayerNames);
		invalidPlayerNames.add(playerOneName);
		String playerTwoName = GetPlayerName("Enter Player Two's Name: ", invalidPlayerNames);
		output.ClearScreen();
		
		Player playerOne = new Player(playerOneName);
		Player playerTwo = new Player(playerTwoName);
		
		DisplayGameRulesScreen(playerOne.Name, playerTwo.Name);
		
		while (true)
		{
			PerformPlayerTurn(playerOne);
			PerformPlayerTurn(playerTwo);

			ResolvePlayerState(playerOne, playerTwo);
			
			if (playerOne.Health() <= 0 || playerTwo.Health() <= 0)
				break;
		}
		
		DisplayEndGameScreen(playerOne, playerTwo);
	}
	
	public void ResolvePlayerState(Player attacker, Player defender)
	{
		if (attacker.State() == PlayerState.SWORD_ATTACK)
		{
			
		}
		else if (attacker.State() == PlayerState.BOW_ATTACK)
		{
			
		}
		else if (attacker.State() == PlayerState.USING_HEALTH_POTION)
		{
			attacker.health += GameConstants.HealthPotionEffect;
		}
		else if (attacker.State() == PlayerState.USING_POISON_POTION)
		{
			
		}
		else if (attacker.State() == PlayerState.USING_STRENGTH_POTION)
		{
			
		}
	}
	
	public void DisplayStartGameScreen() 
	{
		output.ClearScreen();
		output.Print(",.-~*´¨¯¨`*·~-.¸Welcome To,.-~*´¨¯¨`*·~-.¸", true);
		output.Print("     The Battle for Montgomery County", true);
		output.Print(AsciiArt.Sword, true);
	}
	
	public String GetPlayerName(String preText, ArrayList<String> invalidNames)
	{
		while (true)
		{
			output.Print(preText, false);
			String playerName = input.GetInput();
			
			if (playerName.length() == 0 || invalidNames.contains(playerName))
			{
				output.PrintErrorLn("Invalid Player Name");
				continue;
			}
			
			return playerName;
		}
	}
	
	public void DisplayGameRulesScreen(String playerOneName, String playerTwoName)
	{
		output.Print(AsciiArt.Sword, true);
		output.Print("Prepare for battle Player One (" + playerOneName + ") and Player Two (" + playerTwoName + ")", true);
		output.Print("The rules are simple. Defeat your opponent in battle using your Sword, Bow and Potions!", true);
		output.Print("- First person to zero health loses", true);
		output.Print("- If Both players reach zero health its a tie", true);
		output.Print("- Both player moves happen at the same time each turn", true);
		output.Print("Best of luck and PRESS ANY KEY TO BEGIN!", true);
		output.PressAnyKeyToContinue();
		output.ClearScreen();
	}
	
	public void PerformPlayerTurn(Player player)
	{
		output.Print(player.Name + ", it is your turn!", true);
		output.Print("Press any key to begin", true);
		output.PressAnyKeyToContinue();
		
		boolean playerInputInvalid = true;
		while (playerInputInvalid)
		{
			output.Print("Current Health: " + player.Health() + "/100", true);
			PrintPlayerMenu(player);
			int playerInput = GetPlayerMoveInput(player);
			
			if (playerInput >= 0)
			{
				PlayerState state = GetPlayerState(playerInput);
				
				if (state == PlayerState.NONE)
					continue;
				
				player.SetState(state);
				playerInputInvalid = false;
			}
		}
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
	
	public void PrintPlayerMenu(Player player)
	{
		output.Print("[0]: Sword Attack", true);
		output.Print("[1]: Bow Attack", true);
		output.Print("[2]: Block", true);
		
		if (player.HealthPotionCount() > 0)
			output.Print("[3]: Use Health Potion (" + player.HealthPotionCount() + " remaining)", true);
		else
			output.Print("[x]: 0 Health Potions Remaining", true);
		
		if (player.PoisonPotionCount() > 0)
			output.Print("[4]: Use Poison Potion (" + player.PoisonPotionCount() + " remaining)", true);
		else
			output.Print("[x]: 0 Poison Potions Remaining", true);
		
		if (player.PoisonPotionCount() > 0)
			output.Print("[5]: Use Strength Potion (" + player.StrengthPotionCount() + " remaining)", true);
		else
			output.Print("[x]: 0 Strength Potions Remaining", true);
	}
	
	public int GetPlayerMoveInput(Player player)
	{
		output.Print("Enter Move: ", false);
		int playerMove = input.GetIntInput();
		
		if (playerMove < GameConstants.MinPlayerMove || playerMove > GameConstants.MaxPlayerMove)
			return -1;
		else if (playerMove == GameConstants.HealthPotionMove && player.HealthPotionCount() <= 0)
			return -1;
		else if (playerMove == GameConstants.PoisonPotionMove && player.PoisonPotionCount() <= 0)
			return -1;
		else if (playerMove == GameConstants.StrengthPotionMove && player.StrengthPotionCount() <= 0)
			return -1;
		
		return playerMove;
	}
	
	public void DisplayEndGameScreen(Player playerOne, Player playerTwo) 
	{
		boolean playerOneDead = playerOne.Health() <= 0;
		boolean playerTwoDead = playerTwo.Health() <= 0;
		
		if (playerOneDead && playerTwoDead)
		{
			output.Print("The Game is a Tie!!!!", true);
		}
		else if (playerOneDead)
		{
			output.Print("Player Two Won the Game!!!!", true);
		}
		else if (playerTwoDead)
		{
			output.Print("Player One Won the Game!!!!", true);
		}
	}
}
