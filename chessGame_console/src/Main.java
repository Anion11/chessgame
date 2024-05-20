import ChessGame.ChessGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String gameType, player1, player2;
        do {
            System.out.print("Введите тип шахмат (classic или fisher): ");
            gameType = sc.nextLine().toLowerCase();
            if (!gameType.equals("classic") && !gameType.equals("fisher"))
                System.out.println("Неправильно! Введите одно из значений: {classic, fisher} ");
        } while (!gameType.equals("classic") && !gameType.equals("fisher"));
        System.out.print("Введите игрока белых фигур: ");
        player1 = sc.nextLine().toLowerCase();;
        do {
            System.out.print("Введите игрока черных фигур: ");
            player2 = sc.nextLine().toLowerCase();;
            if (player2.equals(player1))
                System.out.println("Введите разные никнеймы!");
        } while (player2.equals(player1));
        ChessGame game = new ChessGame(gameType, player1, player2);
        game.startGame();
    }
}