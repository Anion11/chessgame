package com.example.chessgame.Move;

/**
 * Класс Move представляет ход в игре шахматы с начальными и конечными координатами.
 */
public class Move implements MoveInterface {
    private final int startX; // Начальная координата x
    private final int startY; // Начальная координата y
    private final int endX; // Конечная координата x
    private final int endY; // Конечная координата y

    /**
     * Конструктор класса Move, инициализирует ход с заданными координатами.
     *
     * @param startX начальная координата x
     * @param startY начальная координата y
     * @param endX конечная координата x
     * @param endY конечная координата y
     */
    public Move(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Метод для получения начальной координаты x.
     * @return начальная координата x
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Метод для получения начальной координаты y.
     * @return начальная координата y
     */
    public int getStartY() {
        return startY;
    }

    /**
     * Метод для получения конечной координаты x.
     * @return конечная координата x
     */
    public int getEndX() {
        return endX;
    }

    /**
     * Метод для получения конечной координаты y.
     * @return конечная координата y
     */
    public int getEndY() {
        return endY;
    }
}