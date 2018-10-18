package chess;

import pieces.*;
import java.util.Scanner; 

public class chess {
	private static Board b;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		b = new Board();
		while (true) {
			System.out.println(b.toString());
			String input = sc.nextLine();
			if (input.charAt(2)==' ') {
				move(""+input.charAt(0)+input.charAt(1),""+input.charAt(3)+input.charAt(4));
			}
		}
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
