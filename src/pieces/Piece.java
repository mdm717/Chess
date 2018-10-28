package pieces;

/**
 * Piece.java - This class defines the symbol and color of the all pieces
 * @author mdm289 && 
 *
 */

public abstract class Piece {
	protected boolean color; //false = black, true = white
	protected char symbol;
	
	public abstract boolean canMove(String start, String target, Piece[][] b);
	
	public abstract boolean possibleMove(String start, Piece[][] b, int kRow, int kCol);
	
	
	public Piece (boolean c) {
		color = c;
	}
	
	/**
	 * Sets the color of the piece to be true or false depending on the color
	 * @see Line 10
	 * @parameter str A value of type String
	 */
	public void setColor(String str) {	
		if (str.equalsIgnoreCase("black")) {
			color = false;
		} else if (str.equalsIgnoreCase("white")){
			color = true;
		}
	}
	
	/**
	 * Gets the boolean value of the color
	 * @return A boolean date type
	 */
	public boolean getColorBoolean() {
		return color;
	}
	
	/**
	 * 
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
	 * @return the proper color concatenated with the piece's symbol
	 */
	public String toString() {
		if (color)
			return "w" + symbol;
		return "b" + symbol;
	}
}
