package vocabcard.com.puzzleone;

/**
 * Created by shivamawasthi on 12/09/15.
 */
public class Model {

    private String[][] values;
    private int[] finalState;

    public Model() {
        values = new String[2][9];
        finalState = new int[9];
        values[0][0] = "#D4E157";
        values[0][1] = "#EC407A";
        values[0][2] = "#7E57C2";
        values[0][3] = "#42A5F5";
        values[0][4] = "#EF5350";
        values[0][5] = "#FFA726";
        values[0][6] = "#FFEE58";
        values[0][7] = "#8D6E63";
        values[0][8] = "#26A69A";
        values[1][1] = "#C2185B";
        values[1][2] = "#512DA8";
        values[1][3] = "#1976D2";
        values[1][4] = "#D32F2F";
        values[1][5] = "#F57C00";
        values[1][6] = "#FBC02D";
        values[1][7] = "#5D4037";
        values[1][8] = "#00796B";
        values[1][0] = "#AFB42B";

        for (int i = 0; i < 9; i++) {
            finalState[i] = i;
        }

    }

    public String background(int v) {
        return values[0][v];
    }

    public String text(int v) {
        return values[1][v];
    }

    public int[] getFinalState() {
        return finalState;
    }

}
