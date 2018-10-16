package pieces;

public class Rook extends Piece {
	int moves;
	
	public Rook(boolean c) {
		super(c);
		symbol = 'R';
		moves=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(String start, String target, Piece[][] b) {
		// TODO Auto-generated method stub
		return false;
	}

}
