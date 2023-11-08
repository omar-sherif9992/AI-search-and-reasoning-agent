package code;
public class WaitAction extends Operator {

    public WaitAction(String name) {
        super(name);
    }

    @Override
    public State apply(State state) {
    	
    	if(state.getFutureResource() == null)
    		return null;
    	
    	state.checkDelay();
    	
    	
    	
    	if(state.getFood().getAmount() < 1 || state.getMaterial().getAmount() < 1 
    			|| state.getEnergy().getAmount() < 1)
    		return null;
    	
    	if(state.getBudget() < (state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost()))
    		return null;
    	
    	
    	Resource newFoodResource = new Resource(state.getFood().getCost(), state.getFood().getAmount() - 1, state.getFood().getName());
        Resource newMaterialResource = new Resource(state.getMaterial().getCost(), state.getMaterial().getAmount() - 1, state.getMaterial().getName());
        Resource newEnergyResource = new Resource(state.getEnergy().getCost(), state.getEnergy().getAmount() - 1, state.getEnergy().getName());
    	
        
        double currMoneySpent =  (state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost());
        
        return new State(
        		currMoneySpent + state.getMoneySpent(),
                newFoodResource,
                newEnergyResource,
                newMaterialResource,
                state.getLevelOfProsperity(),
                state.getTimeRemaining() - 1,
                state.getFutureResource(),
                state.getBudget() - currMoneySpent
        );
    	
    	
    }
}
