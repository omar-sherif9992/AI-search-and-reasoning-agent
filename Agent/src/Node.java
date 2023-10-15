import java.util.ArrayList;

public class Node {
    private Node parentNode;
    private ArrayList<Node> childrenNode;
    private State state;
    private final int level;
    private Operator appliedOperator;

    public Node(State state, int level, Node parentNode, Operator appliedOperator) {
        this.state = state;
        this.level = level;
        this.childrenNode = new ArrayList<Node>();
        this.parentNode = parentNode;
        this.appliedOperator = appliedOperator;
    }
}
