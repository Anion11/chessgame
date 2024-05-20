package com.example.chessgame.Board;

import com.example.chessgame.Piece.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

import static com.example.chessgame.ChessLogic.ChessLogic.handlePieceClick;
import static com.example.chessgame.ChessLogic.ChessLogic.handleSquareClick;

/**
 * Класс ClassicBoard представляет классическую игровую доску для шахмат.
 * Он расширяет абстрактный класс Board.
 *
 * Класс ClassicBoard создает доску размером 8x8 и содержит методы для добавления и удаления фигур.
 *
 * @author Nikita
 * @version 1.1
 */
public class ClassicBoard extends Board {
    // Переменная для доски
    private GridPane gridPane = new GridPane();

    // Конструктор для ClassicBoard
    public ClassicBoard(int SQUARE_SIZE) {
        super(SQUARE_SIZE);
    }

    /**
     * Создает игровую доску с квадратами и размещает на ней фигуры.
     * Возвращает GridPane, представляющий доску.
     */
    public GridPane createBoard() {
        squares = new Rectangle[8][8];

        // Создание квадратов для доски
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle();
                square.setWidth(SQUARE_SIZE);
                square.setHeight(SQUARE_SIZE);
                if ((col + row) % 2 != 0) square.setFill(Color.rgb(68, 45, 37));
                else square.setFill(Color.rgb(177, 149, 118));
                square.setOnMouseClicked(e -> handleSquareClick(squares, square));
                squares[row][col] = square;
                gridPane.add(square, col, row);
            }
        }

        // Добавление фигур на доску
        addPieces();

        return gridPane;
    }

    // Удаление фигуры из конкретной строки и столбца
    public void removePiece(int row, int col, Pane piecePane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                gridPane.getChildren().remove(piecePane);
                break;
            }
        }
        squares[row][col].setUserData(null);
    }

    // Добавление фигуры в конкретную строку и столбец
    public void addPiece(Piece piece, int row, int col) {
        Pane piecePane = new Pane();

        ImageView pieceImage = new ImageView(new Image(piece.getImage()));
        pieceImage.setFitWidth(SQUARE_SIZE);
        pieceImage.setFitHeight(SQUARE_SIZE);
        piecePane.getChildren().add(pieceImage);

        gridPane.add(piecePane, col, row);
        piecePane.setOnMouseClicked(e -> handlePieceClick(piecePane, piece));
    }

    // Добавление начального расположения фигур на доске
    private void addPieces() {
        // Добавление пешек для обеих сторон
        for (int col = 0; col < 8; col++) {
            addPiece(new Pawn(PieceColor.WHITE, "white_pawn.png"), 1, col);
            squares[1][col].setUserData(new Pawn(PieceColor.WHITE, "white_pawn.png"));
            addPiece(new Pawn(PieceColor.BLACK, "black_pawn.png"), 6, col);
            squares[6][col].setUserData(new Pawn(PieceColor.BLACK, "black_pawn.png"));
        }

        // Добавление ладей для обеих сторон
        addPiece(new Rook(PieceColor.WHITE,"white_rook.png"), 0, 0);
        squares[0][0].setUserData(new Rook(PieceColor.WHITE,"white_rook.png"));
        addPiece(new Rook(PieceColor.WHITE,"white_rook.png"), 0, 7);
        squares[0][7].setUserData(new Rook(PieceColor.WHITE,"white_rook.png"));
        addPiece(new Rook(PieceColor.BLACK,"black_rook.png"), 7, 0);
        squares[7][0].setUserData(new Rook(PieceColor.BLACK,"black_rook.png"));
        addPiece(new Rook(PieceColor.BLACK,"black_rook.png"), 7, 7);
        squares[7][7].setUserData(new Rook(PieceColor.BLACK,"black_rook.png"));

        // Добавление коней для обеих сторон
        addPiece(new Knight(PieceColor.WHITE,"white_knight.png"), 0, 1);
        squares[0][1].setUserData(new Knight(PieceColor.WHITE,"white_knight.png"));
        addPiece(new Knight(PieceColor.WHITE,"white_knight.png"), 0, 6);
        squares[0][6].setUserData(new Knight(PieceColor.WHITE,"white_knight.png"));
        addPiece(new Knight(PieceColor.BLACK,"black_knight.png"), 7, 1);
        squares[7][1].setUserData(new Knight(PieceColor.BLACK,"black_knight.png"));
        addPiece(new Knight(PieceColor.BLACK,"black_knight.png"), 7, 6);
        squares[7][6].setUserData(new Knight(PieceColor.BLACK,"black_knight.png"));

        // Добавление слонов для обеих сторон
        addPiece(new Bishop(PieceColor.WHITE,"white_bishop.png"), 0, 2);
        squares[0][2].setUserData(new Bishop(PieceColor.WHITE,"white_bishop.png"));
        addPiece(new Bishop(PieceColor.WHITE,"white_bishop.png"), 0, 5);
        squares[0][5].setUserData(new Bishop(PieceColor.WHITE,"white_bishop.png"));
        addPiece(new Bishop(PieceColor.BLACK,"black_bishop.png"), 7, 2);
        squares[7][2].setUserData(new Bishop(PieceColor.BLACK,"black_bishop.png"));
        addPiece(new Bishop(PieceColor.BLACK,"black_bishop.png"), 7, 5);
        squares[7][5].setUserData(new Bishop(PieceColor.BLACK,"black_bishop.png"));

        // Добавление ферзей для обеих сторон
        addPiece(new Queen(PieceColor.WHITE,"white_queen.png"), 0, 3);
        squares[0][3].setUserData(new Queen(PieceColor.WHITE,"white_queen.png"));
        addPiece(new Queen(PieceColor.BLACK,"black_queen.png"), 7, 3);
        squares[7][3].setUserData(new Queen(PieceColor.BLACK,"black_queen.png"));

        // Добавление королей для обеих сторон
        addPiece(new King(PieceColor.WHITE,"white_king.png"), 0, 4);
        squares[0][4].setUserData(new King(PieceColor.WHITE,"white_king.png"));
        addPiece(new King(PieceColor.BLACK,"black_king.png"), 7, 4);
        squares[7][4].setUserData(new King(PieceColor.BLACK,"black_king.png"));
    }
}
