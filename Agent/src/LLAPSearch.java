import java.util.*;

import enums.ResourceEnum;

public class LLAPSearch extends GenericSearch {
    private double budget = 100000;

    private ArrayList<Operator> operators = new ArrayList<Operator>();

    public void solve(String initialState, String strategy, Boolean visualize) {
        String[] splitInitialState = initialState.split(";");
        String[] splitResource = splitInitialState[1].split(",");
        String[] splitResourcePrice = splitInitialState[2].split(",");
        String[] splitFood = splitInitialState[3].split(",");
        String[] splitMaterial = splitInitialState[4].split(",");
        String[] splitEnergy = splitInitialState[5].split(",");
        String[] splitBuild1 = splitInitialState[6].split(",");
        String[] splitBuild2 = splitInitialState[7].split(",");
        State initState = getInitState(splitInitialState, splitResource, splitResourcePrice);
        Node root = new Node(initState, 0, null, null);
        Tree tree = new Tree(root);
        initializeOperators(splitFood, splitMaterial, splitEnergy, splitBuild1, splitBuild2);
        System.out.println(initState);
        switch (strategy) {
            case "BFS":
                BFS(tree, visualize);
                break;
            case "DFS":
                DFS(tree, visualize);
                break;
            case "UCS":
                UCS(tree, visualize);
                break;
            case "IDS":
                IDS(tree, visualize);
                break;
            case "GREEDY":
                GREEDY(tree, visualize);
                break;
            case "ASTAR":
                ASTAR(tree, visualize);
                break;
        }
    }

    private State getInitState(String[] splitInitialState, String[] splitResource, String[] splitResourcePrice) {
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

    private void initializeOperators(String[] splitFood, String[] splitMaterial, String[] splitEnergy, String[] splitBuild1,
            String[] splitBuild2) {
        int amountRequestFood = Integer.parseInt(splitFood[0]);
        int delayRequestFood = Integer.parseInt(splitFood[1]);
        operators.add(new RequestAction(ResourceEnum.FOOD, amountRequestFood, delayRequestFood));

        int amountRequestMaterial = Integer.parseInt(splitMaterial[0]);
        int delayRequestMaterial = Integer.parseInt(splitMaterial[1]);
        operators.add(new RequestAction(ResourceEnum.MATERIAL, amountRequestMaterial, delayRequestMaterial));

        int amountRequestEnergy = Integer.parseInt(splitEnergy[0]);
        int delayRequestEnergy = Integer.parseInt(splitEnergy[1]);
        operators.add(new RequestAction(ResourceEnum.ENERGY, amountRequestEnergy, delayRequestEnergy));

        double prosperityBuild1 = Double.parseDouble(splitBuild1[4]);
        double foodUseBuild1 = Double.parseDouble(splitBuild1[1]);
        double materialUseBuild1 = Double.parseDouble(splitBuild1[2]);
        double energyUseBuild1 = Double.parseDouble(splitBuild1[3]);
        double priceBuild1 = Double.parseDouble(splitBuild1[0]);
        operators.add(new BuildAction("BUILD1", prosperityBuild1, foodUseBuild1,
                materialUseBuild1, energyUseBuild1, priceBuild1));

        double prosperityBuild2 = Double.parseDouble(splitBuild2[4]);
        double foodUseBuild2 = Double.parseDouble(splitBuild2[1]);
        double materialUseBuild2 = Double.parseDouble(splitBuild2[2]);
        double energyUseBuild2 = Double.parseDouble(splitBuild2[3]);
        double priceBuild2 = Double.parseDouble(splitBuild2[0]);
        operators.add(new BuildAction("BUILD2", prosperityBuild2, foodUseBuild2,
                materialUseBuild2, energyUseBuild2, priceBuild2));

        operators.add(new WaitAction("WAIT"));
    }

    public void BFS(Tree tree, Boolean visualize) {
        System.out.println("BFS");
    }

    public void DFS(Tree tree, Boolean visualize) {
        System.out.println("DFS");
    }

    public void UCS(Tree tree, Boolean visualize) {
        System.out.println("UCS");
    }

    public void IDS(Tree tree, Boolean visualize) {
        System.out.println("IDS");
    }

    public void GREEDY(Tree tree, Boolean visualize) {
        System.out.println("GREEDY");
    }

    public void ASTAR(Tree tree, Boolean visualize) {
        System.out.println("ASTAR");
    }

}
