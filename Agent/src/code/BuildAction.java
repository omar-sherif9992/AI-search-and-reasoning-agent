package code;
public class BuildAction extends Operator {
    public double addProsperity;
    private double unitsOfFood;
    private double unitsOfMaterial;
    private double unitsOfEnergy;
    public double cost;


    public BuildAction(String name, double addProsperity, double unitsOfFood, double unitsOfMaterial, double unitsOfEnergy, double cost) {
        super(name);
        this.addProsperity = addProsperity;
        this.setUnitsOfFood(unitsOfFood);
        this.setUnitsOfMaterial(unitsOfMaterial);
        this.setUnitsOfEnergy(unitsOfEnergy);
        this.name = name;
        this.cost = cost;
    }

    @Override
    public State apply(State state) {
    	
    	state.checkDelay();
    	
        if (state.getEnergy().getAmount() < this.getUnitsOfEnergy()) {
            //System.out.println("we can't " + this.name + " energy amount is" + state.getEnergy().getAmount() + "less than required units" + this.unitsOfEnergy + " energy");
            return null;
        } else if (state.getFood().getAmount() < this.getUnitsOfFood()) {
            //System.out.println("we can't " + this.name + " food amount is" + state.getFood().getAmount() + "less than required units" + this.unitsOfFood + " food");
            return null;
        } else if (state.getMaterial().getAmount() < this.getUnitsOfMaterial()) {
            //System.out.println("we can't " + this.name + " material amount is" + state.getMaterial().getAmount() + "less than required units" + this.unitsOfMaterial + " material");
            return null;
        }

        double newMoneySpent = (state.getFood().getCost() * this.getUnitsOfFood()) + (state.getMaterial().getCost() * this.getUnitsOfMaterial()) + (state.getEnergy().getCost() * this.getUnitsOfEnergy()) + this.cost;
        if (state.getBudget() < newMoneySpent) {
            //System.out.println("we can't " + this.name + "  budget is" + state.getBudget() + "less than required money" + newMoneySpent);
            return null;
        }

        double newBudget = state.getBudget() - newMoneySpent;
        newMoneySpent += state.getMoneySpent();
        double newProsperityLevel = state.getLevelOfProsperity() + this.addProsperity;

        Resource newFoodResource = new Resource(state.getFood().getCost(), state.getFood().getAmount() - this.getUnitsOfFood(), state.getFood().getName());
        Resource newMaterialResource = new Resource(state.getMaterial().getCost(), state.getMaterial().getAmount() - this.getUnitsOfMaterial(), state.getMaterial().getName());
        Resource newEnergyResource = new Resource(state.getEnergy().getCost(), state.getEnergy().getAmount() - this.getUnitsOfEnergy(), state.getEnergy().getName());
        
        
        State newState = new State(newMoneySpent, newFoodResource, newEnergyResource, newMaterialResource, 
        		newProsperityLevel, state.getTimeRemaining() - 1, state.getFutureResource(), newBudget);
        
        return  newState;
    }

	public double getUnitsOfEnergy() {
		return unitsOfEnergy;
	}

	public void setUnitsOfEnergy(double unitsOfEnergy) {
		this.unitsOfEnergy = unitsOfEnergy;
	}

	public double getUnitsOfFood() {
		return unitsOfFood;
	}

	public void setUnitsOfFood(double unitsOfFood) {
		this.unitsOfFood = unitsOfFood;
	}

	public double getUnitsOfMaterial() {
		return unitsOfMaterial;
	}

	public void setUnitsOfMaterial(double unitsOfMaterial) {
		this.unitsOfMaterial = unitsOfMaterial;
	}
    
 


}
