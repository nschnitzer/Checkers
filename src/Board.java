
//*************************************************************
// Nathan & Ryan Giovanniello
// Board.java
// 9/24/18
//*************************************************************

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 * 1
 * Creates and manages a board in which a game of checkers between 2 players can play.
 * Player 1 starts and moves down the board
 * If the player can jump pieces they are forced
 * When a player captures all of the oponent's pieces, a winner is declared
 * A draw scenario is also handled
 * 
 * @author 120nschnitzer
 * @author 120rgiovanniello
 */
public class Board
{
	private final int SIZE = 8;
	Piece[][] pieces = new Piece[SIZE][SIZE];
	ArrayList<Piece> p1Eliminated = new ArrayList<Piece>();
	ArrayList<Piece> p2Eliminated = new ArrayList<Piece>();
	private Scanner scan = new Scanner(System.in);
	private StringTokenizer tokenizer;

	/**
	 * 2
	 * Constructs a Board Object
	 * This creates all of the pieces, and puts them into the starting position on the board
	 * 
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	//Constructor
	public Board()
	{
		//Make the pieces for each team
		//Does top and bottom at the same time
		for (int i = 0; i < 5; i++) // Rows
		{
			for (int k = 0; k < SIZE; k++) //Columns
			{
				if (i > 2) //middle empty spaces
				{
					pieces[i][k] = null; //null
					continue;
				}
				if (i % 2 == 0) //Alternates
				{
					if (k % 2 == 0) //Top piece and Bottom null
					{
						pieces[i][k] = new Piece(i, k, true); //Team1
						pieces[SIZE-i-1][k] = null; //null
					}
					else //Top null and Bottom piece
					{
						pieces[SIZE-i-1][k] = new Piece(SIZE-i-1, k, false); //Team2
						pieces[i][k] = null; //null
					}
				}
				else
				{
					if (k % 2 == 0)
					{
						pieces[i][k] = null;
						pieces[SIZE-i-1][k] = new Piece(SIZE-1-i, k, false);
					}
					else
					{
						pieces[i][k] = new Piece(i,k,true);
						pieces[SIZE-i-1][k] = null;
					}
				}
			}
		}

		System.out.println(pieces[5][1].movesUp());

	}

	/**
	 * 3
	 * This method locates and returns the piece object in the matrix
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param r
	 * @param c
	 * @return
	 */
	public Piece findPiece(int r, int c)//finds where the piece can move to
	{
		for (int i=0;i<SIZE;i++)
		{
			for (int j=0;j<SIZE; j++)
			{
				if (pieces[r][c] == pieces[i][j])
					return pieces[i][j];
			}
		}
		return null;
	}


