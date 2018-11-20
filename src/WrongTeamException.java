//*******************************************************
// Nathan Schnitzer & Ryan
// WrongTeamException
// 9/28/18
// Throws if piece of other team is selected
//*******************************************************

/**
 * 27
 * Throws when the player selects a piece of the wrong team
 * @author 120nschnitzer
 * @author 120rgiovanniello
 */
public class WrongTeamException extends Exception
{
	//General Case
	/**
	 * 28
	 * Throws WrongTeamException with no message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public WrongTeamException()
	{
		super();
	}
	
	//Specific Case
	/**
	 * 29
	 * Throws WrongTeamException with a specified error message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param message
	 */
	public WrongTeamException(String message)
	{
		super(message);
	}

}
