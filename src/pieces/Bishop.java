package pieces;

import chess.Board;

public class Bishop extends Piece {

	public Bishop(boolean c) {
		super(c);
		symbol = 'B';
		// TODO Auto-generated constructor stub
	}

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
