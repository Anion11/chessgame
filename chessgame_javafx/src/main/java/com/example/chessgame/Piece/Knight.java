package com.example.chessgame.Piece;
import com.example.chessgame.Board.Board;
import com.example.chessgame.Move.Move;
import javafx.scene.paint.Color;
/**
 * Класс Knight представляет коня в игре шахмат.
 */
public class Knight extends Piece {
    /**
     * Конструктор для создания коня определенного цвета с изображением.
     *
     * @param color цвет фигуры
     * @param ch    изображение фигуры
     */
    public Knight(PieceColor color, String ch) {
        super(color, ch);
    }
    /**
     * Метод для определения всех возможных ходов коня на шахматной доске.
     *
     * @param board  игровая доска
     * @param startX начальная координата x
     * @param startY начальная координата y
     */
    @Override
    public void getValidMove(Board board, int startX, int startY) {
        // Логика определения всех возможных ходов для коня
        int currentX;
        int currentY;
        int[][] possibleMoves = { { 2, 1}, {1, 2}, {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}, {2, -1}, {1, -2} };

        for (int[] possibleMove : possibleMoves) {
            boolean flag = false;
            currentX = startX + possibleMove[0];
            currentY = startY + possibleMove[1];
            if (currentX >= 0 && currentX < 8 && currentY >= 0 && currentY < 8) {
                if (board.getPiece(currentX, currentY) != null) {
                    if (board.getPiece(currentX, currentY).getColor() == this.getColor()) {
                        flag = true;
                    }
                    if (!flag) board.getSquares()[currentX][currentY].setFill(Color.RED);
                    flag = true;
                }
                if (!flag) board.getSquares()[currentX][currentY].setFill(Color.rgb(255, 202, 134));
            }
        }
    }
    /**
     * Метод для проверки допустимости хода для коня.
     *
     * @param board игровая доска
     * @param move  ход, который требуется проверить
     * @return true, если ход допустим, иначе false
     */
    @Override
    public boolean isValidMove(Board board, Move move) {
        // Логика проверки допустимости хода для коня
        int startX = move.getStartX();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int startY = move.getStartY();
        // Проверяем, что начальная позиция не совпадает с конечной позицией
        if (startX == endX && startY == endY) {
            return false;
        }

        // Проверяем, что ход коня является допустимым
        int[][] possibleMoves = { { 2, 1}, {1, 2}, {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}, {2, -1}, {1, -2} };
        boolean flag = false;
        for (int[] possibleMove : possibleMoves) {
            if (endX == startX + possibleMove[0] && endY == startY + possibleMove[1]) flag = true;
        }
        if (!flag) return false;
        // Проверяем, что в конечной точке либо пусто либо фигура противоположного цвета
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() == getColor()) return false;
        return true;
    }
}