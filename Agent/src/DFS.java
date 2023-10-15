
import java.util.*;

public class DFS {
	
	public static String dfs(Tree tree, boolean visualize, ArrayList<Operator> operators)
	{
		Stack<Node> stack = new Stack<Node>();
		stack.push(tree.root);
		
		int nodesExpanded = 0;
		
		Node goal = null;

		while(stack.size() > 0)
		{
			Node currNode = stack.pop();
			
			if(LLAPSearch.isGoal(currNode))
			{
				goal = currNode;
				break;
			}
			
			nodesExpanded++;
			
			//start expanding this node
			for(Operator operator : operators)
			{
				State newState = operator.apply(currNode.getState());
				if(newState == null)
				{
					//Here i can't branch with this operator
					continue;
				}
				//Here i need to make a new node and push it to the stack
				Node child = new Node(newState, currNode.getLevel()+1, currNode, operator);
				currNode.addChild(child);
				stack.push(child);
			}
		}
		
		if(goal == null)
			return "NOSOLUTION";
		
		String plan = LLAPSearch.findPlan(goal);
		double monetaryCost = goal.getState().getMoneySpent();
		
		System.out.println(LLAPSearch.pathToGoal(goal));
		return plan + ";" + monetaryCost + ";" + nodesExpanded;
		
	}

}
