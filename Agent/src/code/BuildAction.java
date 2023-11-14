package code;
public class BuildAction extends Operator {
    public final double addProsperity;
    private final double unitsOfFood;
    private final double unitsOfMaterial;
    private final double unitsOfEnergy;
    public final double cost;


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
    	
        double moneySpent = (state.getFood().getCost() * this.getUnitsOfFood()) + (state.getMaterial().getCost() * this.getUnitsOfMaterial()) + (state.getEnergy().getCost() * this.getUnitsOfEnergy()) + this.cost;
        return applyInternal(state, moneySpent, -this.unitsOfFood, -this.unitsOfMaterial, -this.unitsOfEnergy, this.addProsperity);
    	
    }

	public double getUnitsOfEnergy() {
		return unitsOfEnergy;
	}

	public double getUnitsOfFood() {
		return unitsOfFood;
	}

	public double getUnitsOfMaterial() {
		return unitsOfMaterial;
	}
}
