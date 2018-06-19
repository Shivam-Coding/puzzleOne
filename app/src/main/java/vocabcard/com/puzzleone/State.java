package vocabcard.com.puzzleone;

/**
 * Created by shivamawasthi on 16/10/15.
 */
public class State {
    private int state[][];
    private int manhattanDistance;
    private int evaluationFunction;
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public int getManhattanDistance() {
        return manhattanDistance;
    }

    public void setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
    }

    public int getEvaluationFunction() {
        return evaluationFunction;
    }

    public void setEvaluationFunction(int evaluationFunction) {
        this.evaluationFunction = evaluationFunction;
    }

}
