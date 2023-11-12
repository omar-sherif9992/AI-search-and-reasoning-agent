package code;
import enums.ResourceEnum;

public class RequestAction extends Operator {
    private int delay;
    private int addedAmount;

    private int deductAmount = 1;

    public RequestAction(String name, int addedAmount, int delay) {
        super(name);
        this.setAddedAmount(addedAmount);
        this.delay = delay;
    }


    @Override
    public State apply(State state) {
    	
    	state.checkDelay();
    	
    	if(state.getFutureResource() != null)
    		return null;
    	
        if (state.getEnergy().getAmount() < this.deductAmount) {
            //System.out.println("we can't " + this.name + " energy amount is" + state.getEnergy().getAmount() + "less than required units" + this.deductAmount + " energy");
            return null;
        } else if (state.getFood().getAmount() < this.deductAmount) {
            //System.out.println("we can't " + this.name + " food amount is" + state.getFood().getAmount() + "less than required units" + this.deductAmount + " food");
            return null;
        } else if (state.getMaterial().getAmount() < this.deductAmount) {
            //System.out.println("we can't " + this.name + " material amount is" + state.getMaterial().getAmount() + "less than required units" + this.deductAmount + " material");
            return null;
        }
        
        Resource futureResource = null;

        switch (this.name) {
            case ResourceEnum.ENERGY:
                futureResource = new Resource(state.getEnergy().getCost(), this.getAddedAmount(), this.name);
                break;
            case ResourceEnum.FOOD:
                futureResource = new Resource(state.getFood().getCost(), this.getAddedAmount(), this.name);
                break;
            case ResourceEnum.MATERIAL:
                futureResource = new Resource(state.getMaterial().getCost(), this.getAddedAmount(), this.name);
                break;
        }
        
        switch (this.name) {
        case ResourceEnum.ENERGY:
            if(state.getEnergy().getAmount() == 50)
            	return null;
            break;
        case ResourceEnum.FOOD:
        	if(state.getFood().getAmount() == 50)
            	return null;
            break;
        case ResourceEnum.MATERIAL:
        	if(state.getMaterial().getAmount() == 50)
            	return null;
            break;
        }

        double newMoneySpent = (state.getFood().getCost() * this.deductAmount) + (state.getMaterial().getCost() * this.deductAmount) + (state.getEnergy().getCost() * this.deductAmount);
        if (state.getBudget() < newMoneySpent) {
            return null;
        }
        

        double newBudget = state.getBudget() - newMoneySpent;
        newMoneySpent += state.getMoneySpent();
        Resource newFoodResource = new Resource(state.getFood().getCost(), state.getFood().getAmount() - this.deductAmount, state.getFood().getName());
        Resource newMaterialResource = new Resource(state.getMaterial().getCost(), state.getMaterial().getAmount() - this.deductAmount, state.getMaterial().getName());
        Resource newEnergyResource = new Resource(state.getEnergy().getCost(), state.getEnergy().getAmount() - this.deductAmount, state.getEnergy().getName());


        int newTimeRemaining = this.delay;


        State newState = new State(
                newMoneySpent,
                newFoodResource,
                newEnergyResource,
                newMaterialResource,
                state.getLevelOfProsperity(),
                newTimeRemaining,
                futureResource,
                newBudget
        );
        return newState;
    }


	public int getAddedAmount() {
		return addedAmount;
	}


	public void setAddedAmount(int addedAmount) {
		this.addedAmount = addedAmount;
	}

}
