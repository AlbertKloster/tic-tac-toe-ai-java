package tictactoe.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private final String state;
    private int score;
    private final List<Node> nextNodes;

    public Node(String state, int score) {
        this.state = state;
        this.score = score;
        this.nextNodes = new ArrayList<>();
    }

    public String getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public List<Node> getNextNodes() {
        return nextNodes;
    }

    public void addNextNode(Node node) {
        this.nextNodes.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node that = (Node) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
