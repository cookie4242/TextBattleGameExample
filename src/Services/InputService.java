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
		String text = "";
		
		while (tryForInput)
		{
			try
			{
				text = input.nextLine();
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
