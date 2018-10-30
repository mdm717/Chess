package pieces;

import chess.Board;

/**
 * Pawn.java - This class defines the Pawn piece
 * @author mdm289 && cms631
 *
 */
public class Pawn extends Piece {

	final int WHITE_START = 1;
	final int BLACK_START = 6;
	boolean passPawn = false;
	
	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true-->white, false-->black
	 */
	public Pawn(boolean c) {
		super(c);
		symbol = 'p';
	}
	
	
	/**
	 * This method is called in Chess.move(str,str); however, for pawns, we chose to call this method elsewhere.
	 * 				In order to not call the function where we did not want to, we left this blank and overloaded it
	 * @param b	the 2x2 matrix of pieces
	 */
	@Override
	public void resetPassant(Piece[][] b ) {
	}
	
	/**
	 * overloaded version of the overridden resetPassant(Piece[][])
	 * 				This was done so this method could be called differently from how the rest of the pieces call the method
	 * @param b	the 2x2 matrix of pieces
	 * @param k	this variable is only to change the signature of the method from the overridden version
	 */
	public void resetPassant(Piece[][] b, int k) {

		for(int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				if(b[i][j]!=null) {
					if (b[i][j].getColorBoolean()!=color && b[i][j] instanceof Pawn) {
						((Pawn) b[i][j]).passPawn=false;
					}
				}
			}
		}
	}
	
	/**
	 * Determines whether the selected pawn piece can move
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
		
		if (color && targetRow<startRow) {
			return false;
		} else if (!color && startRow<targetRow) {
			return false;
		}
		
		if (b[targetRow][targetCol] != null && startCol==targetCol) {
			return false;
		}
		
		if (Math.abs(startRow-targetRow)==2 && targetCol==startCol && 		//two-space move from starting
				((color && startRow==WHITE_START) || (!color && startRow==BLACK_START) )) {
			resetPassant(b,0);
			passPawn=true;
			return true;
		}
		if (Math.abs(startRow-targetRow)==1 && targetCol==startCol) {		//one-space move

			resetPassant(b,0);
			passPawn=false;
			return true;
		}
		
		//-------attacking--------//
		if(b[targetRow][targetCol] != null) {
			if (Math.abs(startRow-targetRow)==1 && (targetCol+1==startCol || targetCol-1==startCol) && 
					b[targetRow][targetCol].getColorBoolean()!=color) {		//one-space attack
	
				resetPassant(b,0);
				passPawn=false;
				return true;
			}
		}
		
		//--------en passant------//
		
		if (Math.abs(startRow-targetRow)==1 && (targetCol+1==startCol || targetCol-1==startCol)) {		//one-space attack
				if (b[startRow][targetCol]!=null) {
					if (b[startRow][targetCol].getColorBoolean()!=color && b[startRow][targetCol] instanceof Pawn) {
						if (((Pawn)b[startRow][targetCol]).passPawn) {
							b[startRow][targetCol]=null;
							passPawn=false;
							return true;
						}
					}
				}
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

		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		
		if (b[startRow][startCol].getColorBoolean()) {
			if (b[startRow+1][startCol]==null)  {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startRow+1][startCol+1]!=null) {
				if (b[startRow+1][startCol+1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
			if (b[startRow+1][startCol-1]!=null) {
				if (b[startRow+1][startCol-1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
		} else if (!(b[startRow][startCol].getColorBoolean())) {
			if (b[startRow-1][startCol]==null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startRow-1][startCol+1]!=null) {
				if (b[startRow-1][startCol+1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
			if (b[startRow-1][startCol-1]!=null) {
				if (b[startRow-1][startCol-1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
		}
		
		
		return false;
	}

}
