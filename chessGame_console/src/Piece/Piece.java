package Piece;
import Board.Board;
public abstract class Piece {
    private final PieceColor color;
    private final String ch;
    public Piece(PieceColor color, String ch) {
        this.color = color;
        this.ch = ch;
    }
    public String getChar() {
        return ch;
    }
    public PieceColor getColor() {
        return color;
    }
    public abstract boolean isValidMove(Board board, int startX, int startY, int endX, int endY);
}