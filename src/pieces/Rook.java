package pieces;

import chess.Board;

public class Rook extends Piece {
	int moves;
	
	public Rook(boolean c) {
		super(c);
		symbol = 'R';
		moves=0;
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
		
		if (startRow!=targetRow && startCol!=targetCol) {
			return false;
		}
		
		if (targetRow>startRow) {
			for (int i = startRow; i<targetRow; i++) {
				if (b[i][targetCol]!=null) {
					return false;
				}
			}
		}
			
		if (targetRow<startRow) {
			for (int i = startRow; i>targetRow; i--) {
				if (b[i][targetCol]!=null) {
					return false;
				}
			}
		}
		

		if (targetCol>startCol) {
			for (int i = startCol; i<targetCol; i++) {
				if (b[targetRow][i]!=null) {
					return false;
				}
			}
		}
			
		if (targetCol<startCol) {
			for (int i = startCol; i>targetCol; i--) {
				if (b[targetRow][i]!=null) {
					return false;
				}
			}
		}
		
		moves++;
		return true;
	}

}
