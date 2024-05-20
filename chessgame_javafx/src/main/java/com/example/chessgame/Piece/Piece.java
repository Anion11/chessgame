package com.example.chessgame.Piece;
import com.example.chessgame.Board.Board;
import com.example.chessgame.Move.Move;
/**
 * Абстрактный класс Piece представляет базовую фигуру в игре шахмат.
 */
public abstract class Piece {
    private final PieceColor color; // Цвет фигуры
    private final String image; // Путь к изображению фигуры
    /**
     * Конструктор для создания фигуры определенного цвета с определенным изображением.
     *
     * @param color цвет фигуры
     * @param image путь к изображению фигуры
     */
    public Piece(PieceColor color, String image) {
        this.color = color;
        this.image = image;
    }
    /**
     * Метод для получения цвета фигуры.
     *
     * @return цвет фигуры
     */
    public PieceColor getColor() {
        return color;
    }
    /**
     * Метод для получения пути к изображению фигуры.
     *
     * @return путь к изображению фигуры
     */
    public String getImage() {
        return image;
    }
    /**
     * Абстрактный метод для определения всех возможных ходов фигуры на шахматной доске.
     *
     * @param board   игровая доска
     * @param startX  начальная координата x
     * @param startY  начальная координата y
     */
    public abstract void getValidMove(Board board, int startX, int startY);
    /**
     * Абстрактный метод для проверки допустимости конкретного хода для фигуры.
     *
     * @param chessBoard игровая доска
     * @param move       ход, который нужно проверить
     * @return true, если ход допустим, иначе false
     */
    public abstract boolean isValidMove(Board chessBoard, Move move);
}