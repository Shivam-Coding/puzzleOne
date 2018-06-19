package vocabcard.com.puzzleone;

import java.util.LinkedList;

/**
 * Created by shivamawasthi on 16/10/15.
 */
public class Moves {
    public static LinkedList getUserMoves() {
        return userMoves;
    }

    public static void setUserMoves(LinkedList userMoves) {
        Moves.userMoves = userMoves;
    }

    private static LinkedList userMoves;

    public static LinkedList getMoves() {
        return moves;
    }

    public static void setMoves(LinkedList moves) {
        Moves.moves = moves;
    }

    private static LinkedList moves;
}
