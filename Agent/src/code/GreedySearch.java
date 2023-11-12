package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySearch {
	
	public static double heuristic1(State state, ArrayList<Operator> operators)
	{
		double mx = 0;
		double mn = Integer.MAX_VALUE;
		for(Operator operator : operators)
		{
			if(operator.name == "BUILD1") {
				mx = Math.max(mx, ((BuildAction)operator).addProsperity);
				mn = Math.min(mn, ((BuildAction)operator).cost);
			}
			
		}
		return ( (100 - state.getLevelOfProsperity() ) / mx)  * mn;
		
	}
	public static double heuristic2(State state, ArrayList<Operator> operators)
	{
		return ( (100 - state.getLevelOfProsperity() ) / 100)  * state.getMoneySpent();
	}
    
    public static String greedySearch(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visitedStates, int heuristicNumber) {
        Comparator<Node> customComparator1 = (a, b) -> Double.compare(heuristic1(a.getState(), operators), heuristic1(b.getState(), operators));
        Comparator<Node> customComparator2 = (a, b) -> Double.compare(heuristic2(a.getState(), operators), heuristic2(b.getState(), operators));
        
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>((heuristicNumber)== 1 ? customComparator1:customComparator2);
        
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
