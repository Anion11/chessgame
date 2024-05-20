package ChessGame;

import Board.ClassicBoard;
import Board.FisherBoard;
import Board.Board;
import Piece.*;
import Player.Player;
import Move.Move;

import java.util.Objects;

public class ChessGame {
    private Board chessBoard;
    private final Player[] players = new Player[2];
    private Player currentPlayer;
    public ChessGame(String game, String playerWhite, String playerBlack) {
        if (Objects.equals(game, "classic")) initClassicGame();
        else if (Objects.equals(game, "fisher")) initFisherGame();
        players[0] = new Player(playerWhite, PieceColor.WHITE);
        players[1] = new Player(playerBlack, PieceColor.BLACK);
        currentPlayer = players[0];
    }
    private void initClassicGame() {
        chessBoard = new ClassicBoard();
    }
    private void initFisherGame() {
        chessBoard = new FisherBoard();
    }
    public void startGame() {
        while (!isGameEnd()) {
            printChessBoard();
            Move move = currentPlayer.getMove(chessBoard);
            if (move.getEndGame()) {
                break;
            }
            chessBoard.makeMove(move);
            switchPlayers();
        }
        switchPlayers();
        if (true) displayGameResult(currentPlayer);
        else displayGameResult(null);
    }
    public void printChessBoard() {
        System.out.printf("   %-3s %-3s %-3s %-3s %-3s %-3s %-3s %-3s", "A", "B", "C", "D", "E", "F", "G", "H");
        System.out.println();
        for (int row = 7; row >= 0; row--) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < 8; col++) {
                if (chessBoard.getPiece(row, col) != null) System.out.print("\u001B[32m");
                else System.out.print("\u001B[0m");
                System.out.printf(" %-3s", chessBoard.getPiece(row, col) != null ? chessBoard.getPiece(row, col).getChar() : (row + col) % 2 == 0 ? "■" : "□");
            }
            System.out.print("\u001B[0m");
            System.out.print(" " + (row + 1));
            System.out.println();
        }
        System.out.printf("   %-3s %-3s %-3s %-3s %-3s %-3s %-3s %-3s", "A", "B", "C", "D", "E", "F", "G", "H");
        System.out.println();
    }
    private void displayGameResult(Player winner) {
        if (winner != null) System.out.println("Поздравляем, " + winner.getName() + " выиграл!");
        else System.out.println("Ничья");
    }
    private boolean isGameEnd() {
        int x = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (chessBoard.getPiece(i, j) != null) {
                    if (Objects.equals(chessBoard.getPiece(i, j).getChar(), "K") || Objects.equals(chessBoard.getPiece(i, j).getChar(), "K^")) x++;
                }
            }
        }
        return x < 2;
    }
    private void switchPlayers() {
        if (currentPlayer == players[0]) currentPlayer = players[1];
        else currentPlayer = players[0];
    }
}
