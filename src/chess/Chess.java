package chess;

import pieces.*;
import java.util.Scanner; 

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
					if(b.board[targetRow][targetCol] instanceof King)
					{
						System.out.println("Checkmate.");
						return 0;
					}
					//
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
							((King)b.board[i][j]).setCheck(!((King)b.board[i][j]).safe(i, j, b.board));
							if (((King)b.board[i][j]).inCheck()) {
								System.out.println("Check");
							}
						}
					} catch (NullPointerException e) {}
				}
			}
			
		}
	}
	
	public static boolean isTurn(String start, boolean color) {
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		
		if (b.board[startRow][startCol]!=null) {
			if (b.board[startRow][startCol].getColorBoolean()==color)
				return true;
		}
		return false;
	}
	
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
	
	public static boolean move(String start, String target) {
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		
		boolean move=false;
		if (b.board[startRow][startCol]!=null)
		move = b.board[startRow][startCol].canMove(start,target, b.board);
		
		if (move) {
			b.board[startRow][startCol].resetPassant(b.board);
			b.board[targetRow][targetCol] = b.board[startRow][startCol];
			b.board[startRow][startCol]=null;
		}
		
		return move;
	}

	public static void print() {
		System.out.println(b.toString());
	}
	
}
