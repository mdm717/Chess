package pieces;

import chess.Board;

/**
 * King.java - This class defines the King piece
 * @author mdm289 && cms631
 */

public class King extends Piece {
	private int moves;
	private boolean check;
	
	/**
	 * Class constructor, sets piece color and symbol, as well as sets other private variables
	 * @param c	used to set the color of the piece, true-->white, false-->black
	 */
	public King(boolean c) {
		super(c);
		symbol = 'K';
		moves = 0;
		check = false;
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 */
	@Override
	public boolean canMove(String start, String target, Piece[][] b) {	//needs to add castling
		// TODO Auto-generated method stub
		
		int startRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startCol = Board.columnNum(start.charAt(0));
		int targetRow = Integer.parseInt(target.charAt(1)+"") - 1;
		int targetCol = Board.columnNum(target.charAt(0));
		
		if ((startRow==targetRow || startRow==targetRow+1 || startRow==targetRow-1) &&
				(startCol==targetCol || startCol==targetCol+1 || startCol==targetCol-1) && 
				!(startRow==targetRow && startCol==targetCol)) {
			if (b[targetRow][targetCol]==null) {
				if (safe(targetRow,targetCol, b)==0) {
					moves++;
					return true;
				}
			} else if (b[targetRow][targetCol].getColorBoolean()!=color) {
				if (safe(targetRow,targetCol, b)==0) {
					moves++;
					return true;
				}
			} 
		}
		
		//---------add castling logic here---------//
		if (moves==0 && startRow==targetRow) {
			if (startCol-targetCol==2 && b[startRow][1]==null && b[startRow][2]==null && b[startRow][3]==null && ((Rook)b[startRow][0]).moves==0) {			
					b[targetRow][3] = b[targetRow][0];
					((Rook) b[targetRow][3]).moves++;
					b[targetRow][0]=null;
					return true;
			}

			
			
		//	return true;
			else if (startCol-targetCol==-2 && b[startRow][5]==null && b[startRow][6]==null && ((Rook)b[startRow][7]).moves==0) {
				b[targetRow][5] = b[targetRow][7];
				((Rook) b[targetRow][5]).moves++;
				b[targetRow][7]=null;
				return true;
			}
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
				if (((b[row+1][col+1] instanceof Pawn && !color)|| b[row+1][col+1] instanceof King) 
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
	 * Allows other for other classes to know if a King object is in check
	 * @return	true if in check, false if not
	 */
	public boolean inCheck() {
		return check;
	}

	/**
	 * Sets the value
	 * @param check	boolean used to set a king object's check boolean
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * This method checks if the king is in checkmate
	 * @param startingCol	the column of the king
	 * @param startingRow	the row of the king
	 * @param b				the 2x2 matrix of pieces
	 * @return	true if the king is in checkmate
	 */
	public boolean checkMate(int startingCol, int startingRow, Piece[][] b){

		
		int row = startingRow;
		int col = startingCol;
		
		
		if (safe(startingRow,startingCol, b)==0) {
			try{
				if(this.safe(startingCol, startingRow+1, b)==0){
					if (b[startingRow+1][startingCol] == null) {
						return false;
					}
					if (b[startingRow+1][startingCol].getColorBoolean()!=color)
						return false;
				}
			}catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol+1, startingRow+1,b)==0){
					if (b[startingRow+1][startingCol+1] == null)
						return false;
					if (b[startingRow+1][startingCol+1].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol+1, startingRow,b)==0){
					if (b[startingRow][startingCol+1] == null)
						return false;
					if (b[startingRow][startingCol+1].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol+1, startingRow-1,b)==0){
					if (b[startingRow-1][startingCol+1] == null)
						return false;
					if (b[startingRow-1][startingCol+1].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol, startingRow-1,b)==0){
					if (b[startingRow-1][startingCol] == null)
						return false;
					if (b[startingRow-1][startingCol].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol-1, startingRow-1,b)==0){
					if (b[startingRow-1][startingCol-1] == null)
						return false;
					if (b[startingRow-1][startingCol-1].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol-1, startingRow,b)==0){
					if (b[startingRow][startingCol-1] == null)
						return false;
					if (b[startingRow][startingCol-1].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}
			
			try{
				if(this.safe(startingCol-1, startingRow+1,b)==0){
					if (b[startingRow+1][startingCol-1] == null)
						return false;
					if (b[startingRow+1][startingCol-1].getColorBoolean()!=color)
						return false;
				}
			} catch(ArrayIndexOutOfBoundsException e) {}

			
		} else if (safe(startingRow,startingCol, b)==1) {//rook, queen vertical/horizontal
			for( int i = row+1; i<8;i++) {
				if (b[i][col] != null) {
					if ((b[i][col] instanceof Rook || b[i][col] instanceof Queen) && (b[i][col].color != !color)) {
						return false;
					} else {
						break;
					}
				}
			}

			for( int i = row-1; i>=0;i--) {
				if (b[i][col] != null) {
					if ((b[i][col] instanceof Rook || b[i][col] instanceof Queen) && (b[i][col].color != !color)) {
						return false;
					} else {
						break;
					}
				}
			}

			for( int i = col+1; i<8;i++) {
				if (b[row][i] != null) {
					if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color != !color)) {
						return false;
					} else {
						break;
					}
				}
			}

			for( int i = col-1; i>=0;i--) {
				if (b[row][i] != null) {
					if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color != !color)) {
						return false;
					} else {
						break;
					}
				}
			}
		} else if (safe(startingRow,startingCol, b)==2) {//diagonal
			for( int i = row+1, j = col +1; i<8 && j<8;i++, j++) {
				if (b[i][j] != null) {
					if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == !color)) {
						return false;
					} else {
						break;
					}
				}
			}

			for( int i = row+1, j = col-1; i<8 && j>=0;i++, j--) {
				if (b[i][j] != null) {
					if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == !color)) {
						return false;
					} else {
						break;
					}
				}
			}		

			for( int i = row-1, j = col +1; i>=0 && j<8;i--, j++) {
				if (b[i][j] != null) {
					if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == !color)) {
						return false;
					} else {
						break;
					}
				}
			}

			for( int i = row-1, j = col-1; i>=0 && j>=0;i--, j--) {
				if (b[i][j] != null) {
					if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == !color)) {
						return false;
					} else {
						break;
					}
				}
			}
		} else if (safe(startingRow,startingCol, b)==3) {//knight
			try {
				if (b[row+2][col+1]!= null) {
					if (b[row+2][col+1].getColorBoolean()!=color && b[row+2][col+1] instanceof Knight) {
						if (((Knight)b[row+2][col+1]).safe(row+2, col+1, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row+2][col-1]!= null) {
					if (b[row+2][col-1].getColorBoolean()!=color && b[row+2][col-1] instanceof Knight) {
						if (((Knight)b[row+2][col-1]).safe(row+2, col-1, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row-2][col+1]!= null) {
					if (b[row-2][col+1].getColorBoolean()!=color && b[row-2][col+1] instanceof Knight) {
						if (((Knight)b[row-2][col+1]).safe(row-2, col+1, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row-2][col-1]!= null) {
					if (b[row-2][col-1].getColorBoolean()!=color && b[row-2][col-1] instanceof Knight) {
						if (((Knight)b[row-2][col-1]).safe(row-2, col-1, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
			
			try {
				if (b[row+1][col+2]!= null) {
					if (b[row+1][col+2].getColorBoolean()!=color && b[row+1][col+2] instanceof Knight) {
						if (((Knight)b[row+1][col+2]).safe(row+1, col+2, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row+1][col-2]!= null) {
					if (b[row+1][col-2].getColorBoolean()!=color && b[row+1][col-2] instanceof Knight) {
						if (((Knight)b[row+1][col-2]).safe(row+1, col-2, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row-1][col+2]!= null) {
					if (b[row-1][col+2].getColorBoolean()!=color && b[row-1][col+2] instanceof Knight) {
						if (((Knight)b[row-1][col+2]).safe(row-1, col+2, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row-1][col-2]!= null) {
					if (b[row-1][col-2].getColorBoolean()!=color && b[row-1][col-2] instanceof Knight) {
						if (((Knight)b[row-1][col-2]).safe(row-1, col-2, b)!=0);
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		} else if (safe(startingRow,startingCol, b)==4) {//pawn, king diagonal
			try {
				if (b[row+1][col+1]!=null) {
					if ((((b[row+1][col+1] instanceof Pawn && color) || (b[row+1][col+1] instanceof King)) && b[row+1][col+1].getColorBoolean()==color)) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row+1][col-1]!=null) {
					if ((((b[row+1][col-1] instanceof Pawn && color) || (b[row+1][col-1] instanceof King))  && b[row+1][col-1].getColorBoolean()==color)) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
			
			try {
				if (b[row-1][col+1]!=null) {
					if ((((b[row-1][col+1] instanceof Pawn && !color) || (b[row-1][col+1] instanceof King))  && b[row-1][col+1].getColorBoolean()==color)) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row-1][col-1]!=null) {
					if ((((b[row-1][col-1] instanceof Pawn && !color) || (b[row-1][col-1] instanceof King)) && b[row-1][col-1].getColorBoolean()==color)) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
			
		} else if (safe(startingRow,startingCol, b)==5) {//King
/*THIS CASE SHOULD NEVER HAPPEN BECAUSE A KING CAN NOT BE NEXT TO ANOTHER KING*/
			try {
				if (b[row+1][col]!=null) {
					if (b[row+1][col] instanceof King 
							&& b[row+1][col].getColorBoolean()!=color) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row-1][col]!=null) {
					if (b[row-1][col] instanceof King 
							&& b[row-1][col].getColorBoolean()!=color) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row][col+1]!=null) {
					if (b[row][col+1] instanceof King 
							&& b[row][col+1].getColorBoolean()!=color) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}

			try {
				if (b[row][col-1]!=null) {
					if (b[row][col-1] instanceof King 
							&& b[row][col-1].getColorBoolean()!=color) {
						return false;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		return true;
	}

	/**
	 * Determines if the requested move from the player is legal
	 * @parameter start A value of type string, b A member of a 2D array, kRow A value of type int, kCol A value of type int
	 * @return boolean value true if it is a possible move, boolean value false if it is not
	 */
	@Override
	public boolean possibleMove(String start, Piece[][] b, int kRow, int kCol) {

		int startingRow = Integer.parseInt(start.charAt(1)+"") - 1;
		int startingCol = Board.columnNum(start.charAt(0));
		try{
			if (b[startingRow+1][startingCol] != null) {
				if(this.safe(startingCol, startingRow+1, b)==0){
					if (b[startingRow+1][startingCol].getColorBoolean()!=color)
						return true;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow+1][startingCol+1] != null) {
				if(this.safe(startingCol+1, startingRow+1,b)==0){
					if (b[startingRow+1][startingCol+1].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow][startingCol+1] != null) {
				if(this.safe(startingCol+1, startingRow,b)==0){
					if (b[startingRow][startingCol+1].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow-1][startingCol+1] != null) {
				if(this.safe(startingCol+1, startingRow-1,b)==0){
					if (b[startingRow-1][startingCol+1].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow-1][startingCol] != null) {
				if(this.safe(startingCol, startingRow-1,b)==0){
					if (b[startingRow-1][startingCol].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow-1][startingCol-1] != null) {
				if(this.safe(startingCol-1, startingRow-1,b)==0){
					if (b[startingRow-1][startingCol-1].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow][startingCol-1] != null) {
				if(this.safe(startingCol-1, startingRow,b)==0){
					if (b[startingRow][startingCol-1].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		try{
			if (b[startingRow+1][startingCol-1] != null) {
				if(this.safe(startingCol-1, startingRow+1,b)==0){
					if (b[startingRow+1][startingCol-1].getColorBoolean()!=color)
						return true;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		// TODO Auto-generated method stub
		return false;
	}

	
}