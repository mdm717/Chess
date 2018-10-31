package pieces;

/**
 * Piece.java - This class defines the symbol and color of the all pieces
 * @author Craig Sirota cms631
 * @author Matthew Marrazzo mdm289
 *
 */

public abstract class Piece {
	protected boolean color; //false = black, true = white
	protected char symbol;
	
	/**
	 * This abstract method, when implemented, will check if a player's request for a move is valid.
	 * @param start		the starting spot of the piece requesting to move
	 * @param target	the target spot of the piece requesting to move
	 * @param b			the 2x2 matrix of pieces
	 * @return	true if the piece can be moved to the target, false if it can't
	 */
	public abstract boolean canMove(String start, String target, Piece[][] b);
	
	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true=white, false=black
	 */
	public Piece (boolean c) {
		color = c;
	}
	
	/**
	 * Gets the boolean value of the color
	 * @return boolean corresponding to the color of the piece, true=white, false=black
	 */
	public boolean getColorBoolean() {
		return color;
	}
	
	
	/**
	 * This method loops through the board and resets all passPawn booleans on a pawn from the piece's team after it moves
	 * 				This method was overridden and left blank, and then overloaded in the pawn class because it needs to be used differently by pawns
	 * @param b the 2x2 matrix of pieces
	 */
	public void resetPassant(Piece[][] b ) {
		for(int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				if(b[i][j]!=null)
					if (b[i][j].getColorBoolean()!=color && b[i][j] instanceof Pawn) {
						((Pawn) b[i][j]).passPawn=false;
					}
			}
		}
	}
	
	/**
	 * Converts the color and symbol into a string
	 * @return the piece's color concatenated with the piece's symbol
	 */
	public String toString() {
		if (color)
			return "w" + symbol;
		return "b" + symbol;
	}
}
