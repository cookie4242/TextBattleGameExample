
public class OutputService 
{
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    
	public void ClearScreen() 
	{
		//System.out.print("\033[H\033[2J");  
	    //System.out.flush();
	}
	
	public void Print(String text, Boolean newline) 
	{
		if (newline)
			System.out.println(text);
		else
			System.out.print(text);
	}
	
	public void PrintErrorLn(String text)
	{
		System.out.println(text);
		//System.out.println(RED + text + RESET);
	}
	
	public void PressAnyKeyToContinue()
	{
		try {
	        System.in.read();
		}
		catch (Exception e) { }
	}
}