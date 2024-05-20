package com.example.chessgame;

import com.example.chessgame.Board.ClassicBoard;
import com.example.chessgame.ChessLogic.ChessLogic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Основноый класс для запуска игры.
 * Данный класс содержит приветственное окно для выбора имени и отображение игровой сцены на экране пользователя.
 */
public class ChessGame extends Application {
    // Размер игровой клетки
    int size = 100;
    // Создание экземпляра класса доски
    ClassicBoard board = new ClassicBoard(size);
    Label labelPlayer1 = new Label("Введите имя белых фигур:");
    Label labelPlayer2 = new Label("Введите имя черных фигур:");
    TextField namePlayer1 = new TextField();
    TextField namePlayer2 = new TextField();
    Button submitButton = new Button("Начать игру");
    /**
     * Метод для проверки введенного имени.
     */
    private void handleSubmit(Stage primaryStage) {
        // Проверка на валидность вводимого имени
        if (namePlayer1.getText().trim().isEmpty() || namePlayer2.getText().trim().isEmpty()) {
            return;
        }
        primaryStage.close();
        // Инициализация ChessLogic
        new ChessLogic(board, namePlayer1.getText(),  namePlayer2.getText(), primaryStage);
        // Инициализация игрвоой доски
        GridPane gridPane = board.createBoard();
        // Добавление игрвоой доски на сцену
        Scene scene = new Scene(gridPane, 8 * size, 8 * size);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess Game");
        // Установка обработчика события клик на кнопку
        submitButton.setOnAction(e -> {
            handleSubmit(primaryStage);
        });
        // Создание первоначального окна
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(labelPlayer1, namePlayer1);
        layout.getChildren().addAll(labelPlayer2, namePlayer2, submitButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
