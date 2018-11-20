//***********************************
// Nathan Schnitzer & Ryan Giovanniello
// ******************************

/**
 * 18
 * Creates an instance of a Piece object
 * @author 120nschnitzer
 * @author 120rgiovanniello
 *
 */
public class Piece 
{
	private boolean king;
	private boolean inGame;
	private boolean team1, team2;
	private boolean moveUp; //Team1 moves up to start
	
	/**
	 * 19
	 * Constructs a Piece element and sets instance variables of king, inGame, team1, team2, moveUp and moveDown
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param row
	 * @param col
	 * @param isTeam1
	 */
	public Piece(int row, int col, boolean isTeam1)
	{
		king = false;
		inGame = true;
		team1 = isTeam1;
		team2 = !isTeam1;
		if (isTeam1)
		{
			moveUp = false;
		}
		else
		{
			moveUp = true;
		}
	}
	
	//Returns whether or not the piece is a king
	/**
	 * 20
	 * Returns whether or not the piece is a king
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public boolean kingStatus()
	{
		return king;
	}
	
	//Returns whether the piece is in the game
	/**
	 * 21
	 * Returns whether the piece is in the game
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public boolean isInGame()
	{
		return inGame;
	}
	
	//Returns the team of the player
	/**
	 * 22
	 * Returns whether or not the player is on team1
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public boolean isTeam1()
	{
		return team1;
	}
	
	//Returns string of team
	/**
	 * 23
	 * Returns the string of the team
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public String getTeam()
	{
		if (team1)
		{
			return "T1";
		}
		else
			return "T2";
	}
	
	//Change the status of the king
	//If the piece reached the other side (7 if Player 1 or 0 for Player 2)
	/**
	 * 24
	 * Changes the king status of the piece
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public void changeKingStatus()
	{
		king = !king;
		return;
	}
	
	//Change whether the piece is in the game
	/**
	 * 25
	 * Change whether the piece is in the game
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public void changeInGameStatus()
	{
		inGame = !inGame;
	}
	
	/**
	 * 26
	 * Reutrns whether or not the piece moves in the positive y direction
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public boolean movesUp()
	{
		return moveUp;
	}

}
