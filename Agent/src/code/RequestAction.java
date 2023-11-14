package code;

public class RequestAction extends Operator {
    private int delay;
    private int addedAmount;

    private static final int deductAmount = 1;

    public RequestAction(String name, int addedAmount, int delay) {
        super(name);
        this.setAddedAmount(addedAmount);
        this.delay = delay;
    }


    @Override
    public State apply(State state) {
    	
    	if (state.getFutureResource() != null) {
            return null;
        }

        State newState = applyInternal(
                state,
                state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost(), 
            -deductAmount, 
            -deductAmount, 
            -deductAmount, 
            0
        );
        if (newState == null) {
            return null;
        }
        Resource newResource = new Resource(0, this.getAddedAmount(), this.name);
        return new State(newState.getMoneySpent(), newState.getFood(), newState.getEnergy(), newState.getMaterial(), newState.getLevelOfProsperity(), this.delay, newResource, newState.getBudget());
    }


	public int getAddedAmount() {
		return addedAmount;
	}


	public void setAddedAmount(int addedAmount) {
		this.addedAmount = addedAmount;
	}

}
