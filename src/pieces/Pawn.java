package pieces;

import chess.Board;

/**
 * Pawn.java - This class defines the Pawn piece
 * @author Craig Sirota cms631
 * @author Matthew Marrazzo mdm289
 *
 */
public class Pawn extends Piece {

	final int WHITE_START = 1;
	final int BLACK_START = 6;
	boolean passPawn = false;
	
	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true=white, false=black
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
}
