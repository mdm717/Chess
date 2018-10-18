package pieces;

import chess.Board;

public class Knight extends Piece {

	public Knight(boolean c) {
		super(c);
		symbol = 'N';
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(String start, String target, Piece[][] b) {
		// TODO Auto-generated method stub

		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		

		if (b[targetRow][targetCol] != null) {
			if (b[targetRow][targetCol].getColorBoolean()==color) {
				return false;
			}
		}
		
		if (Math.abs(startRow-targetRow)==2 && Math.abs(startCol-targetCol)==1 ||
				Math.abs(startRow-targetRow)==1 && Math.abs(startCol-targetCol)==2) {
			return true;
		}
		
		return false;
	}

}
