package code;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class GenericSearch {

	
	public abstract String bfs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited);
	
	public abstract String dfs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited);
	
	public abstract String iterativeDFS(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited);
	
	public abstract String ucs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited);
	
	public abstract String greedySearch(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited, int heuristicNumbers);
	
	public abstract String aStar(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited, int heuristicNumbers);


}
