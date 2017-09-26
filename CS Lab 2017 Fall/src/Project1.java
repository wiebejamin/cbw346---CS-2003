import java.util.Arrays;
import java.util.Scanner;

//Name: Connor Wiebe

public class Project1
{
	char[][] board = new char[8][8];
	boolean isBlack = true;

	public void initialize(){
		//creates Board, letter then number, and prints it once
		for(char[] i: board) Arrays.fill(i, ' ');
		board[3][3] = 'W'; board[4][4] = 'W';
		board[3][4] = 'B'; board[4][3] = 'B';
	}

	public static void main(String[] args)
	{
		Project1 proj = new Project1();

		proj.initialize();


		//isBlack is a boolean that keeps track of whose turn it is, and what color piece is being considered.
		//it starts as true because it is always black's turn first.

		//passedTurn tests if the previous player had to pass their turn
		boolean passedTurn = false;

		//a loop that runs the game
		while(true)
		{

			while(proj.canContinue())
			{
				proj.display();
				if(passedTurn)
					if(proj.isBlack) System.out.println("White could not play, it is Black's turn again.");
					else System.out.println("Black could not play, it is White's turn again.");
				if(proj.canMove(proj.isBlack)) {proj.placePiece(); passedTurn = false;}
				else passedTurn = true;
				proj.isBlack = !proj.isBlack;
			}

			//finishes the game, deciding the winner
			proj.display();
			System.out.println("Game over");
			int wCount = 0, bCount = 0;
			for(char[] i: proj.board) for(char j: i)
			{
				if(j == 'B') bCount++;
				if(j == 'W') wCount++;
			}
			if(bCount > wCount) System.out.println("Black wins!");
			if(wCount > bCount) System.out.println("White wins!");
			if(wCount == bCount) System.out.println("It's a tie, which I didn't know was possible and didn't bother testing.");

			System.out.println("Play Again? (yes/no)");
			Scanner console = new Scanner(System.in);
			String ans = console.next();
			
			if(ans.toUpperCase().equals("YES") || ans.toUpperCase().equals("Y")) continue;
			if(ans.toUpperCase().equals("NO")  || ans.toUpperCase().equals("N")) break;
		}
		System.out.println("Thanks for playing!");
	}

	//asks the user where the piece should be placed, and places it
	public void placePiece()
	{
		Scanner console = new Scanner(System.in);
		while(true)
		{
			if(isBlack) System.out.println("It is black's turn.");
			else System.out.println("It is white's turn.");
			System.out.println("Where will you place your piece?");
			String input = console.next();

			//tests input string length
			if(input.length() == 2) 
			{
				int col; int row;
				//assigns row value
				switch(input.charAt(0))
				{
				case 'A': case 'a': row = 0; break;
				case 'B': case 'b': row = 1; break;
				case 'C': case 'c': row = 2; break;
				case 'D': case 'd': row = 3; break;
				case 'E': case 'e': row = 4; break;
				case 'F': case 'f': row = 5; break;
				case 'G': case 'g': row = 6; break;
				case 'H': case 'h': row = 7; break;
				default: System.out.println("Please place the letter first."); continue;
				}
				//assigns column value
				try{col = Integer.parseInt(input.substring(1)) - 1;					}

				catch(java.lang.NumberFormatException name)
				{System.out.println("Put number value in the second position"); continue;}

				if(col > 7 || col < 0)
				{
					System.out.println("Please don't place the piece off the board, it makes the code cry.");
					continue;
				}

				if(canPlace(row, col))
				{
					if(isBlack) board[row][col] = 'B';
					else board[row][col] = 'W';
					switchPiece(row, col);
					break;
				}
				else {System.out.println("Invalid move."); continue;}					
			}
			else {System.out.println("Please say the letter/number combination only."); continue;}
		}
	}

