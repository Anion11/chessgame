package com.example.chessgame.Piece;
import com.example.chessgame.Board.Board;
import com.example.chessgame.Move.Move;
import javafx.scene.paint.Color;
/**
 * Класс Pawn представляет пешку в игре шахмат.
 */
public class Pawn extends Piece {
    /**
     * Конструктор для создания пешки определенного цвета с изображением.
     *
     * @param color цвет фигуры
     * @param ch    изображение фигуры
     */
    public Pawn(PieceColor color, String ch) {
        super(color, ch);
    }
    /**
     * Метод для определения всех возможных ходов пешки на шахматной доске.
     *
     * @param board  игровая доска
     * @param startX начальная координата x
     * @param startY начальная координата y
     */
    @Override
    public void getValidMove(Board board, int startX, int startY) {
        // Логика определения всех возможных ходов для пешки
        int temp;
        if (this.getColor() == PieceColor.WHITE) {
            temp = 1;
        }
        else {
            temp = -1;
        }
        if (startX == 1 || startX == 6) {
            if (board.getSquares()[startX+temp][startY].getUserData() == null) {
                board.getSquares()[startX+temp][startY].setFill(Color.rgb( 255,202,134));
                if (board.getSquares()[startX+temp*2][startY].getUserData() == null) {
                    board.getSquares()[startX+temp*2][startY].setFill(Color.rgb( 255,202,134));
                }
            }
        }
        else {
            if (board.getSquares()[startX+temp][startY].getUserData() == null) {
                board.getSquares()[startX+temp][startY].setFill(Color.rgb( 255,202,134));
            }
        }
        if (startY != 7) {
            if (board.getSquares()[startX+temp][startY+1].getUserData() != null) {
                Piece p = (Piece) board.getSquares()[startX+temp][startY+1].getUserData();
                if (p.getColor() != this.getColor()) {
                    board.getSquares()[startX+temp][startY+1].setFill(Color.RED);
                }
            }
        }
        if (startY != 0) {
            if (board.getSquares()[startX+temp][startY-1].getUserData() != null) {
                Piece p = (Piece) board.getSquares()[startX+temp][startY-1].getUserData();
                if (p.getColor() != this.getColor()) {
                    board.getSquares()[startX+temp][startY-1].setFill(Color.RED);
                }
            }
        }
    }
    /**
     * Метод для проверки допустимости хода для пешки.
     *
     * @param board игровая доска
     * @param move  ход, который требуется проверить
     * @return true, если ход допустим, иначе false
     */
    @Override
    public boolean isValidMove(Board board, Move move) {
        // Логика проверки допустимости хода для пешки
        // Нельзя ходить назад
        int startX = move.getStartX();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int startY = move.getStartY();
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