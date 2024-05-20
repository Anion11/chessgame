package Player;
import Piece.Piece;
import Piece.PieceColor;
import Move.Move;
import Move.MoveError;
import Board.Board;
import java.util.Objects;
import java.util.Scanner;

public class Player implements PlayerInterface {
    private final String name;
    private final PieceColor pieceColor;
    private final Scanner scanner;
    public String getName() {
        return name;
    }

    public Player(String name, PieceColor pieceColor) {
        this.name = name;
        this.pieceColor = pieceColor;
        this.scanner = new Scanner(System.in);
    }

    public Move getMove(Board board) {
        while (true) {
            System.out.print(name + ", введите ваш ход: ");
            String input = scanner.nextLine().toLowerCase();;
            if (isValidInput(input)) {
                Move move = parseInputToMove(input);
                if (isValidMove(board, move).getNotError()) return move;
                else System.out.println(isValidMove(board, move).getErrorText());
            } else System.out.println("Некорректный ввод. Введите координаты начальной и конечной клеток (например, a2 a4).");
        }
    }
    private MoveError isValidMove(Board board, Move move) {
        MoveError error = new MoveError();
        if (move.getEndGame()) {
            error.setNotError(true);
            return error;
        };
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();
        Piece piece = board.getPiece(startX, startY);
        if (piece == null) {
            error.setNotError(false);
            error.setErrorText("Здесь нет фигуры!");
            return error;
        }
        if (pieceColor != board.getPiece(startX, startY).getColor()) {
            error.setNotError(false);
            error.setErrorText("Это не ваша фигура!");
            return error;
        };
        error.setNotError(piece.isValidMove(board, startX, startY, endX, endY));
        return error;
    }
    private boolean isValidInput(String input) {
        if (Objects.equals(input, "end")) return true;;
        return input.matches("[a-h][1-8]\\s[a-h][1-8]");
    }
    private Move parseInputToMove(String input) {
        if (Objects.equals(input, "end")) return new Move(-1, -1, -1, -1);;
        String[] coordinates = input.split("\\s");
        String startCoordinate = coordinates[0];
        String endCoordinate = coordinates[1];

        int startX = Character.getNumericValue(startCoordinate.charAt(1)) - 1;
        int startY = startCoordinate.charAt(0) - 'a';
        int endX = Character.getNumericValue(endCoordinate.charAt(1)) - 1;
        int endY = endCoordinate.charAt(0) - 'a';
        return new Move(startX, startY, endX, endY);
    }
}