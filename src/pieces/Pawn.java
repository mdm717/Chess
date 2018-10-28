package pieces;

import chess.Board;

public class Pawn extends Piece {

	final int WHITE_START = 1;
	final int BLACK_START = 6;
	boolean passPawn = false;
	
	public Pawn(boolean c) {
		super(c);
		symbol = 'p';
	}
	
	
	
	@Override
	public void resetPassant(Piece[][] b ) {
	}
	
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



	@Override
	public boolean possibleMove(String start, Piece[][] b, int kRow, int kCol) {
		// TODO Auto-generated method stub

		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		
		if (b[startRow][startCol].getColorBoolean()) {
			if (b[startRow+1][startCol]==null)  {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startRow+1][startCol+1]!=null) {
				if (b[startRow+1][startCol+1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
			if (b[startRow+1][startCol-1]!=null) {
				if (b[startRow+1][startCol-1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
		} else if (!(b[startRow][startCol].getColorBoolean())) {
			if (b[startRow-1][startCol]==null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startRow-1][startCol+1]!=null) {
				if (b[startRow-1][startCol+1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
			if (b[startRow-1][startCol-1]!=null) {
				if (b[startRow-1][startCol-1].getColorBoolean()!=b[startRow][startCol].getColorBoolean())  {
					if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
						return true;
				}
			}
		}
		
		
		return false;
	}

}
