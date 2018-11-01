package pieces;

import chess.Board;

/**
 * Bishop.java - This class defines the bishop piece
 * @author Craig Sirota cms631
 * @author Matthew Marrazzo mdm289
 */

public class Bishop extends Piece {


	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true=white, false=black
	 */
	public Bishop(boolean c) {
		super(c);
		symbol = 'B';
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method checks if a player's request for a move is valid.
	 * @param start		the starting spot of the piece requesting to move
	 * @param target	the target spot of the piece requesting to move
	 * @param b			the 2x2 matrix of pieces
	 * @return	true if the piece can be moved to the target, false if it can't
	 */
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
		
		if (Math.abs(startRow-targetRow)==Math.abs(startCol-targetCol)) {

			if (targetRow>startRow && targetCol>startCol) {
				for (int i = startRow+1, j = startCol+1; i<targetRow; i++, j++) {
					if (b[i][j]!=null) {
						return false;
					}
				}
			}
			

			if (targetRow>startRow && targetCol<startCol) {
				for (int i = startRow+1, j = startCol-1; i<targetRow; i++, j--) {
					if (b[i][j]!=null) {
						return false;
					}
				}
			}
			
			if (targetRow<startRow && targetCol<startCol) {
				for (int i = startRow-1, j = startCol-1; i<targetRow; i--, j--) {
					if (b[i][j]!=null) {
						return false;
					}
				}
			}
			

			if (targetRow<startRow && targetCol>startCol) {
				for (int i = startRow-1, j = startCol+1; i<targetRow; i--, j++) {
					if (b[i][j]!=null) {
						return false;
					}
				}
			}
			
			return true;
			
		}
		
		return false;
	}
}