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
			//Player Ones turn [State Based]
			//Player Twos turn [State Based]
			
			//Perform players turns
			
			if (playerOne.Health() <= 0 || playerTwo.Health() <= 0)
				break;
		}
		
		DisplayEndGameScreen(playerOne, playerTwo);
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
