package pieces;

import chess.Board;

public class Knight extends Piece {

	public Knight(boolean c) {
		super(c);
		symbol = 'N';
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
		
		if (Math.abs(startRow-targetRow)==2 && Math.abs(startCol-targetCol)==1 ||
				Math.abs(startRow-targetRow)==1 && Math.abs(startCol-targetCol)==2) {
			return true;
		}
		
		return false;
	}
	

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

}
