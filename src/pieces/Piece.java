package pieces;

public abstract class Piece {
	protected boolean color; //false = black, true = white
	protected char symbol;
	
	public abstract boolean canMove(String start, String target, Piece[][] b);
	
	public Piece (boolean c) {
		color = c;
	}
	
	public void setColor(String str) {	
		if (str.equalsIgnoreCase("black")) {
			color = false;
		} else if (str.equalsIgnoreCase("white")){
			color = true;
		}
	}
	
	public boolean getColorBoolean() {
		return color;
	}
	
	public String toString() {
		if (color)
			return "w" + symbol;
		return "b" + symbol;
	}
}
