package com.example.chessgame.Player;

import com.example.chessgame.Board.Board;
import com.example.chessgame.Move.Move;
import com.example.chessgame.Piece.PieceColor;

import java.util.Objects;

/**
 * Класс Player представляет игрока в шахматах с именем и цветом его фигур.
 */
public class Player implements PlayerInterface {
    private final String name; // Имя игрока
    private final PieceColor pieceColor; // Цвет фигур игрока

    /**
     * Метод для получения имени игрока.
     * @return имя игрока
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения цвета фигур игрока.
     * @return цвет фигур игрока
     */
    public PieceColor getColor() {
        return pieceColor;
    }

    /**
     * Конструктор класса Player, инициализирует объект игрока с заданным именем и цветом фигур.
     * @param name имя игрока
     * @param pieceColor цвет фигур игрока
     */
    public Player(String name, PieceColor pieceColor) {
        this.name = name;
        this.pieceColor = pieceColor;
    }
}
