/*
package code;

import java.util.*;

public class IDFS {
	
	static boolean flag = false;
	
	public static String iterativeDFS(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited)
	{
		
		int depth = 0;
		
		while(true)
		{
			visited = new HashSet<String>();
			flag = false;
			
			String currSolution = dfsSpecial(tree, visualize, operators, depth, visited);
	
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
	
	
	public static String dfsSpecial(Tree tree, boolean visualize, ArrayList<Operator> operators, int mxLevel, HashSet<String> visited)
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
				if(newState == null || visited.contains(newState.toString()))
				{
					//Here i can't branch with this operator
					continue;
				}
				
				//Here i need to make a new node and push it to the stack
				Node child = new Node(newState, currNode.getLevel()+1, currNode, operator);
				stack.push(child);
				visited.add(newState.toString());
			}
		}
		
		if(goal == null)
			return "NOSOLUTION";
		
		String plan = LLAPSearch.findPlan(goal);
		int monetaryCost = (int)goal.getState().getMoneySpent();
		
		if(visualize)
		{
			System.out.println(LLAPSearch.pathToGoal(goal));
		}
		return plan + ";" + monetaryCost + ";" + nodesExpanded;
		
	}
	

}
*/