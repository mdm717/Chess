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
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		boolean turn = true;
		boolean drawAsked = false;
		
		b = new Board();

		System.out.println(b.toString());
		
		while (true) {
System.out.println(stalemate(b.board, turn));
			
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
						print();
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
									print();
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
						print();
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
									System.exit(0);
								}else {
									System.out.println("");
								}
							}
						}
					} catch (NullPointerException e) {}
				}
			}

			if (stalemate(b.board, turn)) {
				System.out.println("Stalemate");
			}
		}
		
	}
	
	/**
	 * 
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
	 * Promotes a pawn that reaches the opposite side of the board
	 * @return true if correct input, else false
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
	 * Moves a piece on the board
	 * @parameter start A variable of type String, target A variable of type String
	 * @return boolean data type
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
	 * Prints 
	 */
	public static void print() {
		System.out.println(b.toString());
	}
	
	/**
	 * Determines whether the game is at a stalemate or not
	 * @parameter
	 * @return boolean value true if stalemate, boolean value false if not stalemate
	 */
	
	public static boolean stalemate(Piece[][] b, boolean color) {
		int kRow=0;
		int kCol=0;
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				try {
					if (b[i][j] instanceof King && b[i][j].getColorBoolean()==color) {
						kRow=i;
						kCol=j;
					}
				} catch (NullPointerException e) {}
			}
		
		}
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				Piece p = b[i][j];
				String start = spotString(i,j);
				if (p!=null) {
					if (p.getColorBoolean()==color) {
						if (p.possibleMove(start, b, kRow, kCol)) {

							System.out.println("p" + p.possibleMove(start, b, kRow, kCol));
							return false;
						}else {
							System.out.println("p"+p.possibleMove(start, b, kRow, kCol));
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public static String spotString(int i, int j) {
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
