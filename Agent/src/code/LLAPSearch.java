package code;
import java.util.*;

import enums.ResourceEnum;

public class LLAPSearch extends GenericSearch {
	
	public static LLAPSearch solver = new LLAPSearch();
   
    public static String solve(String initialState, String strategy, Boolean visualize) {
    	 double budget = 100000;
    	 ArrayList<Operator> operators = new ArrayList<Operator>();
    	 HashSet<String> visitedStates = new HashSet<String>();

        String[] splitInitialState = initialState.split(";");
        String[] splitResource = splitInitialState[1].split(",");
        String[] splitResourcePrice = splitInitialState[2].split(",");
        String[] splitFood = splitInitialState[3].split(",");
        String[] splitMaterial = splitInitialState[4].split(",");
        String[] splitEnergy = splitInitialState[5].split(",");
        String[] splitBuild1 = splitInitialState[6].split(",");
        String[] splitBuild2 = splitInitialState[7].split(",");
        
        State initState = getInitState(splitInitialState, splitResource, splitResourcePrice,budget);
      
        Node root = new Node(initState, 0, null, null);
        Tree tree = new Tree(root);
        initializeOperators(splitFood, splitMaterial, splitEnergy, splitBuild1, splitBuild2, operators);
        //System.out.println(initState);
        switch (strategy) {
            case "BF":
            	return solver.bfs(tree, visualize, operators, visitedStates);
             
            case "DF":
                return solver.dfs(tree, visualize, operators, visitedStates);
                
            case "UC":
                return solver.ucs(tree, visualize, operators, visitedStates);
                
            case "ID":
                return solver.iterativeDFS(tree, visualize, operators, visitedStates);
              
            case "GR1":
                return solver.greedySearch(tree, visualize, operators, visitedStates, 1);
                
            case "GR2":
                return solver.greedySearch(tree, visualize, operators, visitedStates, 2);
               
            case "AS1":
                return solver.aStar(tree, visualize, operators, visitedStates, 1);
                
            case "AS2":
            	return solver.aStar(tree, visualize, operators, visitedStates, 2);
               
            default:
            	return "NONE";
                
        }
    }

    private static State getInitState(String[] splitInitialState, String[] splitResource, String[] splitResourcePrice, double budget) {
        double foodPrice = Double.parseDouble(splitResourcePrice[0]);
        double initialFoodAmount = Double.parseDouble(splitResource[0]);

        double energyPrice = Double.parseDouble(splitResourcePrice[2]);
        double initialEnergyAmount = Double.parseDouble(splitResource[2]);

        double materialPrice = Double.parseDouble(splitResourcePrice[1]);
        double initialMaterialAmount = Double.parseDouble(splitResource[1]);

        Resource food = new Resource(foodPrice, initialFoodAmount,
                ResourceEnum.FOOD);
        Resource energy = new Resource(energyPrice, initialEnergyAmount,
                ResourceEnum.ENERGY);
        Resource material = new Resource(materialPrice, initialMaterialAmount,
                ResourceEnum.MATERIAL);
                
        return new State(0,
                food,
                energy,
                material,
                Integer.parseInt(splitInitialState[0]), 0, null, budget);
    }

    private static void initializeOperators(String[] splitFood, String[] splitMaterial, String[] splitEnergy, String[] splitBuild1,
            String[] splitBuild2, ArrayList<Operator> operators) {
    	
    	double prosperityBuild2 = Double.parseDouble(splitBuild2[4]);
        double foodUseBuild2 = Double.parseDouble(splitBuild2[1]);
        double materialUseBuild2 = Double.parseDouble(splitBuild2[2]);
        double energyUseBuild2 = Double.parseDouble(splitBuild2[3]);
        double priceBuild2 = Double.parseDouble(splitBuild2[0]);
        operators.add(new BuildAction("BUILD2", prosperityBuild2, foodUseBuild2,
                materialUseBuild2, energyUseBuild2, priceBuild2));
    	
    	double prosperityBuild1 = Double.parseDouble(splitBuild1[4]);
        double foodUseBuild1 = Double.parseDouble(splitBuild1[1]);
        double materialUseBuild1 = Double.parseDouble(splitBuild1[2]);
        double energyUseBuild1 = Double.parseDouble(splitBuild1[3]);
        double priceBuild1 = Double.parseDouble(splitBuild1[0]);
        operators.add(new BuildAction("BUILD1", prosperityBuild1, foodUseBuild1,
                materialUseBuild1, energyUseBuild1, priceBuild1));

    	
    	int amountRequestFood = Integer.parseInt(splitFood[0]);
        int delayRequestFood = Integer.parseInt(splitFood[1]);
        operators.add(new RequestAction(ResourceEnum.FOOD, amountRequestFood, delayRequestFood));
        

        int amountRequestMaterial = Integer.parseInt(splitMaterial[0]);
        int delayRequestMaterial = Integer.parseInt(splitMaterial[1]);
        operators.add(new RequestAction(ResourceEnum.MATERIAL, amountRequestMaterial, delayRequestMaterial));

        int amountRequestEnergy = Integer.parseInt(splitEnergy[0]);
        int delayRequestEnergy = Integer.parseInt(splitEnergy[1]);
        operators.add(new RequestAction(ResourceEnum.ENERGY, amountRequestEnergy, delayRequestEnergy));
        
        operators.add(new WaitAction("WAIT"));
    	

    }
    
