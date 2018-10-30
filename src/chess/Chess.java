package chess;

import java.util.Scanner;

import pieces.*; 

/**
 * Chess.java - This is the main class that runs the chess game
 * @author mdm289 && cms631
 */

public class Chess {
	private static Board b;
	private static Scanner sc;
	
	
	/**
	 * This method hold the loop which continues the game until it ends
	 * @param	args	arguments for the program to run
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		boolean turn = true;
		boolean drawAsked = false;
		
		b = new Board();

		System.out.println(b.toString());
		
		while (true) {
			
			if (turn) {
				System.out.print("\nWhite's move: ");
			} else {	
				System.out.print("\nBlack's move: ");
			}
			
			String input = sc.nextLine();
			
			System.out.println();
//draw	
			if (input.equals("draw") && drawAsked) {
				return;
			}
			if (drawAsked && !input.equals("draw")) {
				drawAsked = false;
			}
			
//regular move
			if (input.charAt(2)==' ' && input.length()==5) {
				if (isTurn(""+input.charAt(0)+input.charAt(1), turn)) {
					if (move(""+input.charAt(0)+input.charAt(1),"" + input.charAt(3)+input.charAt(4))) {

						int targetRow = Integer.parseInt(input.charAt(4)+"") - 1;
						int targetCol = Board.columnNum(input.charAt(3));
						
						if (b.board[targetRow][targetCol] instanceof Pawn && (targetRow == 7 || targetRow == 0)) {
							boolean color = b.board[targetRow][targetCol].getColorBoolean();
							b.board[targetRow][targetCol] = new Queen(color);
						}
							
						turn=!turn;
						System.out.println(b.toString());
					} 
					//if it is a legal move, and the requested position on the board is an instance of a King
					//Checkmate, and game ends
/*					if(b.board[targetRow][targetCol] instanceof King)
					{
						System.out.println("Checkmate.");
						return 0;
					}
*/					//
					//
					else {
						System.out.println("Illegal move, try again");
					}
				} else {
					System.out.println("Illegal move, try again");
				}
			}

//promotion move
			if (input.length()==7) {
					if (input.charAt(2)==' ' && input.charAt(5)==' ') {
						if (isTurn(""+input.charAt(0)+input.charAt(1), turn)) {
							if (move(""+input.charAt(0)+input.charAt(1),"" + input.charAt(3)+input.charAt(4))) {

								int targetRow = Integer.parseInt(input.charAt(4)+"") - 1;
								int targetCol = Board.columnNum(input.charAt(3));
								
								if (((targetRow== 7 && (b.board[targetRow][targetCol].getColorBoolean()) && b.board[targetRow][targetCol]  instanceof Pawn))
										||(targetRow== 0 && !(b.board[targetRow][targetCol].getColorBoolean()) && b.board[targetRow][targetCol]  instanceof Pawn)) {
									promote("" + input.charAt(3)+input.charAt(4), input.charAt(6));
									turn=!turn;
									System.out.println(b.toString());
								}
								
							} else {
								System.out.println("Illegal move, try again");
							}
						} else {
							System.out.println("Illegal move, try again");
						}
					}
			}
//draw
			if (input.charAt(2)==' ' && input.length()==11 && input.substring(6).equals("draw?")) {
				if (isTurn(""+input.charAt(0)+input.charAt(1), turn)) {
					if (move(""+input.charAt(0)+input.charAt(1),"" + input.charAt(3)+input.charAt(4))) {
						turn=!turn;
						drawAsked= true;
						System.out.println(b.toString());
					} else {
						System.out.println("Illegal move, try again");
					}
				} else {
					System.out.println("Illegal move, try again");
				}
			}
			
//resign
			if (input.equals("resign")) {
				if (turn) {
					System.out.println("Black wins");
				} else {
					System.out.println("White wins");
				
				}
				return;
			}

			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j< 8; j++) {
					try {
						if (b.board[i][j] instanceof King) {
							((King)b.board[i][j]).setCheck(0!=((King)b.board[i][j]).safe(i, j, b.board));
							if (((King)b.board[i][j]).inCheck()) {
								System.out.print("\nCheck");
								if(((King)b.board[i][j]).checkMate(j, i, b.board)) {

									System.out.println("mate");
									
									if (turn) {
										System.out.println("Black wins");
									} else {
										System.out.println("White wins");
									}
									System.exit(0);
								}else {
									System.out.println("");
								}
							}
						}
					} catch (NullPointerException e) {}
				}
			}

