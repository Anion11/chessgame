package Piece;
import Board.Board;
public class Knight extends Piece {
    public Knight(PieceColor color, String ch) {
        super(color, ch);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Проверяем, что начальная позиция не совпадает с конечной позицией
        if (startX == endX && startY == endY) {
            return false;
        }

        // Проверяем, что ход коня является допустимым
        int diffX = Math.abs(startX - endX);
        int diffY = Math.abs(startY - endY);
        if (!(diffX == 2 && diffY == 1)) {
            return false;
        }
        // Проверяем, что в конечной точке либо пусто либо фигура противоположного цвета
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() == getColor()) return false;
        return true;
    }
}