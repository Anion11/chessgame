package com.example.chessgame.Piece;
import com.example.chessgame.Board.Board;
import com.example.chessgame.Move.Move;
import javafx.scene.paint.Color;
/**
 * Класс King представляет короля в игре шахмат.
 */
public class King extends Piece {
    /**
     * Конструктор для создания короля определенного цвета с изображением.
     *
     * @param color цвет фигуры
     * @param ch    изображение фигуры
     */
    public King(PieceColor color, String ch) {
        super(color, ch);
    }
    /**
     * Метод для определения всех возможных ходов короля на шахматной доске.
     *
     * @param board  игровая доска
     * @param startX начальная координата x
     * @param startY начальная координата y
     */
    @Override
    public void getValidMove(Board board, int startX, int startY) {
        // Логика определения всех возможных ходов для короля
        int[][] possibleMoves = { {1, 0}, {0, 1}, {1, 1}, {-1, -1}, {0, -1}, {-1, 0}, {-1, 1}, {1, -1} };
        for (int[] item: possibleMoves) {
            if (startX + item[0] >= 0 && startX + item[0] < 8 && startY + item[1] >= 0 && startY + item[1] < 8) {
                if (board.getPiece((startX + item[0]), (startY + item[1])) != null) {
                    if (board.getPiece((startX + item[0]), (startY + item[1])).getColor() != getColor()) board.getSquares()[(startX + item[0])][(startY + item[1])].setFill(Color.RED);
                }
                else board.getSquares()[(startX + item[0])][(startY + item[1])].setFill(Color.rgb(255, 202, 134));
            }

        }
    }
    /**
     * Метод для проверки допустимости хода для короля.
     *
     * @param board игровая доска
     * @param move  ход, который требуется проверить
     * @return true, если ход допустим, иначе false
     */
    @Override
    public boolean isValidMove(Board board, Move move) {
        // Логика проверки допустимости хода для короля
        int startX = move.getStartX();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int startY = move.getStartY();
        // Проверяем, что начальная позиция не совпадает с конечной позицией
        if (startX == endX && startY == endY) {
            return false;
        }
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }
        // Проверяем, если фигура в конечной точке наша, то ход недоступен
        if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getColor() == this.getColor()) {
            return false;
        }
        return true;
    }
}