/*			if (stalemate(b.board, turn)) {
				System.out.println("Stalemate");
			}
*/
		}
		
	}
	
	/**
	 * This method determines if it is a given piece's turn to move
	 * @param start	position of a piece on the board
	 * @param color	boolean representing the color of the current turn, true-->white, false-->black
	 * @return	boolean value of whether or not it is the piece at position "start"'s turn 
	 */
	public static boolean isTurn(String start, boolean color) {
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		
		if (b.board[startRow][startCol]!=null) {
			if (b.board[startRow][startCol].getColorBoolean()==color)
				return true;
		}
		return false;
	}
	
	/**
	 * changes a pawn to either a queen, rook, knight, or bishop
	 * @param target	the position of the pawn to be promoted
	 * @param c			a character representing the type of piece the pawn is to be promoted to
	 * @return	true if the pawn was promoted, false if c is passed an incorrect character
	 */
	public static boolean promote(String target, char c) {

		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		boolean color = b.board[targetRow][targetCol].getColorBoolean();
		
		switch (c) {
		case 'R':
			b.board[targetRow][targetCol] = new Rook(color);
			return true;
		case 'N':
			b.board[targetRow][targetCol] = new Knight(color);
			return true;
		case 'B':
			b.board[targetRow][targetCol] = new Bishop(color);
			return true;
		case 'Q':
			b.board[targetRow][targetCol] = new Queen(color);
			return true;
		}
		
		
		return false;
	}
	
	/**
	 * moves a piece on the board as long as it does not put its own king in check
	 * @param start		the position of the piece that is attempting to move
	 * @param target	the position of the target spot
	 * @return	true if the piece is able to properly move, false if the move fails
	 */
	public static boolean move(String start, String target) {
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		
		boolean move=false;
		if (b.board[startRow][startCol]!=null)
		move = b.board[startRow][startCol].canMove(start,target, b.board);
		
		if (move) {
			int kingRow=-1;
			int kingCol=-1;
			
//			b.board[startRow][startCol].resetPassant(b.board);
			Piece temp = b.board[targetRow][targetCol];
			b.board[targetRow][targetCol] = b.board[startRow][startCol];
			b.board[startRow][startCol]=null;
			
			for(int i = 0; i < 8; i++) {
				boolean breakIt=false;
				for (int j = 0; j <8; j++) {
					if (b.board[i][j]!=null) {
						if (b.board[i][j] instanceof King && b.board[i][j].getColorBoolean()==b.board[targetRow][targetCol].getColorBoolean()) {
							kingRow=i;
							kingCol=j;
							breakIt = true;
							break;
						}
					}
				}
				if (breakIt) {
					break;
				}
			}
			
			if (0!=((King)b.board[kingRow][kingCol]).safe(kingRow, kingCol, b.board)) {
				
				b.board[startRow][startCol]=b.board[targetRow][targetCol];
				b.board[targetRow][targetCol] = temp;
				move = false;
			} else {
				b.board[targetRow][targetCol].resetPassant(b.board);
				
			}
			
		}
		
		return move;
	}
	
	/**
	 * determines whether or not a player is in stalemate
	 * @param b		a reference to the 2x2 matrix containing the pieces
	 * @param color	a boolean corresponding to the color of the player being tested
	 * @return true if the player, designated by color has no valid moves, else false
	 */
	public static boolean stalemate(Piece[][] b, boolean color) {
		int kRow=0;
		int kCol=0;
		int f = 0;
		int[] rows = new int[16];
		int[] cols = new int[16];
		for (int i = 0; i < 16; i++) {
			rows[i] = cols[i] = -1;
		}
		
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				try {
					if (b[i][j].getColorBoolean()==color) {
						rows[f] = i;
						cols[f] = j;
						f++;
						if (b[i][j] instanceof King) {
							kRow=i;
							kCol=j;
						}
					}
				} catch (NullPointerException e) {}
			}
		
		}
		if(((King)b[kRow][kCol]).inCheck())
		{
			return false;
		}
		
		for (f=0; f < 16 && rows[f] != -1; f++) {
			Piece p = b[rows[f]][cols[f]];

			System.out.println(rows[f]+":"+cols[f]);
			if (p.possibleMove(spotString(rows[f], cols[f]), b, kRow, kCol)) {
				System.out.println(rows[f]+":"+cols[f] + " can move");
				return false;
			}
			
		}
		
		System.out.println("Stalemate");
		System.exit(0);
		return true;
	}
	
	public static String spotString(int i, int j) {
		i++;
		if (j==0) return "a"+i;
		if (j==1) return "b"+i;
		if (j==2) return "c"+i;
		if (j==3) return "d"+i;
		if (j==4) return "e"+i;
		if (j==5) return "f"+i;
		if (j==6) return "g"+i;
		if (j==7) return "h"+i;
		return "";
	}
	
}