	/**
	 * 4
	 * The method runs multiple exception catches and moves a piece throughout the matrix
	 * Also can eliminate pieces
	 * @author 120rgiovanniello
	 * @author 120nschnitzer
	 * @param startRow
	 * @param startCol
	 * @param endRow
	 * @param endCol
	 * @param isPlayer1
	 * @throws InvalidMoveException if the move is invalid
	 * @throws WrongTeamException if the user chooses the wrong piece
	 * @throws NullPointerException 
	 * @throws CanJumpException if the player can jump again
	 * @throws YouArePatheticException if the player does something stupid
	 */
	public void move(int startRow, int startCol, int endRow, int endCol, boolean isPlayer1) throws InvalidMoveException, WrongTeamException, NullPointerException, CanJumpException, YouArePatheticException
	{
		if (startRow < 0 || startRow >= 8 || endRow < 0 || endRow >= 8 || startCol < 0 || startCol >= 8 || endCol < 0 || endCol >= 8)
		{
			throw new YouArePatheticException();
		}
		Piece user = findPiece(startRow, startCol);
		if (user == null)
		{
			throw new NullPointerException();
		}
		if (user.isTeam1() != isPlayer1)
		{
			throw new InvalidMoveException("T1");
		}
		int checkRow = 0, checkCol = 0;

		if (findPiece(endRow, endCol) != null)
		{
			if (findPiece(endRow, endCol).isTeam1() == isPlayer1)
			{
				throw new InvalidMoveException("T2");
			}
		}
		
		//If the move isnt diagonal
		if ((Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 0) || ((Math.abs(startRow - endRow) == 0) && Math.abs(startCol - endCol) == 1))
		{
			throw new InvalidMoveException("Not Diagonal");
		}

		if (Math.abs(startRow-endRow) > 1 || Math.abs(startCol-endCol) > 1)//if capturing opponent
		{

			if (endCol - startCol > 1) //Move to right
			{
				checkCol = startCol + 1;
			}
			else if (endCol - startCol < -1) //Move to left
			{
				checkCol = startCol - 1;
			}

			if (endRow - startRow > 1) //Move up
			{
				if (user.kingStatus() || user.movesUp() == false) //If king or on team that can move up
				{
					checkRow = startRow + 1;
				}
				else //invalid move exception
				{
					throw new InvalidMoveException("T3");
				}
			}
			else if (endRow - startRow < -1) //Move down
			{
				if (user.kingStatus() || user.movesUp() == true)
				{
					checkRow = startRow -1;
				}
				else //Invalid Move Exception
				{
					throw new InvalidMoveException("T4");
				}
			} //end if

			if (findPiece(checkRow, checkCol).isTeam1() == user.isTeam1()) //if piece in the way is the same team
			{
				throw new InvalidMoveException("T5");
			}
			else // Confirmed that piece in the way is the the opponent
			{
				if (findPiece(checkRow, checkCol).isTeam1())
				{
					p1Eliminated.add(findPiece(checkRow, checkCol));
				}
				else
				{
					p2Eliminated.add(findPiece(checkRow, checkCol));
				}
				pieces[checkRow][checkCol] = null;
				
				//Checks if it becomes a king
				if (user.isTeam1())
				{
					if (endRow == SIZE - 1)
					{
						user.changeKingStatus();
					}
				}
				else
				{
					if (endRow == 0)
					{
						user.changeKingStatus();
					}
				}


				pieces[endRow][endCol] = user;
				pieces[startRow][startCol] = null;
				System.out.println();
				
				
				//Move this to driver and make new exception
				if (checkJump(endRow, endCol))
				{
					throw new CanJumpException();
				}
				System.out.println("Player 1's Eliminated Pieces: " + p1Eliminated.size());
				System.out.println("Player 2's Eliminated Pieces: " + p2Eliminated.size());
				return;
			}
		} //end big if
		
		//Checks if it becomes a king
		if (user.isTeam1())
		{
			if (endRow == SIZE - 1)
			{
				user.changeKingStatus();
			}
		}
		else
		{
			if (endRow == 0)
			{
				user.changeKingStatus();
			}
		}

		pieces[endRow][endCol] = user;
		pieces[startRow][startCol] = null;

		System.out.println("Player 1's Eliminated Pieces: " + p1Eliminated.size());
		System.out.println("Player 2's Eliminated Pieces: " + p2Eliminated.size());
		return;
	}

	//Prints out board
	/**
	 * 5
	 * Prints out the board
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 */
	public void printBoard()
	{
		System.out.print("\t");
		for (int i = 0; i < SIZE; i++) //Top legend
		{
			System.out.print(i + "\t");
		}
		System.out.println();
		
		
		for (int i = 0; i < SIZE; i++) //row
		{
			System.out.print(i + "\t");
			for (int k = 0; k < SIZE; k++) //col
			{
				if (pieces[i][k] == null)
				{
					System.out.print("--\t");
				}
				else if (pieces[i][k].kingStatus() == false)
					System.out.print(pieces[i][k].getTeam() + "\t");
				else
					System.out.print(pieces[i][k].getTeam() + "K\t");
			}
			System.out.println();
		}
	}

