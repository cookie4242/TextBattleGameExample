package Services;

import Player.Player;
import Player.PlayerState;
import UserInterface.GameUI;

public class PlayerTurnService 
{
	OutputService output;
	GameUI userInterface;
	PlayerStateService stateService;
	
	public PlayerTurnService(OutputService output, GameUI userInterface, PlayerStateService stateService)
	{
		this.output = output;
		this.userInterface = userInterface;
		this.stateService = stateService;
	}
	
	public Player PerformPlayerTurn(Player player)
	{
		output.Print(player.Name + ", it is your turn!", true);
		output.Print("Press any key to begin", true);
		output.PressAnyKeyToContinue();
		
		boolean playerInputInvalid = true;
		while (playerInputInvalid)
		{
			output.Print("Current Health: " + player.Health + "/100", true);
			userInterface.PrintPlayerMenu(player);
			int playerInput = userInterface.GetPlayerMoveInput(player);
			
			if (playerInput >= 0)
			{
				PlayerState state = stateService.GetPlayerState(playerInput);
				
				if (state == PlayerState.NONE)
					continue;
				
				player.State = state;
				playerInputInvalid = false;
			}
		}
		
		return player;
	}
}
