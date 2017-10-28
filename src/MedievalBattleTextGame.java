
public class MedievalBattleTextGame 
{
	private static Game game;
	
	public static void main(String[] args) 
	{
		OutputService output = new OutputService();
		InputService input = new InputService();
		game = new Game(output, input);
		game.Play();
	}
}