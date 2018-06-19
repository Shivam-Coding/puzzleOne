package vocabcard.com.puzzleone;

import java.util.ArrayList;

/**
 * Created by shivamawasthi on 16/10/15.
 */
public class Node {

    private Node parent;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    private ArrayList<Node> children;
    private State state;


}