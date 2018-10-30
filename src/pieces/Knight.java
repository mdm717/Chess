package pieces;

import chess.Board;

/**
 * Knight.java - This class defines the Knight piece
 * @author mdm289 && cms631
 */

public class Knight extends Piece {


	/**
	 * Class constructor, sets piece color and symbol
	 * @param c	used to set the color of the piece, true-->white, false-->black
	 */
	public Knight(boolean c) {
		super(c);
		symbol = 'N';
		// TODO Auto-generated constructor stub
	}

	/**
	 * Determines whether the selected knight piece can move
	 * @parameter start A value of type String, target A value of type String, b A member of a 2D array
	 * @return boolean value true if piece can move, boolean value false if piece cannot move
	 */
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
		
		if (Math.abs(startRow-targetRow)==2 && Math.abs(startCol-targetCol)==1 ||
				Math.abs(startRow-targetRow)==1 && Math.abs(startCol-targetCol)==2) {
			return true;
		}
		
		return false;
	}
	

	/**
	 * Checks whether a spot is in a line of attack from the opposing team
	 * @param row	the row to check the safety of
	 * @param col	the column to check the safety of
	 * @param b		the 2x2 matrix of pieces
	 * @return		0 if safe
	 * 					1 - in attack by a rook or queen vertically/horizontally
	 * 					2 - in attack by bishop or queen diagonally
	 * 					3 - in attack by knight
	 * 					4 - in attack by pawn of king diagonally
	 * 					5 - in attack by king
	 */	
	public int safe(int row, int col, Piece[][] b) {
		
		/*
		 *  RETURNS
		 *  0 - safe
		 *  1 - rook, queen (vertical or horizontal)
		 *  2 - bishop, queen (diagonal)
		 *  3 - knight
		 *  4 - pawn, king (diagonal)
		 *  5 - king (vertical or horizontal)
		 */

//checking rook and queen
		for( int i = row+1; i<8;i++) {
			if (b[i][col] != null) {
				if ((b[i][col] instanceof Rook || b[i][col] instanceof Queen) && (b[i][col].color != color)) {
					return 1;
				} else {
					break;
				}
			}
		}

		for( int i = row-1; i>=0;i--) {
			if (b[i][col] != null) {
				if ((b[i][col] instanceof Rook || b[i][col] instanceof Queen) && (b[i][col].color != color)) {
					return 1;
				} else {
					break;
				}
			}
		}
		
		for( int i = col+1; i<8;i++) {
			if (b[row][i] != null) {
				if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color != color)) {
					return 1;
				} else {
					break;
				}
			}
		}

		for( int i = col-1; i>=0;i--) {
			if (b[row][i] != null) {
				if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color != color)) {
					return 1;
				} else {
					break;
				}
			}
		}
		
//checking queen and bishop

		for( int i = row+1, j = col +1; i<8 && j<8;i++, j++) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 2;
				} else {
					break;
				}
			}
		}


		for( int i = row+1, j = col-1; i<8 && j>=0;i++, j--) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 2;
				} else {
					break;
				}
			}
		}
		

		for( int i = row-1, j = col +1; i>=0 && j<8;i--, j++) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 2;
				} else {
					break;
				}
			}
		}

		for( int i = row-1, j = col-1; i>=0 && j>=0;i--, j--) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 2;
				} else {
					break;
				}
			}
		}

//knight		
		
		try {
			if (b[row+2][col+1]!= null) {
				if (b[row+2][col+1].getColorBoolean()!=color && b[row+2][col+1] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row+2][col-1]!= null) {
				if (b[row+2][col-1].getColorBoolean()!=color && b[row+2][col-1] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-2][col+1]!= null) {
				if (b[row-2][col+1].getColorBoolean()!=color && b[row-2][col+1] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-2][col-1]!= null) {
				if (b[row-2][col-1].getColorBoolean()!=color && b[row-2][col-1] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		
		

		try {
			if (b[row+1][col+2]!= null) {
				if (b[row+1][col+2].getColorBoolean()!=color && b[row+1][col+2] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row+1][col-2]!= null) {
				if (b[row+1][col-2].getColorBoolean()!=color && b[row+1][col-2] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col+2]!= null) {
				if (b[row-1][col+2].getColorBoolean()!=color && b[row-1][col+2] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col-2]!= null) {
				if (b[row-1][col-2].getColorBoolean()!=color && b[row-1][col-2] instanceof Knight) {
					return 3;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

//Pawn and King
		try {
			if (b[row+1][col+1]!=null) {
				if (((b[row+1][col+1] instanceof Pawn && color)|| b[row+1][col+1] instanceof King) 
						&& b[row+1][col+1].getColorBoolean()!=color) {
					return 4;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row+1][col-1]!=null) {
				if (((b[row+1][col-1] instanceof Pawn && color) || b[row+1][col-1] instanceof King) 
						&& b[row+1][col-1].getColorBoolean()!=color) {
					return 4;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		

		try {
			if (b[row-1][col+1]!=null) {
				if (((b[row-1][col+1] instanceof Pawn && !color) || b[row-1][col+1] instanceof King) 
						&& b[row-1][col+1].getColorBoolean()!=color) {
					return 4;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col-1]!=null) {
				if (((b[row-1][col-1] instanceof Pawn && !color) || b[row-1][col-1] instanceof King) 
						&& b[row-1][col-1].getColorBoolean()!=color) {
					return 4;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		
	
		
//King
		

		try {
			if (b[row+1][col]!=null) {
				if (b[row+1][col] instanceof King 
						&& b[row+1][col].getColorBoolean()!=color) {
					return 5;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col]!=null) {
				if (b[row-1][col] instanceof King 
						&& b[row-1][col].getColorBoolean()!=color) {
					return 5;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		

		try {
			if (b[row][col+1]!=null) {
				if (b[row][col+1] instanceof King 
						&& b[row][col+1].getColorBoolean()!=color) {
					return 5;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row][col-1]!=null) {
				if (b[row][col-1] instanceof King 
						&& b[row][col-1].getColorBoolean()!=color) {
					return 5;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		
		return 0;
	}

	/**
	 * Determines if the requested move from the player is legal
	 * @parameter start A value of type string, b A member of a 2D array, kRow A value of type int, kCol A value of type int
	 * @return boolean value true if it is a possible move, boolean value false if it is not
	 */
	@Override
	public boolean possibleMove(String start, Piece[][] b, int kRow, int kCol) {
		// TODO Auto-generated method stub

		int startingRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startingCol = Board.columnNum(start.charAt(0));
		
		try{
			if (b[startingRow+2][startingCol+1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+2][startingCol+1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow+2][startingCol-1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+2][startingCol-1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-2][startingCol+1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-2][startingCol+1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-2][startingCol-1] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-2][startingCol-1].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow+1][startingCol+2] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+1][startingCol+2].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow+1][startingCol-2] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow+1][startingCol-2].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-1][startingCol+2] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-1][startingCol+2].getColorBoolean()!=color) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if (b[startingRow-1][startingCol-2] == null) {
				if (((King)b[kRow][kCol]).safe(kRow, kCol, b)==0)
					return true;
			}
			if (b[startingRow-1][startingCol-2].getColorBoolean()!=color) {
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
