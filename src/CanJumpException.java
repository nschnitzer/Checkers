//*********************************************************
// Nathan & Ryan
// CanJumpException.java
// 10/9/18
// Singals that you can jump
//*********************************************************

/**
 * 10
 * Gets thrown when you can jump places after a jump
 * @author 120nschnitzer
 * @author 120rgiovanniello
 */
public class CanJumpException extends Exception 
{
	/**
	 * 11
	 * Throws CanJumpException with no message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public CanJumpException()
	{
		super();
	}
	
	/**
	 * 12
	 * Throws CanJumpException with a specified message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param m
	 */
	public CanJumpException(String m)
	{
		super(m);
	}

}
