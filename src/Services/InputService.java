package Services;
import java.util.Scanner;

public class InputService 
{
	private Scanner input;
	
	public InputService()
	{
		input = new Scanner(System.in);
	}
	
	public String GetInput()
	{
		return input.nextLine();
	}
	
	public int GetIntInput()
	{
		boolean tryForInput = true;
		String text = input.nextLine();
		
		while (tryForInput)
		{
			try
			{
				return Integer.parseInt(text);
			}
			catch (Exception e)
			{
				System.out.println("Invalid number '" + text + "' entered. Try again.");
			}
		}
		
		return 0;
	}
}
