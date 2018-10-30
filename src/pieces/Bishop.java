package pieces;

import chess.Board;

/**
 * Bishop.java - This class defines the bishop piece
 * @author mdm289 && cms631
 */

public class Bishop extends Piece {


	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true-->white, false-->black
	 */
	public Bishop(boolean c) {
		super(c);
		symbol = 'B';
		// TODO Auto-generated constructor stub
	}

	/**
	 * Determines whether the selected bishop piece can move
	 * @parameter start A value of type String, target A value of type String, b A member of a 2D array
	 * @return boolean value true if piece can move, boolean value false if piece cannot move
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

	
	/**
	 * Determines if the requested move from the player is legal
	 * @parameter start A value of type string, b A member of a 2D array, kRow A value of type int, kCol A value of type int
	 * @return boolean value true if it is a possible move, boolean value false if it is not
	 */
	
	@Override
	public boolean possibleMove(String start, Piece[][] b, int kRow, int kCol) {
		// TODO Auto-generated method stub
		
		int startingRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startingCol = Board.columnNum(start.charAt(0));
		
		
		try{
			if (b[startingRow+1][startingCol+1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+1][startingCol+1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-1][startingCol+1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-1][startingCol+1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-1][startingCol-1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-1][startingCol-1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow+1][startingCol-1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+1][startingCol-1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		
		return false;
	}

}
