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
    
    public String toString()
    {
    	return "Node{" +
                "state=" + this.state.toString() +
                ", applied opearator=" + (appliedOperator == null? "": appliedOperator.toString()) +
                ", level=" + level +
                '}';
    }
    
    public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public ArrayList<Node> getChildrenNode() {
		return childrenNode;
	}

	public void setChildrenNode(ArrayList<Node> childrenNode) {
		this.childrenNode = childrenNode;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Operator getAppliedOperator() {
		return appliedOperator;
	}

	public void setAppliedOperator(Operator appliedOperator) {
		this.appliedOperator = appliedOperator;
	}

	public int getLevel() {
		return level;
	}
	
	public void addChild(Node child) {
		this.childrenNode.add(child);
	}

    
  

    
 
}
