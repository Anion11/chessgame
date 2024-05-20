package Piece;
import Board.Board;
public class King extends Piece {
    public King(PieceColor color, String ch) {
        super(color, ch);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Проверяем, что начальная позиция не совпадает с конечной позицией
        if (startX == endX && startY == endY) {
            return false;
        }
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        if (deltaX > 1 && deltaY > 1) {
            return false;
        }
        // Проверяем, если фигура в конечной точке наша, то ход недоступен
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() == this.getColor()) {
            return false;
        }
        return true;
    }
}
