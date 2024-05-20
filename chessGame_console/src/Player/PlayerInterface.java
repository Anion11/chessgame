package Player;
import Move.Move;
import Board.Board;

public interface PlayerInterface {
    String getName();
    Move getMove(Board board);
}
