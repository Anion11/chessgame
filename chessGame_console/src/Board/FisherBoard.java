package Board;

import Piece.Piece;
import Piece.Pawn;
import Piece.Rook;
import Piece.Knight;
import Piece.Bishop;
import Piece.Queen;
import Piece.King;
import Piece.PieceColor;

import java.util.Arrays;
import java.util.Random;

public class  FisherBoard extends Board {
    public Piece[][] resetBoard() {
        Piece[][] pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(PieceColor.WHITE, "P");
            pieces[6][i] = new Pawn(PieceColor.BLACK, "P^");
        }
        int BishopPosition = new Random().nextInt(8);
        pieces[0][BishopPosition] = new Bishop(PieceColor.WHITE, "B");
        pieces[7][BishopPosition] = new Bishop(PieceColor.BLACK, "B^");

        int BishopPosition2;
        if (BishopPosition % 2 == 0) {
            do {
                BishopPosition2 = new Random().nextInt(8);
            } while (pieces[0][BishopPosition2] != null || BishopPosition2 % 2 == 0);
        }
        else {
            do {
                BishopPosition2 = new Random().nextInt(8);
            } while (pieces[0][BishopPosition2] != null || BishopPosition2 % 2 != 0);
        }
        pieces[0][BishopPosition2] = new Bishop(PieceColor.WHITE, "B");
        pieces[7][BishopPosition2] = new Bishop(PieceColor.BLACK, "B^");

        int RookPosition;
        do {
            RookPosition = new Random().nextInt(8);
        } while (pieces[0][RookPosition] != null);
        pieces[0][RookPosition] = new Rook(PieceColor.WHITE, "R");
        pieces[7][RookPosition] = new Rook(PieceColor.BLACK, "R^");
        int RookPosition2;
        do {
            RookPosition2 = new Random().nextInt(8);
        } while (pieces[0][RookPosition2] != null || Math.abs(RookPosition2 - RookPosition) < 2);
        pieces[0][RookPosition2] = new Rook(PieceColor.WHITE, "R");
        pieces[7][RookPosition2] = new Rook(PieceColor.BLACK, "R^");

        int KingPosition;
        do {
            KingPosition = new Random().nextInt(8);
        } while (pieces[0][KingPosition] != null || ((KingPosition <= RookPosition || KingPosition >= RookPosition2) && (KingPosition >= RookPosition || KingPosition <= RookPosition2)));
        pieces[0][KingPosition] = new King(PieceColor.WHITE, "K");
        pieces[7][KingPosition] = new King(PieceColor.BLACK, "K^");

        int QueenPosition;
        do {
            QueenPosition = new Random().nextInt(8);
        } while (pieces[0][QueenPosition] != null);
        pieces[0][QueenPosition] = new Queen(PieceColor.WHITE, "Q");
        pieces[7][QueenPosition] = new Queen(PieceColor.BLACK, "Q^");

        int KnightPosition;
        do {
            KnightPosition = new Random().nextInt(8);
        } while (pieces[0][KnightPosition] != null);

        pieces[0][KnightPosition] = new Knight(PieceColor.WHITE, "N");
        pieces[7][KnightPosition] = new Knight(PieceColor.BLACK, "N^");
        do {
            KnightPosition = new Random().nextInt(8);
        } while (pieces[0][KnightPosition] != null);
        pieces[0][KnightPosition] = new Knight(PieceColor.WHITE, "N");
        pieces[7][KnightPosition] = new Knight(PieceColor.BLACK, "N^");

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
        return pieces;
    }
}
