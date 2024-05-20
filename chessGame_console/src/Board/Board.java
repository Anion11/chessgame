package Board;
import Piece.*;
import Move.Move;
public abstract class Board {
    private final Piece[][] pieces;
    public Board() {
        pieces = resetBoard();
    }
    public abstract Piece[][] resetBoard();

    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }

    public void makeMove(Move move) {
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();

        Piece piece = pieces[startX][startY];
        pieces[startX][startY] = null;
        pieces[endX][endY] = piece;
    }
}
