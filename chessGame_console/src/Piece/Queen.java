package Piece;
import Board.Board;
public class Queen extends Piece {
    public Queen(PieceColor color, String ch) {
        super(color, ch);
    }
    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Проверяем, что начальная позиция не совпадает с конечной позицией
        if (startX == endX && startY == endY) {
            return false;
        }

        // Проверяем, что между начальной и конечной позицией нет других фигур по горизонтали, вертикали
        if (startX == endX || startY == endY) {
            int dx = Integer.compare(endX, startX);
            int dy = Integer.compare(endY, startY);
            int x = startX + dx;
            int y = startY + dy;

            while (x != endX || y != endY) {
                if (board.getPiece(x, y) != null) {
                    return false;
                }
                x += dx;
                y += dy;
            }
        }
        else {
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
        }
        // Проверяем, что в конечной точке либо пусто либо фигура противоположного цвета
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() == getColor()) return false;
        return true;
    }
    private boolean isClearHorizontalPath(Board board, int startX, int startY, int endX, int endY) {

        int step = (startX < endX) ? 1 : -1;
        for (int y = startY + step; y != endY; y += step) {
            if (board.getPiece(startX, y) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isClearVerticalPath(Board board, int startX, int startY, int endX, int endY) {
        int step = (startX < endX) ? 1 : -1;
        for (int x = startX + step; x != endX; x += step) {
            if (board.getPiece(x, startY) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isClearDiagonalPath(Board board, int startX, int startY, int endX, int endY) {
        int stepX = (startX < endX) ? 1 : -1;
        int stepY = (startY < endY) ? 1 : -1;
        int x = startX + stepX;
        int y = startY + stepY;
        while (x != endX && y != endY) {
            if (board.getPiece(x, y) != null) {
                return false;
            }
            x += stepX;
            y += stepY;
        }
        return true;
    }
}