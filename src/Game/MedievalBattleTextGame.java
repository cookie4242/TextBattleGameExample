package Game;
import Services.InputService;
import Services.OutputService;
import Services.PlayerStateService;
import Services.PlayerTurnService;
import UserInterface.GameUI;

public class MedievalBattleTextGame 
{
	public static void main(String[] args) 
	{
		InputService input = new InputService();
		OutputService output = new OutputService();
		PlayerStateService stateService = new PlayerStateService();
		GameUI userInterface = new GameUI(input, output);
		PlayerTurnService turnService = new PlayerTurnService(output, userInterface, stateService);
		Game game = new Game(input, output, stateService, turnService, userInterface);
		game.Play();
	}
}