	//switches all relevant pieces
	public void switchPiece(int row, int col)
	{
		//assigns color as the color for boolean testing
		char color = board[row][col];


		/* tests in all directions for pieces to flip
		 * this repeated system uses the same structure used in canPlace
		 * instead of returning whether the pieces can be flipped, it flips them
		 */

		//up
		if(row>0)
		{
			int rowtmp = row;
			rowtmp--;
			if(board[rowtmp][col] != ' ' && board[rowtmp][col] != color)
				while(rowtmp >= 0 && board[rowtmp][col] != ' ')
				{
					if(board[rowtmp][col] == color)
					{
						for(int i = row; i > rowtmp; i--)
							board[i][col] = color;
						break;
					}
					rowtmp--;
				}
		}

		//down
		if(row<7)
		{
			int rowtmp = row;
			rowtmp++;
			if(board[rowtmp][col] != ' ' && board[rowtmp][col] != color)
				while(rowtmp <= 7 && board[rowtmp][col] != ' ')
				{
					if(board[rowtmp][col] == color)
					{
						for(int i = row; i < rowtmp; i++)
							board[i][col] = color;
						break;
					}
					rowtmp++;
				}
		}

		//left
		if(col>0)
		{
			int rowtmp = row;
			int coltmp = col;
			coltmp--;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp >= 0 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color)
					{
						for(int j = col; j > coltmp; j--)
							board[row][j] = color;
						break;
					}
					coltmp--;
				}
		}

		//right
		if(col<7)
		{
			int rowtmp = row;
			int coltmp = col;
			coltmp++;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp <= 7 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color)
					{
						for(int j = col; j < coltmp; j++)
							board[row][j] = color;
						break;
					}
					coltmp++;
				}
		}

		//up left
		if(row > 0 && col > 0)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp--;
			coltmp--;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp >= 0 && rowtmp >= 0 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color)
					{
						for(int i = row, j = col; i > rowtmp && j > coltmp; i--, j--)
							board[i][j] = color;
						break;
					}
					coltmp--;
					rowtmp--;
				}
		}

		//up right
		if(row > 0 && col < 7)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp--;
			coltmp++;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp <= 7 && rowtmp >= 0 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color)
					{
						for(int i = row, j = col; i > rowtmp && j < coltmp; i--, j++)
							board[i][j] = color;
						break;
					}
					coltmp++;
					rowtmp--;
				}
		}

		//down left
		if(row < 7 && col > 0)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp++;
			coltmp--;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp >= 0 && rowtmp <= 7 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color)
					{
						for(int i = row, j = col; i < rowtmp && j > coltmp; i++, j--)
							board[i][j] = color;
						break;
					}
					coltmp--;
					rowtmp++;
				}
		}

		//down right
		if(row < 7 && col < 7)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp++;
			coltmp++;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp <= 7 && rowtmp <= 7 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color)
					{
						for(int i = row, j = col; i < rowtmp && j < coltmp; i++, j++)
							board[i][j] = color;
						break;
					}
					coltmp++;
					rowtmp++;
				}
		}
	}

	//tests if the piece can be placed in position
	public boolean canPlace(int row, int col)
	{
		if(board[row][col] != ' ') return false;

		//assigns color as the color for boolean testing
		char color;
		if(isBlack) color = 'B'; else color = 'W';

		//tests in all directions for pieces to flip
		//I use tmp values to test in various directions if it follows the rules for piece placement/flipping.
		//up
		if(row>0)
		{
			int rowtmp = row;
			rowtmp--;
			if(board[rowtmp][col] != ' ' && board[rowtmp][col] != color)
				while(rowtmp >= 0 && board[rowtmp][col] != ' ')
				{
					if(board[rowtmp][col] == color) return true;
					rowtmp--;
				}
		}

		//down
		if(row<7)
		{
			int rowtmp = row;
			rowtmp++;
			if(board[rowtmp][col] != ' ' && board[rowtmp][col] != color)
				while(rowtmp <= 7 && board[rowtmp][col] != ' ')
				{
					if(board[rowtmp][col] == color) return true;
					rowtmp++;
				}
		}

		//left
		if(col>0)
		{
			int rowtmp = row;
			int coltmp = col;
			coltmp--;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp >= 0 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color) return true;
					coltmp--;
				}
		}

		//right
		if(col<7)
		{
			int rowtmp = row;
			int coltmp = col;
			coltmp++;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp <= 7 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color) return true;
					coltmp++;
				}
		}

		//up left
		if(row > 0 && col > 0)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp--;
			coltmp--;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp >= 0 && rowtmp >= 0 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color) return true;
					coltmp--;
					rowtmp--;
				}
		}

		//up right
		if(row > 0 && col < 7)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp--;
			coltmp++;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp <= 7 && rowtmp >= 0 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color) return true;
					coltmp++;
					rowtmp--;
				}
		}

		//down left
		if(row < 7 && col > 0)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp++;
			coltmp--;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp >= 0 && rowtmp <= 7 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color) return true;
					coltmp--;
					rowtmp++;
				}
		}

		//down right
		if(row < 7 && col < 7)
		{
			int rowtmp = row;
			int coltmp = col;
			rowtmp++;
			coltmp++;
			if(board[rowtmp][coltmp] != ' ' && board[rowtmp][coltmp] != color)
				while(coltmp <= 7 && rowtmp <= 7 && board[rowtmp][coltmp] != ' ')
				{
					if(board[rowtmp][coltmp] == color) return true;
					coltmp++;
					rowtmp++;
				}
		}

		//default case, if nothing was true
		return false;
	}

	//tests if the player can move
	public boolean canMove(boolean isBlack)
	{
		//goes over all game spaces
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if(canPlace(i, j)) return true;
		return false;
	}

	//tests if the game can continue
	public boolean canContinue()
	{
		if(canMove(true) || canMove(false))
		{
			return true;
		}
		return false;
	}	

	//prints the board as it currently exists
	public void display()
	{
		String headline =  String.format("    1   2   3   4   5   6   7   8  %n");
		String separator = String.format("  ---------------------------------%n");
		String blankline = String.format("  |   |   |   |   |   |   |   |   |%n");
		String lineA =     String.format("A | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[0][0], board[0][1], board[0][2], board[0][3], board[0][4], board[0][5], board[0][6], board[0][7]);
		String lineB =     String.format("B | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[1][0], board[1][1], board[1][2], board[1][3], board[1][4], board[1][5], board[1][6], board[1][7]);
		String lineC =     String.format("C | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[2][0], board[2][1], board[2][2], board[2][3], board[2][4], board[2][5], board[2][6], board[2][7]);
		String lineD =     String.format("D | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[3][0], board[3][1], board[3][2], board[3][3], board[3][4], board[3][5], board[3][6], board[3][7]);
		String lineE =     String.format("E | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[4][0], board[4][1], board[4][2], board[4][3], board[4][4], board[4][5], board[4][6], board[4][7]);
		String lineF =     String.format("F | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[5][0], board[5][1], board[5][2], board[5][3], board[5][4], board[5][5], board[5][6], board[5][7]);
		String lineG =     String.format("G | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[6][0], board[6][1], board[6][2], board[6][3], board[6][4], board[6][5], board[6][6], board[6][7]);
		String lineH =     String.format("H | %c | %c | %c | %c | %c | %c | %c | %c |%n", board[7][0], board[7][1], board[7][2], board[7][3], board[7][4], board[7][5], board[7][6], board[7][7]);

		System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n"
				+ headline 
				+ separator 
				+ blankline
				+ lineA
				+ blankline
				+ separator
				+ blankline
				+ lineB
				+ blankline
				+ separator
				+ blankline
				+ lineC
				+ blankline
				+ separator
				+ blankline
				+ lineD
				+ blankline
				+ separator
				+ blankline
				+ lineE
				+ blankline
				+ separator
				+ blankline
				+ lineF
				+ blankline
				+ separator
				+ blankline
				+ lineG
				+ blankline
				+ separator
				+ blankline
				+ lineH
				+ blankline
				+ separator
				);	
	}

}
