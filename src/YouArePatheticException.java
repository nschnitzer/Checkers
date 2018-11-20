//************************************************************
// Nathan and Ryan
// 10/9/18
// YouArePatheticException
// Gets thrown only for the 'special' cases
//************************************************************

/**
 * 30
 * Gets thrown whenever the user does something really stupid (Assume the user is an idiot_
 * @author 120nschnitzer
 * @author 120rgiovanniello
 */
public class YouArePatheticException extends Exception
{
	/**
	 * 31
	 * Throws YouArePatheticException with no message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public YouArePatheticException()
	{
		super();
	}
	
	/**
	 * 32
	 * 
	 * Throws YouArePatheticException with a specified message
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param s
	 */
	public YouArePatheticException(String s)
	{
		super(s);
	}

}