	//Checks if a player has won
	/**
	 * 6
	 * Checks if player1 has won
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return 
	 */
	public boolean checkTeam1Winner()
	{
		if (p2Eliminated.size() == 12)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//Checks if plater2 has won
	/**
	 * 7
	 * Checks if player2 has won
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public boolean checkTeam2Winner()
	{
		if (p1Eliminated.size() == 12)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//Checks if it is a stalemate
	/**
	 * 8
	 * Checks if there is a stalemate
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @return
	 */
	public boolean isStalemate()
	{
		if (p1Eliminated.size() == 11 && p2Eliminated.size() == 11)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Checks if there is another jump
	/**
	 * 9
	 * Checks if the player can jump again
	 * @author 120nschnitzer
	 * @author 120rgiovanniello
	 * @param newRow
	 * @param newCol
	 * @return
	 */
	public boolean checkJump(int newRow, int newCol)
	{
		int checkRow = 0, checkCol = 0; //Check if the piece is enemy
		int checkNewRow = 0, checkNewCol = 0; //See if place is empty to jump
		//Check for bottom right
		//Check for out of bounds exceptions
		if (newRow + 1 < 8 && newRow + 2 < 8 && newCol + 2 < 8 && newCol + 1 < 8)
		{
			checkRow = newRow + 1;
			checkNewRow = newRow + 2;
			checkCol = newCol + 1;
			checkNewCol = newCol + 2;

			if (findPiece(newRow, newCol).kingStatus() && findPiece(newRow, newCol).isTeam1()) //If it can move up or is king
			{
				if (findPiece(checkRow, checkCol).isTeam1() && findPiece(checkNewRow, checkNewCol) == null) //If the next piece is the opponent and the piece that you jump to is empty
				{
					System.out.println("Good1");
					return true;
				}
			}
		}
		
		//Check for top right
		//Check for out of bounds issues
		if (newRow - 1 > 0 && newRow - 2 > 0)
		{
			checkRow = newRow - 1;
			checkNewRow = newRow -2;

			if (findPiece(newRow, newCol).kingStatus() && findPiece(newRow, newCol).isTeam1() == false) //If it can move down or is king
			{
				if (findPiece(checkRow, checkCol).isTeam1() && findPiece(checkNewRow, checkNewCol) == null) //If the next piece is the opponent and the piece that you jump to is empty
				{
					System.out.println("Good2");
					return true;
				}
			}
		}
		
		//Check for top left
		//Check for out of bounds
		if (newCol - 1 > 0 && newCol - 2 > 0)
		{
			System.out.println("Close");
			checkCol = newCol - 1;
			checkNewCol = newCol -2;

			if (findPiece(newRow, newCol).kingStatus() || findPiece(newRow, newCol).isTeam1() == false) //If it can move down or is king
			{
				System.out.println("Almost");
				if (findPiece(checkRow, checkCol).isTeam1() != findPiece(newRow, newCol).isTeam1() && findPiece(checkNewRow, checkNewCol) == null) //If the next piece is the opponent and the piece that you jump to is empty
				{
					System.out.println("Good3");
					return true;
				}
			}
		}
		
		//check for bottom left
		//Check for out of bounds exception
		if (newRow+ 1 < 8 && newRow + 2 < 8)
		{
			checkRow = newRow + 1;
			checkNewRow = newRow + 2;

			if (findPiece(newRow, newCol).kingStatus() && findPiece(newRow, newCol).isTeam1()) //If it can move up or is king
			{
				if (findPiece(checkRow, checkCol).isTeam1() && findPiece(checkNewRow, checkNewCol) == null) //If the next piece is the opponent and the piece that you jump to is empty
				{
					System.out.println("Good4");
					return true;
				}
			}
		}
		
		//No more jumps possible
		System.out.println("Bad");
		return false;
	}
}