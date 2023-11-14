package code;

import enums.ResourceEnum;

abstract public class Operator {
    String name;

    public Operator(String name) {
        this.name = name;
    }

    abstract public State apply(State state);
    
    public String toString()
    {
    	return name;
    }

    
    protected State applyInternal(State state, double moneySpent, double deltaFood, double deltaMaterial, double deltaEnergy, int deltaProsperity) {
        if (state == null) return null;
        if (state.getFood().getAmount() + deltaFood < 0) return null;
        if (state.getMaterial().getAmount() + deltaMaterial < 0) return null;
        if (state.getEnergy().getAmount() + deltaEnergy < 0) return null;
        if (state.getBudget() < moneySpent) return null;
        if (moneySpent < 0) return null;
        
        Resource futureResource = state.getFutureResource();
        int timeRemaining = Math.max(state.getTimeRemaining() - 1, 0);
        if (state.getFutureResource() != null && timeRemaining == 0) {
        	switch(state.getFutureResource().getName()) {
                case ResourceEnum.FOOD:
                    deltaFood += state.getFutureResource().getAmount();
                    break;
                case ResourceEnum.MATERIAL:
                    deltaMaterial += state.getFutureResource().getAmount();
                    break;
                case ResourceEnum.ENERGY:
                    deltaEnergy += state.getFutureResource().getAmount();
                    break;
            }
            futureResource = null;
        }
        double newMoneySpent = moneySpent + state.getMoneySpent();
        double newBudget = state.getBudget() - moneySpent;
        Resource newFoodResource = deltaFood  == 0 ? state.getFood() : new Resource(state.getFood().getCost(), state.getFood().getAmount() + deltaFood, state.getFood().getName());
        Resource newMaterialResource = deltaMaterial == 0 ? state.getMaterial() : new Resource(state.getMaterial().getCost(), state.getMaterial().getAmount() + deltaMaterial, state.getMaterial().getName());
        Resource newEnergyResource = deltaEnergy == 0 ? state.getEnergy() : new Resource(state.getEnergy().getCost(), state.getEnergy().getAmount() + deltaEnergy, state.getEnergy().getName());

        return new State(newMoneySpent, newFoodResource, newEnergyResource, newMaterialResource, state.getLevelOfProsperity() + deltaProsperity, timeRemaining, futureResource, newBudget);
    }
}
