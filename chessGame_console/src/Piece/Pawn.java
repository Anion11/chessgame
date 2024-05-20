package Piece;
import Board.Board;
public class Pawn extends Piece {
    public Pawn(PieceColor color, String ch) {
        super(color, ch);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Нельзя ходить назад
        if (!((startX < endX && this.getColor() == PieceColor.WHITE) || (endX < startX && !(this.getColor() == PieceColor.WHITE)))) {
            return false;
        }
        // С начальной позиции можн осделать 2 шага вперед
        if (startX == 1 || startX == 6) {
            if (Math.abs(endX - startX) > 2) return false;
        }
        else {
            if (Math.abs(endX - startX) != 1) return false;
        }
        // Проверка возможности атаки по диагонали
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() != this.getColor() && Math.abs(endX - startX) == 1 && Math.abs(endY - startY) == 1) {
            return true;
        }
        // Т.к мы не били то теперь нельзя ходить по диагонали
        if (Math.abs(endY - startY) != 0) return false;
        // Проверка возможности движения вперед (есть ли кто то в нашей точке)
        if (board.getPiece(endX, endY) != null) {
            return false;
        }
        // Проверка возможности движения вперед (есть ли кто то на пути)
        if (Math.abs(endX - startX) == 2) {
            if (this.getColor() == PieceColor.WHITE) return board.getPiece(endX - 1, endY) == null;
            else return board.getPiece(endX + 1, endY) == null;
        }
        return true;
    }
}