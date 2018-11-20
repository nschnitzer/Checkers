//***************************************
// Nathan & Ryan
// InvalidMoveException.java
// 9/26/18
//**************************************

/**
 * 16
 * Throws an exception for an invalid move
 * @author 120nschnitzer
 * @author 120rgiovanniello
 * @version
 */
public class InvalidMoveException extends Exception
{
	
	//Constructor
	/**
	 * 14
	 * Throws an InvalidMoveException with no message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public InvalidMoveException()
	{
		super();
	}
	
	/**
	 * 15
	 * Throws an InvalidMoveException with a message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param message
	 */
	public InvalidMoveException(String message)
	{
		super(message);
	}

}
