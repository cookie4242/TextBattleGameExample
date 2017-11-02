package Game;
import java.util.ArrayList;

import Player.Player;
import Player.PlayerState;
import Services.InputService;
import Services.OutputService;
import Services.PlayerStateService;
import Services.PlayerTurnService;
import UserInterface.GameUI;

public class Game 
{
	private OutputService output;
	private InputService input;
	private PlayerStateService stateService;
	private PlayerTurnService turnService;
	private GameUI userInterface;
	
	public Game(InputService input, OutputService output, PlayerStateService stateService,
			PlayerTurnService turnService, GameUI userInterface) 
	{
		this.input = input;
		this.output = output;
		this.stateService = stateService;
		this.turnService = turnService;
		this.userInterface = userInterface;
	}
	
	public void Play() 
	{
		userInterface.DisplayStartGameScreen();
		
		ArrayList<String> invalidPlayerNames = new ArrayList<String>();
		invalidPlayerNames.add("");
		String playerOneName = GetPlayerName("Enter Player One's Name: ", invalidPlayerNames);
		invalidPlayerNames.add(playerOneName);
		String playerTwoName = GetPlayerName("Enter Player Two's Name: ", invalidPlayerNames);
		output.ClearScreen();
		
		Player playerOne = new Player(playerOneName);
		Player playerTwo = new Player(playerTwoName);
		
		userInterface.DisplayGameRulesScreen(playerOne.Name, playerTwo.Name);
		
		while (true)
		{
			playerOne = turnService.PerformPlayerTurn(playerOne);
			playerTwo = turnService.PerformPlayerTurn(playerTwo);

			playerOne = stateService.ResolvePlayerState(playerOne, playerTwo);
			playerTwo = stateService.ResolvePlayerState(playerTwo, playerOne);
			
			playerOne.ResolveEffects();
			playerTwo.ResolveEffects();
			
			if (playerOne.Health <= 0 || playerTwo.Health <= 0)
				break;
		}
		
		userInterface.DisplayEndGameScreen(playerOne, playerTwo);
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
}
