import enums.ResourceEnum;

public class RequestAction extends Operator {
    private int delay;
    private int addedAmount;

    private int deductAmount = 1;

    public RequestAction(String name, int addedAmount, int delay) {
        super(name);
        this.addedAmount = addedAmount;
        this.delay = delay;
    }


    @Override
    public State apply(State state) {
        if (state.getTimeRemaining() > 0 || state.getFutureResource() != null) {
            //System.out.println("we can't " + this.name + " request you have to wait for the delay");
            return null;
        } else if (state.getEnergy().getAmount() < this.deductAmount) {
            //System.out.println("we can't " + this.name + " energy amount is" + state.getEnergy().getAmount() + "less than required units" + this.deductAmount + " energy");
            return null;
        } else if (state.getFood().getAmount() < this.deductAmount) {
            //System.out.println("we can't " + this.name + " food amount is" + state.getFood().getAmount() + "less than required units" + this.deductAmount + " food");
            return null;
        } else if (state.getMaterial().getAmount() < this.deductAmount) {
            //System.out.println("we can't " + this.name + " material amount is" + state.getMaterial().getAmount() + "less than required units" + this.deductAmount + " material");
            return null;
        }
        
        switch (this.name) {
        case ResourceEnum.ENERGY:
            if(state.getEnergy().getAmount() + this.addedAmount > state.getEnergy().getMAX()) return null;
 
        case ResourceEnum.FOOD:
        	if(state.getFood().getAmount() + this.addedAmount > state.getFood().getMAX()) return null;
            
        case ResourceEnum.MATERIAL:
        	if(state.getMaterial().getAmount() + this.addedAmount > state.getMaterial().getMAX()) return null;
            
    }


        //System.out.println(state);
        //System.out.println(" |\n" +
                //this.name + " request \n" +
                //"V \n");


        Resource futureResource = null;

        switch (this.name) {
            case ResourceEnum.ENERGY:
                futureResource = new Resource(state.getEnergy().getCost(), this.addedAmount, this.name);
                break;
            case ResourceEnum.FOOD:
                futureResource = new Resource(state.getFood().getCost(), this.addedAmount, this.name);
                break;
            case ResourceEnum.MATERIAL:
                futureResource = new Resource(state.getMaterial().getCost(), this.addedAmount, this.name);
                break;
        }

        double newMoneySpent = state.getFood().getCost() * this.deductAmount + state.getMaterial().getCost() * this.deductAmount + state.getEnergy().getCost() * this.deductAmount;
        if (state.getBudget() < newMoneySpent) {
            //System.out.println("we can't " + this.name + "  budget is" + state.getBudget() + "less than required money" + newMoneySpent);
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

        if (newTimeRemaining == 0 && state.getFutureResource() != null) {
            // we used the WAIT Constructur it will make futureResource null and acquire future resource
            return new State(newState);
        }

        return newState;
    }

}