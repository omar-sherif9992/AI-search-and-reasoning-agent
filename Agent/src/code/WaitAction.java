package code;
public class WaitAction extends Operator {

    public WaitAction(String name) {
        super(name);
    }

    @Override
    public State apply(State state) {
        //System.out.println(state);
        //System.out.println(" |\n" +
          //      "WAIT ACTION\n"+
            //    "V \n");
    	state.checkDelay();
    	
    	if(state.getFood().getAmount() < 1 || state.getMaterial().getAmount() < 1 
    			|| state.getEnergy().getAmount() < 1)
    		return null;
    	
    	if(state.getBudget() < (state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost()))
    		return null;
    	
    	
    	/*if(state.getFutureResource() == null || state.getTimeRemaining() <= 0)
    		return null;
    	*/

        //State ret =  new State(state);
        //
        //
        /*ret.getFood().setAmount(ret.getFood().getAmount()-1);
        ret.getMaterial().setAmount(ret.getMaterial().getAmount()-1);
        ret.getEnergy().setAmount(ret.getEnergy().getAmount()-1);
        //
        if(ret.getFood().getAmount() < 0 || ret.getEnergy().getAmount() < 0 || ret.getMaterial().getAmount() < 0)
        	return null;
        
        double moneyDeduct = ret.getFood().getCost() + ret.getEnergy().getCost() + ret.getMaterial().getCost();
        
        ret.setBudget(ret.getBudget() - moneyDeduct);
        
        return ret;*/
        //
    	
    	Resource newFoodResource = new Resource(state.getFood().getCost(), state.getFood().getAmount() - 1, state.getFood().getName());
        Resource newMaterialResource = new Resource(state.getMaterial().getCost(), state.getMaterial().getAmount() - 1, state.getMaterial().getName());
        Resource newEnergyResource = new Resource(state.getEnergy().getCost(), state.getEnergy().getAmount() - 1, state.getEnergy().getName());
    	
        int newTimeRemaining = state.getTimeRemaining();
        
        double currMoneySpent =  (state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost());
        
        return new State(
        		currMoneySpent + state.getMoneySpent(),
                newFoodResource,
                newEnergyResource,
                newMaterialResource,
                state.getLevelOfProsperity(),
                newTimeRemaining,
                state.getFutureResource(),
                state.getBudget() - currMoneySpent
        );
    	
    	
    }
}
