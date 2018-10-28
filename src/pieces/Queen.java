package pieces;

import chess.Board;

public class Queen extends Piece {

	public Queen(boolean c) {
		super(c);
		symbol = 'Q';
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(String start, String target, Piece[][] b) {
		// TODO Auto-generated method stub
		
		return new Rook(color).canMove(start, target, b) || new Bishop(color).canMove(start, target, b);
	}

	@Override
	public boolean possibleMove(String start, Piece[][] b, int kRow, int kCol) {
		// TODO Auto-generated method stub
		int startingRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startingCol = Board.columnNum(start.charAt(0));
		
		
		try{
			if (b[startingRow+1][startingCol] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+1][startingCol].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-1][startingCol] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-1][startingCol].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow][startingCol+1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow][startingCol+1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow][startingCol-1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow][startingCol-1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow+1][startingCol+1] == null){
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
			if (b[startingRow+1][startingCol-1] == null){
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+1][startingCol-1].getColorBoolean()!=color){
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		return false;
	}

}
