package com.example.chessgame.Piece;
import com.example.chessgame.Board.Board;
import com.example.chessgame.Move.Move;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * Класс Bishop представляет слона в игре шахмат.
 */
public class Bishop extends Piece {
    /**
     * Конструктор для создания слона определенного цвета с изображением.
     *
     * @param color цвет фигуры
     * @param ch    изображение фигуры
     */
    public Bishop(PieceColor color, String ch) {
        super(color, ch);
    }
    /**
     * Метод для определения всех возможных ходов слона на шахматной доске.
     *
     * @param board  игровая доска
     * @param startX начальная координата x
     * @param startY начальная координата y
     */
    @Override
    public void getValidMove(Board board, int startX, int startY) {
        // Логика определения всех возможных ходов для слона
        int temp = 1;
        for (int i = 0; i < 4; i++) {
            if (i == 1) temp = -1;
            int currentX = startX;
            int currentY = startY;
            while (currentX >= 0 && currentX < 8 && currentY >= 0 && currentY < 8) {

                if (board.getSquares()[currentX][currentY].getUserData() != null && currentX != startX && currentY != startY) {
                    Piece p = (Piece) board.getSquares()[currentX][currentY].getUserData();
                    if (p.getColor() != this.getColor()) {
                        board.getSquares()[currentX][currentY].setFill(Color.RED);
                    }
                    break;
                }
                if (startX != currentX && startY != currentY)
                    board.getSquares()[currentX][currentY].setFill(Color.rgb(255, 202, 134));
                if (i == 2) {
                    currentX += temp;
                    currentY -= temp;
                } else if (i == 3) {
                    currentX -= temp;
                    currentY += temp;
                } else {
                    currentX += temp;
                    currentY += temp;
                }
            }
        }
    }
    /**
     * Метод для проверки допустимости хода для слона.
     *
     * @param board игровая доска
     * @param move  ход, который требуется проверить
     * @return true, если ход допустим, иначе false
     */
    @Override
    public boolean isValidMove(Board board, Move move) {
        // Логика проверки допустимости хода для слона
        int startX = move.getStartX();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int startY = move.getStartY();
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