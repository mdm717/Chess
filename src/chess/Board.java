package chess;

import pieces.*;

public class Board {
	public Piece[][] board = new Piece[8][8];
	
	public Board() {
		for(int i = 0; i < 8; i++) {
//			board[1][i]=new Pawn(true);
//			board[6][i]=new Pawn(false);
		}
/*
		board[7][0]=new Rook(false);
		board[7][1]=new Knight(false);
		board[7][2]=new Bishop(false);
		board[7][3]=new Queen(false);
		board[7][4]=new King(false);
		board[7][5]=new Bishop(false);
		board[7][6]=new Knight(false);
		board[7][7]=new Rook(false);
		
		board[0][0]=new Rook(true);
		board[0][1]=new Knight(true);
		board[0][2]=new Bishop(true);
		board[0][3]=new Queen(true);
		board[0][4]=new King(true);
		board[0][5]=new Bishop(true);
		board[0][6]=new Knight(true);
		board[0][7]=new Rook(true);
*/
		board[7][4] = new King(false);
//		board[7][7] = new Pawn(false);
	

		board[6][4] = new Pawn(true);
//		board[1][7] = new Rook(true);
//		board[2][7] = new Rook(true);
//		board[1][7] = new Rook(true);
		board[1][0] = new Rook(true);
		board[5][4] = new King(true);
		
		
	}
	
	public static int columnNum(char c) {
		switch (c) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		}
		return -1;
	}
	
	public String toString() {
		String str = "";
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					str= str+(board[i][j].toString()+" ");
				} else {
					if ((i+j)%2 == 1) {
						str= str+("   ");
					} else {
						str= str+("## ");
					}
				}
			}
			str= str+((i+1)+"\n");
		}

		str= str+(" a  b  c  d  e  f  g  h");
		return str;
	}
	
}
