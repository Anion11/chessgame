package com.example.chessgame.ChessLogic;

import com.example.chessgame.Board.Board;
import com.example.chessgame.ChessGame;
import com.example.chessgame.Move.Move;
import com.example.chessgame.Piece.King;
import com.example.chessgame.Piece.Piece;
import com.example.chessgame.Piece.PieceColor;
import com.example.chessgame.Player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * Класс для логики игры в шахматы.
 * Содержит в себе методы для смены игрока, проверки на окончание игры, обработчики события при нажатии на фигуру/клетку.
 */
public class ChessLogic {
    private static final Player[] players = new Player[2]; // Массив игроков
    private static Player currentPlayer; // Текущий игрок
    private static Board chessBoard = null; // Переменная для игровой доски
    private static final int[] startCoord = new int[2]; // Массив для хранения начальных координат фигуры при клике на неё или на квадрат на доске.
    private static Pane piecePane; // Панель, на которой располагается фигура, по которой был клик.
    private static Pane piecePaneNew; //  Дополнительная панель, используемая при перемещении фигуры.
    private static boolean isFigure = false; // Флаг для отслеживания клика по фигуре.
    private static boolean isKillFigure = false; // Дополнительный флаг, используемый в обработке кликов фигур.
    private static Stage ps; // Ссылка на объект окна (Stage) для отображения сообщений и управления игрой.
    /**
     * Конструктор для инициализации игроков, доски и текущего игрока.
     */
    public ChessLogic(Board board, String playerWhite, String playerBlack, Stage ps) {
        // Создание двух игроков
        players[0] = new Player(playerWhite, PieceColor.WHITE);
        players[1] = new Player(playerBlack, PieceColor.BLACK);
        currentPlayer = players[0]; // Установка текущего игрока
        chessBoard = board; // Установка игровой доски
        this.ps = ps;
        ps.setTitle("Ход игрока: " + currentPlayer.getName()); // Установка заголовка окна
    }
    /**
     * ОБработчик события при нажатии на клетку доски
     */
    public static void handleSquareClick(Rectangle[][] board, Rectangle square) {
        isFigure = false;
        // Создаем экземпляр хода
        Move move = new Move(startCoord[0], startCoord[1], GridPane.getRowIndex(square), GridPane.getColumnIndex(square));
        // Берем фигуру по начальным координатам
        Piece p = chessBoard.getPiece(startCoord[0], startCoord[1]);
        // Если ход недопустим до окрашиваем поля в стандартные цвета и прекращаем дальнейшее выполнее метода
        if (!p.isValidMove(chessBoard, move)) {
            fillBoard(board);
            return;
        }
        // Забираем метаданные которые находятся на этой клетке
        Object data = chessBoard.getRect(startCoord[0], startCoord[1]).getUserData();
        // Удаляем фигуру на начальных координатах
        chessBoard.removePiece(startCoord[0], startCoord[1], piecePane);
        // Если это атака на другую фигуру, то удаляем и её
        if (isKillFigure) {
            chessBoard.removePiece(GridPane.getRowIndex(square), GridPane.getColumnIndex(square), piecePaneNew);
        }
        // ДОбавляем фигуру на новые координаты
        chessBoard.addPiece(p, GridPane.getRowIndex(square), GridPane.getColumnIndex(square));
        chessBoard.getSquares()[GridPane.getRowIndex(square)][GridPane.getColumnIndex(square)].setUserData(data);
        // Если конец игры то отображаем сцену победителя
        if (isGameEnd()) {
            setWinnerScene();
            return;
        }
        // Смена игроков
        switchPlayers();
        // окрашиваем поля в стандартные цвета
        fillBoard(board);
    }
    /**
     * ОБработчик события при нажатии на фигуру
     */
    public static void handlePieceClick(Pane piecePane, Piece piece) {
        // Если до этого мы уже нажимали на фигуру, то предполагаем что это атака на вражескую фигуру
        if (isFigure) {
            isKillFigure = true;
            piecePaneNew = piecePane;
            // Принудительно вызывем нажатие на клетку доски
            handleSquareClick(chessBoard.getSquares(), chessBoard.getSquares()[GridPane.getRowIndex(piecePane)][GridPane.getColumnIndex(piecePane)]);
            isKillFigure = false;
            return;
        }
        isFigure = true;
        // окрашиваем поля в стандартные цвета
        fillBoard(chessBoard.getSquares());
        // Если это не наша фигура то прекращаем выполнение метода
        if (piece.getColor() != currentPlayer.getColor()) return;
        // Проверяем допустимые клетки для хода
        piece.getValidMove(chessBoard, GridPane.getRowIndex(piecePane), GridPane.getColumnIndex(piecePane));
        // Устанавливаем начальные координаты
        startCoord[0] = GridPane.getRowIndex(piecePane);
        startCoord[1] = GridPane.getColumnIndex(piecePane);
        ChessLogic.piecePane = piecePane;
    }
    /**
     * Метод для окраски поля
     */
    private static void fillBoard(Rectangle[][] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((col + row) % 2 != 0) board[row][col].setFill(Color.rgb(68, 45, 37));
                else board[row][col].setFill(Color.rgb( 177,149,118));
                board[row][col].setOpacity(1);
            }
        }
    }
    /**
     * Метод для показа сцены победителя
     */
    private static void setWinnerScene(){
        ps.close();
        Label winnerLabel = new Label("Победитель " + currentPlayer.getName() + "!");
        Stage winnerStage = new Stage();
        VBox winnerRoot = new VBox();
        winnerRoot.setStyle("-fx-background-color: #f9fbe7; -fx-padding: 20px;");
        winnerLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #4caf50;");
        Button resetButton = new Button("Начать игру заново");
        Button exitButton = new Button("Выйти из игры");
        Pane padding = new Pane();
        winnerRoot.getChildren().add(winnerLabel);
        exitButton.setOnAction(e -> {
            winnerStage.close();
        });
        resetButton.setOnAction(e -> {
            winnerStage.close();
            ChessGame game = new ChessGame();
            game.start(ps);
        });
        padding.setPadding(new Insets(10));
        winnerRoot.getChildren().add(resetButton);
        winnerRoot.getChildren().add(padding);
        winnerRoot.getChildren().add(exitButton);
        winnerRoot.setAlignment(Pos.CENTER);
        Scene winnerScene = new Scene(winnerRoot, 400, 200);
        winnerStage.setScene(winnerScene);
        winnerStage.show();
    }
    /**
     * Метод для проверки окончания игры
     */
    private static boolean isGameEnd() {
        // Проверка наличия двух королей на доске
        int x = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (chessBoard.getPiece(row, col) != null) {
                    if (chessBoard.getPiece(row, col).getClass() == King.class) x++;
                }
            }
        }
        return x < 2;
    }
    /**
     * Метод для смены игроков
     */
    private static void switchPlayers() {
        // Смена текущего игрока
        if (currentPlayer == players[0]) currentPlayer = players[1];
        else currentPlayer = players[0];
        ps.setTitle("Ход игрока: " + currentPlayer.getName());
    }
}
