package pieces;

import chess.Board;

public class King extends Piece {
	private int moves;
	
	public King(boolean c) {
		super(c);
		symbol = 'K';
		moves = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(String start, String target, Piece[][] b) {	//needs to add castling
		// TODO Auto-generated method stub
		
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		
		if ((startRow==targetRow || startRow==targetRow+1 || startRow==targetRow-1) &&
				(startCol==targetCol || startCol==targetCol+1 || startCol==targetCol-1)) {
				if (b[targetRow][targetCol]==null) {
					moves++;
					return true;
				} else if (b[targetRow][targetCol].getColorBoolean()!=color) {
					moves++;
					return true;
				} 
		}
		
		//---------add castling logic here---------//
		if (moves==0 && startRow==targetRow) {
			if (startCol-targetCol==2 && b[startRow][1]==null && b[startRow][2]==null && b[startRow][3]==null && ((Rook)b[startRow][0]).moves==0) {			
					b[targetRow][3] = b[targetRow][0];
					((Rook) b[targetRow][3]).moves++;
					b[targetRow][0]=null;
					return true;
			}

			
			
		//	return true;
			else if (startCol-targetCol==-2 && b[startRow][5]==null && b[startRow][6]==null && ((Rook)b[startRow][7]).moves==0) {
				b[targetRow][5] = b[targetRow][7];
				((Rook) b[targetRow][5]).moves++;
				b[targetRow][7]=null;
				return true;
			}
		}
		
		
		
		return false;
	}
}