    public static String pathToGoal(Node goal)
	{
		String path = "";
		while(goal != null)
		{
		    path =  goal.toString() + "\n ============================ \n" + path;
			goal = goal.getParentNode();
		}
		
		if(path == "")
			return path;
		
		return path.substring(0, path.length());
	}
	
	public static String findPlan(Node goal)
	{
		
		String plan = "";
		while(goal.getParentNode() != null)
		{
		    plan =  goal.getAppliedOperator().toString() + "," + plan;
			goal = goal.getParentNode();
		}
		
		if(plan == "")
			return plan;
		
		return plan.substring(0, plan.length()-1);
		
	}
    
    
    public static boolean isGoal(Node node)
    {
    	return node.getState().getLevelOfProsperity() >= 100;
    }

	@Override
	public String bfs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited) {
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
	
	@Override
	public String dfs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited)
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
	
	@Override
	public String iterativeDFS(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited)
	{
		int depth = 0;
		while(true)
		{
			visited = new HashSet<String>();
			
			String currSolution = dfsSpecial(tree, visualize, operators, depth, visited);
	
			if(currSolution != "NOSOLUTION" && currSolution != "I NEED MORE LEVELS" )
				return currSolution;
			if(currSolution != "I NEED MORE LEVELS")
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
		boolean flag = false;
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
		if(flag == true && goal == null)
			return "I NEED MORE LEVELS";
		
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
	
	@Override
	public String ucs(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visited)
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
				if(newState == null || visited.contains(newState.toString()))
				{
					//Here i can't branch with this operator
					continue;
				}
				//Here i need to make a new node and push it to the queue
				Node child = new Node(newState, currNode.getLevel()+1, currNode, operator);
				pq.add(child);
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
		return plan + ";" + monetaryCost + ";" + nodesExpanded;
		
	}
	
	@Override
	public String greedySearch(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visitedStates, int heuristicNumber) {
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
	
	
	public static double heuristic1(State state, ArrayList<Operator> operators)
	{
		double mx = 0;
		double mn = Integer.MAX_VALUE;
		for(Operator operator : operators)
		{
			if(operator.name.equals("BUILD1") || operator.name.equals("BUILD2")) {
				mx = Math.max(mx, ((BuildAction)operator).addProsperity);
				double buildCost = ((BuildAction)operator).cost + ((BuildAction)operator).getUnitsOfFood()*state.getFood().getCost()
						+  ((BuildAction)operator).getUnitsOfEnergy()*state.getEnergy().getCost()
						+  ((BuildAction)operator).getUnitsOfMaterial()*state.getMaterial().getCost();
				mn = Math.min(mn, buildCost);
			}
			
		}
		return ( (100 - state.getLevelOfProsperity() ) / mx)  * mn;
		
	}
	
	public static double heuristic2(State state, ArrayList<Operator> operators)
	{
		double mxPros = 0;
		double buildNeededFood = Integer.MAX_VALUE;
		for(Operator operator : operators)
		{
			if(operator.name.equals("BUILD1") || operator.name.equals("BUILD2")) {
				//needed food 
				mxPros = Math.max(((BuildAction)operator).addProsperity, mxPros);
				buildNeededFood = Math.min(((BuildAction)operator).getUnitsOfFood(), buildNeededFood);
			}
		}
		int remBuilds = (int)( (100 - state.getLevelOfProsperity()) / mxPros) + (int)( ((int)((100 - state.getLevelOfProsperity()) % mxPros) != 0)? 1:0);
		int remFood = Math.max((int)(remBuilds*buildNeededFood - state.getFood().getAmount()), 0);
		
		//Now how many food requests needed to get that food?
		Operator op = null;
		for(Operator i : operators)
		{
			if(i.name.equals("REQUEST FOOD"))
			{
				op = i;
				break;
			}
		}
		int foodRequestActionsNeeded = remFood / ((RequestAction)op).getAddedAmount();
		double costOfReqFood = (state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost()) * foodRequestActionsNeeded;
		return costOfReqFood;		
		
	}

	@Override
	public String aStar(Tree tree, boolean visualize, ArrayList<Operator> operators, HashSet<String> visitedStates,
			int heuristicNumber) {
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
