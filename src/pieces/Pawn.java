package pieces;

import chess.Board;

public class Pawn extends Piece {

	final int WHITE_START = 1;
	final int BLACK_START = 6;
	
	public Pawn(boolean c) {
		super(c);
		symbol = 'p';
	}
	
	@Override
	public boolean canMove(String start, String target, Piece[][] b) {
		// TODO Auto-generated method stub
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		
		if (color) {										//white pawns
			if (startCol==targetCol) { //move straight
				if (((targetRow-startRow==2 && startRow==WHITE_START) || targetRow-startRow==1)
						&& b[targetCol][targetRow]==null)
					return true;
			}

			if (startCol+1==targetCol || startCol-1==targetCol) { //attack
				if (targetRow-startRow==1 && b[targetCol][targetRow-1]!=null) {
					if (b[targetCol][targetRow-1].getColorBoolean()!=color) {
						return true;
					}
				}
				//----------en passant---------//
				if (targetRow-startRow==2 && startRow==WHITE_START 
						&& b[targetCol][targetRow-1]!=null && b[targetCol][targetRow]==null) {
					if (b[targetCol][targetRow-1].getColorBoolean()!=color) {
						return true;
					}
				}
			}
			
			
		}else {												//black pawns
			if (startCol==targetCol) { //move straight
				if (((targetRow-startRow==-2 && startRow==BLACK_START) || targetRow-startRow==-1)
						&& b[targetCol][targetRow]==null)
					return true;
			}

			if (startCol+1==targetCol || startCol-1==targetCol) { //attack
				if (targetRow-startRow==-1 && b[targetCol][targetRow+1]!=null) {
					if (b[targetCol][targetRow+1].getColorBoolean()!=color) {
						return true;
					}
				}
				//----------en passant---------//
				if (targetRow-startRow==-2 && startRow==BLACK_START
						&& b[targetCol][targetRow+1]!=null&& b[targetCol][targetRow]==null) {
					if (b[targetCol][targetRow+1].getColorBoolean()!=color) {
						return true;
					}
				}
			}
			
			
		}
		
		return false;
	}

}
