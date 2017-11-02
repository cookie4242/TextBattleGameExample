package UserInterface;
import Game.Constants.GameConstants;
import Player.Player;
import Services.InputService;
import Services.OutputService;

public class GameUI 
{
	private InputService input;
	private OutputService output;
	
	public GameUI(InputService input, OutputService output)
	{
		this.input = input;
		this.output = output;
	}
	
	public void DisplayStartGameScreen() 
	{
		output.ClearScreen();
		output.Print(",.-~*´¨¯¨`*·~-.¸Welcome To,.-~*´¨¯¨`*·~-.¸", true);
		output.Print("     The Battle for Montgomery County", true);
		output.Print(AsciiArt.Sword, true);
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
		boolean playerOneDead = playerOne.Health <= 0;
		boolean playerTwoDead = playerTwo.Health <= 0;
		
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
