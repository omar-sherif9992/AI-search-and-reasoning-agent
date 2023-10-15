
import java.util.*;

public class IDFS {
	
	static boolean flag = false;
	
	public static String iterativeDFS(Tree tree, boolean visualize, ArrayList<Operator> operators)
	{
		
		int depth = 0;
		
		while(true)
		{
			flag = false;
			String currSolution = dfsSpecial(tree, visualize, operators, depth);
			
			if(currSolution != "NOSOLUTION")
				return currSolution;
			if(flag == false)
			{
				break;
			}
			depth++;
		}
		
		return "NOSOLUTION";
		
	}
	
	
	public static String dfsSpecial(Tree tree, boolean visualize, ArrayList<Operator> operators, int mxLevel)
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
				if(currNode.getLevel() + 1 > mxLevel)
				{
					flag = true;
					continue;
				}
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
