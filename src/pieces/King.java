package pieces;

import chess.Board;

/**
 * King.java - This class defines the King piece
 * @author Craig Sirota cms631
 * @author Matthew Marrazzo mdm289
 */

public class King extends Piece {
	private int moves;
	private boolean check;
	
	/**
	 * Class constructor, sets piece color and symbol, as well as sets other private variables
	 * @param c	used to set the color of the piece, true=white, false=black
	 */
	public King(boolean c) {
		super(c);
		symbol = 'K';
		moves = 0;
		check = false;
		// TODO Auto-generated constructor stub
	}


	/**
	 * This method checks if a player's request for a move is valid.
	 * @param start		the starting spot of the piece requesting to move
	 * @param target	the target spot of the piece requesting to move
	 * @param b			the 2x2 matrix of pieces
	 * @return	true if the piece can be moved to the target, false if it can't
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
	 * 					1-4 - rook, queen (vertical or horizontal)
	 *  				5-8 - bishop, queen (diagonal)
	 * 					9-16 - knight
	 * 					17-20 - pawn, king (diagonal)
	 *  				21-24 - king (vertical or horizontal)
	 *  					numbers correspond to lines of attack
	 */
	public int safe(int row, int col, Piece[][] b) {
		
		/*
		 *  RETURNS
		 *  0 - safe
		 *  1-4 - rook, queen (vertical or horizontal)
		 *  5-8 - bishop, queen (diagonal)
		 *  9-16 - knight
		 *  17-20 - pawn, king (diagonal)
		 *  21-24 - king (vertical or horizontal)
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
					return 2;
				} else {
					break;
				}
			}
		}
		
		for( int i = col+1; i<8;i++) {
			if (b[row][i] != null) {
				if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color != color)) {
					return 3;
				} else {
					break;
				}
			}
		}

		for( int i = col-1; i>=0;i--) {
			if (b[row][i] != null) {
				if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color != color)) {
					return 4;
				} else {
					break;
				}
			}
		}
		
//checking queen and bishop

		for( int i = row+1, j = col +1; i<8 && j<8;i++, j++) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 5;
				} else {
					break;
				}
			}
		}


		for( int i = row+1, j = col-1; i<8 && j>=0;i++, j--) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 6;
				} else {
					break;
				}
			}
		}
		

		for( int i = row-1, j = col +1; i>=0 && j<8;i--, j++) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 7;
				} else {
					break;
				}
			}
		}

		for( int i = row-1, j = col-1; i>=0 && j>=0;i--, j--) {
			if (b[i][j] != null) {
				if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color != color)) {
					return 8;
				} else {
					break;
				}
			}
		}

//knight		
		
		try {
			if (b[row+2][col+1]!= null) {
				if (b[row+2][col+1].getColorBoolean()!=color && b[row+2][col+1] instanceof Knight) {
					return 9;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row+2][col-1]!= null) {
				if (b[row+2][col-1].getColorBoolean()!=color && b[row+2][col-1] instanceof Knight) {
					return 10;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-2][col+1]!= null) {
				if (b[row-2][col+1].getColorBoolean()!=color && b[row-2][col+1] instanceof Knight) {
					return 11;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-2][col-1]!= null) {
				if (b[row-2][col-1].getColorBoolean()!=color && b[row-2][col-1] instanceof Knight) {
					return 12;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		
		try {
			if (b[row+1][col+2]!= null) {
				if (b[row+1][col+2].getColorBoolean()!=color && b[row+1][col+2] instanceof Knight) {
					return 13;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row+1][col-2]!= null) {
				if (b[row+1][col-2].getColorBoolean()!=color && b[row+1][col-2] instanceof Knight) {
					return 14;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col+2]!= null) {
				if (b[row-1][col+2].getColorBoolean()!=color && b[row-1][col+2] instanceof Knight) {
					return 15;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col-2]!= null) {
				if (b[row-1][col-2].getColorBoolean()!=color && b[row-1][col-2] instanceof Knight) {
					return 16;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

//Pawn and King
		try {
			if (b[row+1][col+1]!=null) {
				if (((b[row+1][col+1] instanceof Pawn && !color)|| b[row+1][col+1] instanceof King) 
						&& b[row+1][col+1].getColorBoolean()!=color) {
					return 17;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row+1][col-1]!=null) {
				if (((b[row+1][col-1] instanceof Pawn && color) || b[row+1][col-1] instanceof King) 
						&& b[row+1][col-1].getColorBoolean()!=color) {
					return 18;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		

		try {
			if (b[row-1][col+1]!=null) {
				if (((b[row-1][col+1] instanceof Pawn && !color) || b[row-1][col+1] instanceof King) 
						&& b[row-1][col+1].getColorBoolean()!=color) {
					return 19;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col-1]!=null) {
				if (((b[row-1][col-1] instanceof Pawn && !color) || b[row-1][col-1] instanceof King) 
						&& b[row-1][col-1].getColorBoolean()!=color) {
					return 20;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		
	
		
//King
		

		try {
			if (b[row+1][col]!=null) {
				if (b[row+1][col] instanceof King 
						&& b[row+1][col].getColorBoolean()!=color) {
					return 21;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row-1][col]!=null) {
				if (b[row-1][col] instanceof King 
						&& b[row-1][col].getColorBoolean()!=color) {
					return 22;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		

		try {
			if (b[row][col+1]!=null) {
				if (b[row][col+1] instanceof King 
						&& b[row][col+1].getColorBoolean()!=color) {
					return 23;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		try {
			if (b[row][col-1]!=null) {
				if (b[row][col-1] instanceof King 
						&& b[row][col-1].getColorBoolean()!=color) {
					return 24;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}

		
		return 0;
	}

	/**
	 * This method checks if any the spots surrounding the a position are safe
	 * @param startingRow	the row of the center spot
	 * @param startingCol	the column of the center spot
	 * @param b				the 2x2 matrix of pieces
	 * @return boolean values, true if there are no safe empty spots or safe spots occupied by an opponent around the king, false if one of those conditions fails
	 */
	public boolean surroundingSpots(int startingRow, int startingCol, Piece[][] b) {

		try{
			if(this.safe(startingRow+1, startingCol, b)==0){
				if (b[startingRow+1][startingCol] == null) {
					return false;
				}
				if (b[startingRow+1][startingCol].getColorBoolean()!=color)
					return false;
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow+1, startingCol+1,b)==0){
				if (b[startingRow+1][startingCol+1] == null)
					return false;
				if (b[startingRow+1][startingCol+1].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow, startingCol+1,b)==0){
				if (b[startingRow][startingCol+1] == null)
					return false;
				if (b[startingRow][startingCol+1].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow-1, startingCol+1,b)==0){
				if (b[startingRow-1][startingCol+1] == null)
					return false;
				if (b[startingRow-1][startingCol+1].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow-1, startingCol,b)==0){
				if (b[startingRow-1][startingCol] == null)
					return false;
				if (b[startingRow-1][startingCol].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow-1, startingCol-1,b)==0){
				if (b[startingRow-1][startingCol-1] == null)
					return false;
				if (b[startingRow-1][startingCol-1].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow, startingCol-1,b)==0){
				if (b[startingRow][startingCol-1] == null)
					return false;
				if (b[startingRow][startingCol-1].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		
		try{
			if(this.safe(startingRow+1, startingCol-1,b)==0){
				if (b[startingRow+1][startingCol-1] == null)
					return false;
				if (b[startingRow+1][startingCol-1].getColorBoolean()!=color)
					return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}

		return true;
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
	 * @param col	the column of the king
	 * @param row	the row of the king
	 * @param b		the 2x2 matrix of pieces
	 * @return	true if the king is in checkmate, false if not
	 */
	public boolean checkMate2(int col, int row, Piece[][] b) {
		if (safe(row, col, b)==0)
			return false;
		
		int krow= -1, kcol=-1;
		
		if (surroundingSpots(row, col, b)) {
			for (int i = 0; i < 8 && krow == -1; i++) {
				for (int j = 0; j < 8 && kcol == -1; j++) {
					if (b[i][j] != null) {
						if (b[i][j].getColorBoolean()==color && b[i][j] instanceof King) {
							krow = i;
							kcol = j;
						}
					}
				}
			}
			
			switch (safe(row, col, b)) {
				case 1:
					for( int i = row+1; i<8;i++) {
						if (b[i][col] != null) {
							if ((b[i][col] instanceof Rook || b[i][col] instanceof Queen) && (b[i][col].color == color)) {
								if(((King)b[krow][kcol]).safe(i, col, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(i, col, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 2:
					for( int i = row-1; i>=0;i--) {
						if (b[i][col] != null) {
							if ((b[i][col] instanceof Rook || b[i][col] instanceof Queen) && (b[i][col].color == color)) {
								if(((King)b[krow][kcol]).safe(i, col, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(i, col, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 3:
					for( int i = col+1; i<8;i++) {
						if (b[row][i] != null) {
							if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color == color)) {
								if(((King)b[krow][kcol]).safe(i, col, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(row, i, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 4:
					for( int i = col-1; i>=0;i--) {
						if (b[row][i] != null) {
							if ((b[row][i] instanceof Rook || b[row][i] instanceof Queen) && (b[row][i].color == color)) {
								if(((King)b[krow][kcol]).safe(i, col, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(row, i, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 5:
					for( int i = row+1, j = col +1; i<8 && j<8;i++, j++) {
						if (b[i][j] != null) {
							if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == color)) {
								if(((King)b[krow][kcol]).safe(i, j, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(i, j, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 6:
					for( int i = row+1, j = col-1; i<8 && j>=0;i++, j--) {
						if (b[i][j] != null) {
							if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == color)) {
								if(((King)b[krow][kcol]).safe(i, j, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(i, j, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 7:
					for( int i = row-1, j = col+1; j<8 && i>=0;j++, i--) {
						if (b[i][j] != null) {
							if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == color)) {
								if(((King)b[krow][kcol]).safe(i, j, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(i, j, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 8:
					for( int i = row-1, j = col-1; i>=0 && j>=0;i--, j--) {
						if (b[i][j] != null) {
							if ((b[i][j] instanceof Bishop || b[i][j] instanceof Queen) && (b[i][j].color == color)) {
								if(((King)b[krow][kcol]).safe(i, j, b)==0) {
									return true;
								}
							} else {
								if(((King)b[krow][kcol]).safe(i, j, b)!=0) {
									return false;
								}
							}
						}
					}
					break;
				case 9:
					if (((King)b[krow][kcol]).safe(row+2, col+1, b)==0)
						return true;
					break;
				case 10:
					if (((King)b[krow][kcol]).safe(row+2, col-1, b)==0)
						return true;
					break;
				case 11:
					if (((King)b[krow][kcol]).safe(row-2, col+1, b)==0)
						return true;
					break;
				case 12:
					if (((King)b[krow][kcol]).safe(row-2, col-1, b)==0)
						return true;
					break;
				case 13:
					if (((King)b[krow][kcol]).safe(row+1, col+2, b)==0)
						return true;
					break;
				case 14:
					if (((King)b[krow][kcol]).safe(row+1, col-2, b)==0)
						return true;
					break;
				case 15:
					if (((King)b[krow][kcol]).safe(row-1, col+2, b)==0)
						return true;
					break;
				case 16:
					if (((King)b[krow][kcol]).safe(row-1, col-2, b)==0)
						return true;
					break;
				case 17:
					if (((King)b[krow][kcol]).safe(row+1, col+1, b)==0)
						return true;
					break;
				case 18:
					if (((King)b[krow][kcol]).safe(row+1, col-1, b)==0)
						return true;
					break;
				case 19:
					if (((King)b[krow][kcol]).safe(row-1, col+1, b)==0)
						return true;
					break;
				case 20:
					if (((King)b[krow][kcol]).safe(row-1, col-1, b)==0)
						return true;
					break;
				case 21:
					if (((King)b[krow][kcol]).safe(row+1, col, b)==0)
						return true;
					break;
				case 22:
					if (((King)b[krow][kcol]).safe(row-1, col, b)==0)
						return true;
					break;
				case 23:
					if (((King)b[krow][kcol]).safe(row, col+1, b)==0)
						return true;
					break;
				case 24:
					if (((King)b[krow][kcol]).safe(row, col-1, b)==0)
						return true;
					break;
			}
		}
		
		return false;
	}


}