package pieces;

/**
 * Queen.java - This class defines the Queen piece
 * @author Craig Sirota cms631
 * @author Matthew Marrazzo mdm289
 */
public class Queen extends Piece {

	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true=white, false=black
	 */
	public Queen(boolean c) {
		super(c);
		symbol = 'Q';
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * This method checks if a player's request for a move is valid as a composite of the moves of rook and bishop.
	 * @param start		the starting spot of the piece requesting to move
	 * @param target	the target spot of the piece requesting to move
	 * @param b			the 2x2 matrix of pieces
	 * @return	true if the piece can be moved to the target, false if it can't
	 */
	@Override
	public boolean canMove(String start, String target, Piece[][] b) {
		// TODO Auto-generated method stub
		
		return new Rook(color).canMove(start, target, b) || new Bishop(color).canMove(start, target, b);
	}
}
