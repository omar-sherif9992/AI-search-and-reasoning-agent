/*
package code;

import java.util.*;

public class UniformCost {
	
	
	public static String ucs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<State> visited)
	{
		
		Comparator<Node> customComparator = (a, b) -> Double.compare(a.getState().getMoneySpent(), b.getState().getMoneySpent());
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(customComparator);
		
		pq.add(tree.root);
		
		Node goal = null;
		int nodesExpanded = 0;
		
		
		
		while(pq.size() > 0)
		{
			Node currNode = pq.poll();
			
			if(LLAPSearch.isGoal(currNode))
			{
				
				goal = currNode;
				break;
			}
			
			nodesExpanded++;
			
			//try the different operators on this node
			for(Operator operator : operators)
			{
				State newState = operator.apply(currNode.getState());
				if(newState == null || visited.contains(newState))
				{
					//Here i can't branch with this operator
					continue;
				}
				//Here i need to make a new node and push it to the queue
				Node child = new Node(newState, currNode.getLevel()+1, currNode, operator);
				pq.add(child);
				visited.add(newState);
			}
		}
		if(goal == null)
		{
			return "NOSOLUTION";
		}
		
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