package chess;

import pieces.*;
import java.util.Scanner; 

public class chess {
	private static Board b;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		boolean turn = true;
		boolean drawAsked = false;
		
		b = new Board();
		while (true) {
			System.out.println(b.toString());
			String input = sc.nextLine();
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
						turn=!turn;
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
					} else {
						System.out.println("Illegal move, try again");
					}
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
		}
	}
	
	public static boolean isTurn(String start, boolean color) {
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		
		if (b.board[startRow][startCol].getColorBoolean()==color)
			return true;
		
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
		
		
		System.out.println(move);
		if (move) {
			b.board[startRow][startCol].resetPassant(b.board);
			b.board[targetRow][targetCol] = b.board[startRow][startCol];
			b.board[startRow][startCol]=null;
		}
		
		return move;
	}

}
