//*************************************************************
// Nathan & Ryan Giovanniello
// driver.java
// 9/24/18
//*************************************************************

import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 17
 * Contains a game of checkers and the inputs between 2 players
 * @author 120nschnitzer
 * @author 120rgiovanniello
 */
public class Driver
{

	/**
	 * 13
	 * Instance where the a game of checkers can be played
	 * @param args
	 * @throws IOException if an input or output exception occured
	 */
	public static void main(String[] args) throws IOException
	{
		StringTokenizer tokenizer;
		//Scanner scan = new Scanner(new File("src/input.txt"));
		Scanner scan = new Scanner(System.in);
		Random gen = new Random();
		Board board = new Board();
		board.printBoard();

		int counter = 0;

		while (board.checkTeam2Winner() == false && board.checkTeam1Winner() == false && board.isStalemate() == false)
		{
			int x1 = -1, x2 = -1, y2 = -1, y1 = -1;
			boolean isPlayer1;
			if (counter % 2 == 0)
			{
				System.out.println("Player 1's Turn:");
				System.out.println("Please enter the coordinates of the piece you want to move and the position you want to move it to.");
				isPlayer1 = true;
			}//end if
			else
			{
				System.out.println("Player 2's Turn:");
				System.out.println("Please enter the coordinates of the piece you want to move and the position you want to move it to");
				isPlayer1 = false;
			}//end else
			boolean lcv = true;
			while (lcv)
			{
				tokenizer = new StringTokenizer(scan.nextLine());
				lcv = false;
				try 
				{
					x1 = Integer.parseInt(tokenizer.nextToken());
					y1 = Integer.parseInt(tokenizer.nextToken());
					x2 = Integer.parseInt(tokenizer.nextToken());
					y2 = Integer.parseInt(tokenizer.nextToken());
					System.out.println(x1 + " " + y1 + " " + x2 + " "+ y2);
					board.move(x1, y1, x2, y2, isPlayer1);
				} 
				catch (WrongTeamException e) 
				{	
					System.out.println("Wrong Team: Input a different move...");
					e.printStackTrace();
					continue;
				}
				catch (NullPointerException e) 
				{
					System.out.println("Invalid Move: Input a different move...");
					e.printStackTrace();
					continue;
				}
				catch (InvalidMoveException e)
				{
					System.out.println("Invalid Move: Input a different move...");
					e.printStackTrace();
					continue;
				}
				catch (CanJumpException e)
				{
					System.out.println("You can jump! Please enter coordinates of the next jump or -1 to end turn");
					board.printBoard();
					lcv = true;
				}
				catch (YouArePatheticException e)
				{
					System.out.println("Are you kidding me?!?!?!?\nYou lose your turn for this.");
					break;
				}
			}
			
			counter++;
			board.printBoard();
		}//end while loop
		if (board.checkTeam1Winner() == true)
			System.out.println("Team 1 wins!");
		else if (board.checkTeam2Winner() == true)
			System.out.println("Team 2 wins!");
		else //stalemate
		{
			//Coin flip
			System.out.println("It's a draw! Coin Toss will determine the winner\nFlipping...");
			int x = gen.nextInt(1);
			if (x == 0)
				System.out.println("Team 1 wins!");
			else
				System.out.println("Team 2 wins!");

		}

	}


}


/*
 * true
T1	--	T1	--	T1	--	T1	--	
--	T1	--	T1	--	T1	--	T1	
T1	--	T1	--	T1	--	T1	--	
--	--	--	--	--	--	--	--	
--	--	--	--	--	--	--	--	
--	T2	--	T2	--	T2	--	T2	
T2	--	T2	--	T2	--	T2	--	
--	T2	--	T2	--	T2	--	T2	
Player 1's Turn:
Please enter the coordinates of the piece you want to move and the position you want to move it to.
2 0 3 1
2 0 3 1
0
0
T1	--	T1	--	T1	--	T1	--	
--	T1	--	T1	--	T1	--	T1	
--	--	T1	--	T1	--	T1	--	
--	T1	--	--	--	--	--	--	
--	--	--	--	--	--	--	--	
--	T2	--	T2	--	T2	--	T2	
T2	--	T2	--	T2	--	T2	--	
--	T2	--	T2	--	T2	--	T2	
Player 2's Turn:
Please enter the coordinates of the piece you want to move and the position you want to move it to
5 1 4 2
5 1 4 2
0
0
T1	--	T1	--	T1	--	T1	--	
--	T1	--	T1	--	T1	--	T1	
--	--	T1	--	T1	--	T1	--	
--	T1	--	--	--	--	--	--	
--	--	T2	--	--	--	--	--	
--	--	--	T2	--	T2	--	T2	
T2	--	T2	--	T2	--	T2	--	
--	T2	--	T2	--	T2	--	T2	
Player 1's Turn:
Please enter the coordinates of the piece you want to move and the position you want to move it to.
2 6 3 7
2 6 3 7
0
0
T1	--	T1	--	T1	--	T1	--	
--	T1	--	T1	--	T1	--	T1	
--	--	T1	--	T1	--	--	--	
--	T1	--	--	--	--	--	T1	
--	--	T2	--	--	--	--	--	
--	--	--	T2	--	T2	--	T2	
T2	--	T2	--	T2	--	T2	--	
--	T2	--	T2	--	T2	--	T2	
Player 2's Turn:
Please enter the coordinates of the piece you want to move and the position you want to move it to
4 2 2 0
4 2 2 0
1
0
T1	--	T1	--	T1	--	T1	--	
--	T1	--	T1	--	T1	--	T1	
T2	--	T1	--	T1	--	--	--	
--	--	--	--	--	--	--	T1	
--	--	--	--	--	--	--	--	
--	--	--	T2	--	T2	--	T2	
T2	--	T2	--	T2	--	T2	--	
--	T2	--	T2	--	T2	--	T2	
Player 1's Turn:
Please enter the coordinates of the piece you want to move and the position you want to move it to.
 */