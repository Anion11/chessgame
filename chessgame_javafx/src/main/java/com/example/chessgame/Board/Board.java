package com.example.chessgame.Board;

import com.example.chessgame.Piece.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Абстрактный класс Board представляет общую структуру для игровых досок шахмат.
 * Данный класс содержит основные методы и поля, необходимые для работы с игровой доской.
 */
public abstract class Board {
    Rectangle[][] squares; // Двумерный массив квадратов, представляющих клетки доски
    public final int SQUARE_SIZE; // Размер одной клетки доски
    public Board(int SQUARE_SIZE) {
        this.SQUARE_SIZE = SQUARE_SIZE;
    }

    /**
     * Абстрактный метод для создания игровой доски.
     * Этот метод должен быть реализован в подклассах.
     */
    public abstract GridPane createBoard();

    /**
     * Метод для получения двумерного массива клеток доски.
     * @return двумерный массив клеток
     */
    public Rectangle[][] getSquares() {
        return squares;
    }

    /**
     * Метод для получения клетки по заданным координатам.
     * @param x координата x
     * @param y координата y
     * @return клетка по указанным координатам
     */
    public Rectangle getRect(int x, int y) {
        return squares[x][y];
    }
    /**
     * Метод для получения фигуры, расположенной на указанной клетке.
     * @param endX координата x клетки
     * @param endY координата y клетки
     * @return фигура на указанной клетке
     */
    public Piece getPiece(int endX, int endY) {
        return (Piece) squares[endX][endY].getUserData();
    }

    /**
     * Абстрактный метод для добавления фигуры на доску.
     * @param piece фигура для добавления
     * @param row строка, на которой нужно разместить фигуру
     * @param col столбец, на котором нужно разместить фигуру
     */
    public abstract void addPiece(Piece piece, int row, int col);

    /**
     * Абстрактный метод для удаления фигуры с указанной клетки доски.
     * @param row строка, на которой расположена удаляемая фигура
     * @param col столбец, на котором расположена удаляемая фигура
     * @param piecePane панель, содержащая изображение удаляемой фигуры
     */
    public abstract void removePiece(int row, int col, Pane piecePane);
}
