package tictactoe.tree;

import tictactoe.utils.Analyser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeHandler {

    private final int MAX_SCORE = Integer.MAX_VALUE;
    private final int MIN_SCORE = Integer.MIN_VALUE;
    private final int DEPTH_SCORE = 1;
    private final int X_WIN_SCORE = -10;
    private final int O_WIN_SCORE = 10;
    private final int DRAW_SCORE = 0;
    private final Tree tree;
    private final Analyser analyser = new Analyser();

    public TreeHandler() {
        this.tree = new Tree(createRoot(new Node("_".repeat(9), MAX_SCORE)));
    }

    public Node getNodeByState(String state) {
        Node root = tree.getRoot();
        if (root.getState().equals(state)) return root;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            if (currentNode.getState().equals(state)) return currentNode;
            currentNode.getNextNodes().forEach(stack::push);
        }
        return null;
    }

    private Node createRoot(Node node) {

        if (analyser.isO(node.getState()) || analyser.isX(node.getState()) || analyser.isDraw(node.getState())) {
            node.setScore(getScore(node));
            return node;
        }

        for (Node currentNode : getNextNodes(node)) {
            Node nextNode = createRoot(currentNode);
            if (analyser.nextPlayer(currentNode.getState()).equals("O"))
                node.setScore(Math.min(node.getScore(), nextNode.getScore() - DEPTH_SCORE));
            else
                node.setScore(Math.max(node.getScore(), nextNode.getScore() + DEPTH_SCORE));
            node.addNextNode(nextNode);
        }
        return node;
    }

    private List<Node> getNextNodes(Node node) {
        String s = analyser.nextPlayer(node.getState());
        List<Node> nextNodes = new ArrayList<>();
        String state = node.getState();
        if (analyser.isGameOver(state)) return nextNodes;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '_') {
                String nextState = state.substring(0, i) + s + state.substring(i + 1);
                if (s.equals("X"))
                    nextNodes.add(new Node(nextState, MIN_SCORE));
                else
                    nextNodes.add(new Node(nextState, MAX_SCORE));
            }
        }
        return nextNodes;
    }

    private int getScore(Node node) {
        if (analyser.isDraw(node.getState())) return DRAW_SCORE;
        if (analyser.isX(node.getState())) return X_WIN_SCORE;
        if (analyser.isO(node.getState())) return O_WIN_SCORE;
        String nextPlayer = analyser.nextPlayer(node.getState());
        return nextPlayer.equals("X") ? MIN_SCORE : MAX_SCORE;
    }

}
