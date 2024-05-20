package Piece;
import Board.Board;
public class Bishop extends Piece {
    public Bishop(PieceColor color, String ch) {
        super(color, ch);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {

        // Проверяем, что начальная позиция не совпадает с конечной позицией
        if (startX == endX && startY == endY) {
            return false;
        }

        // Проверяем, что начальная позиция и конечная позиция находятся на диагонали
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        if (deltaX != deltaY) {
            return false;
        }

        // Проверяем, что между начальной и конечной позицией нет других фигур
        int xDirection = (endX > startX) ? 1 : -1;
        int yDirection = (endY > startY) ? 1 : -1;
        int currentX = startX + xDirection;
        int currentY = startY + yDirection;
        while (currentX != endX && currentY != endY) {
            if (board.getPiece(currentX, currentY) != null) {
                return false;
            }
            currentX += xDirection;
            currentY += yDirection;
        }
        // Проверяем, если фигура в конечной точке наша, то ход недоступен
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() == this.getColor()) {
            return false;
        }

        return true;
    }
}