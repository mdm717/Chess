package pieces;

public class Queen extends Piece {

	public Queen(boolean c) {
		super(c);
		symbol = 'Q';
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(String start, String target, Piece[][] b) {
		// TODO Auto-generated method stub
		  new Bishop(color).canMove(start, target, b);
		
		return new Rook(color).canMove(start, target, b) || 
				  new Bishop(color).canMove(start, target, b);
	}

}
