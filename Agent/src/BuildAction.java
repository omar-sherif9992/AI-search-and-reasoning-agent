public class BuildAction extends Operator {
    private double addProsperity;
    private double unitsOfFood;
    private double unitsOfMaterial;
    private double unitsOfEnergy;
    private double cost;


    public BuildAction(String name, double addProsperity, double unitsOfFood, double unitsOfMaterial, double unitsOfEnergy, double cost) {
        super(name);
        this.addProsperity = addProsperity;
        this.unitsOfFood = unitsOfFood;
        this.unitsOfMaterial = unitsOfMaterial;
        this.unitsOfEnergy = unitsOfEnergy;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public State apply(State state) {
        if (state.getEnergy().getAmount() < this.unitsOfEnergy) {
            //System.out.println("we can't " + this.name + " energy amount is" + state.getEnergy().getAmount() + "less than required units" + this.unitsOfEnergy + " energy");
            return null;
        } else if (state.getFood().getAmount() < this.unitsOfFood) {
            //System.out.println("we can't " + this.name + " food amount is" + state.getFood().getAmount() + "less than required units" + this.unitsOfFood + " food");
            return null;
        } else if (state.getMaterial().getAmount() < this.unitsOfMaterial) {
            //System.out.println("we can't " + this.name + " material amount is" + state.getMaterial().getAmount() + "less than required units" + this.unitsOfMaterial + " material");
            return null;
        }

        double newMoneySpent = state.getFood().getCost() * this.unitsOfFood + state.getMaterial().getCost() * this.unitsOfMaterial + state.getEnergy().getCost() * this.unitsOfEnergy + this.cost;
        if (state.getBudget() < newMoneySpent) {
            //System.out.println("we can't " + this.name + "  budget is" + state.getBudget() + "less than required money" + newMoneySpent);
            return null;
        }

        double newBudget = state.getBudget() - newMoneySpent;
        newMoneySpent += state.getMoneySpent();
        double newProsperityLevel = state.getLevelOfProsperity() + this.addProsperity;

        Resource newFoodResource = new Resource(state.getFood().getCost(), state.getFood().getAmount() - this.unitsOfFood, state.getFood().getName());
        Resource newMaterialResource = new Resource(state.getMaterial().getCost(), state.getMaterial().getAmount() - this.unitsOfMaterial, state.getMaterial().getName());
        Resource newEnergyResource = new Resource(state.getEnergy().getCost(), state.getEnergy().getAmount() - this.unitsOfEnergy, state.getEnergy().getName());
        int newTimeRemaining = Math.max(0, state.getTimeRemaining() - 1);
        State newState = new State(newMoneySpent, newFoodResource, newEnergyResource, newMaterialResource, newProsperityLevel, newTimeRemaining, state.getFutureResource(), newBudget);

        if (newTimeRemaining == 0 && state.getFutureResource() != null) {
            // we used the WAIT Constructur it will make futureResource null
            return new State(newState);
        }
        return  newState;
    }


}
