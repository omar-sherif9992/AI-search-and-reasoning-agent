/*
package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

	
    public static String aStar(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visitedStates, int heuristicNumber) {
        Comparator<Node> customComparator1 = (a, b) -> Double.compare(GreedySearch.heuristic1(a.getState(), operators) + a.getState().getMoneySpent(),GreedySearch.heuristic1(b.getState(), operators) + b.getState().getMoneySpent());
        Comparator<Node> customComparator2 = (a, b) -> Double.compare(GreedySearch.heuristic2(a.getState(), operators) + a.getState().getMoneySpent(),GreedySearch.heuristic2(b.getState(), operators) + b.getState().getMoneySpent());
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(heuristicNumber == 1 ? customComparator1:customComparator2);
        
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
				if(newState == null || visitedStates.contains(newState.toString()))
				{
					//Here i can't branch with this operator
					continue;
				}
				//Here i need to make a new node and push it to the queue
				Node child = new Node(newState, currNode.getLevel()+1, currNode, operator);
				pq.add(child);
				visitedStates.add(newState.toString());
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