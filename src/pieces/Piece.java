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
	
	public String toString() {
		if (color)
			return "w" + symbol;
		return "b" + symbol;
	}
}
