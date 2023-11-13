/*package code;

import java.util.*;
 

public class BFS {
	
	
	public static String bfs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited)
	{
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(tree.root);
		
		Node goal = null;
		int nodesExpanded = 0;
		
		
		
		while(queue.size() > 0)
		{
			Node currNode = queue.poll();
			
			if(LLAPSearch.isGoal(currNode))
			{
				
				goal = currNode;
				break;
			}
			
			nodesExpanded++;
			
			//try the different operators on this node
			for(Operator operator : operators)
			{
				State tst = currNode.getState();
				State newState = operator.apply(tst);
				if( newState == null || visited.contains(newState.toString()) )
				{
					continue;
				}

				//Here i need to make a new node and push it to the queue
				Node child = new Node(newState, currNode.getLevel()+1, currNode, operator);
				queue.add(child);
				visited.add(newState.toString());
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
//		System.out.println(plan);
		return plan + ";" + monetaryCost + ";" + nodesExpanded;
		
		
	}
	
	
	
	

}
*/