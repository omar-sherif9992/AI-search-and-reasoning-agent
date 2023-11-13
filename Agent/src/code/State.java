package code;
import java.util.Objects;

import enums.ResourceEnum;

public class State {
    private double moneySpent;
    private final Resource food;
    private final Resource energy;
    private final Resource material;
    private final double levelOfProsperity;
    private int timeRemaining;
    private Resource futureResource;
    private double budget;



    public State(double moneySpent, Resource food, Resource energy, Resource material, double levelOfProsperity,int timeRemaining,Resource futureResource,double budget) {
        this.moneySpent = moneySpent;
        this.food = food;
        this.energy = energy;
        this.material = material;
        this.levelOfProsperity = levelOfProsperity;
        this.timeRemaining = timeRemaining;
        this.budget = budget;
        this.futureResource = futureResource;
    }


    // FOR WAIT  ACTION
    
    // DELIEVER THE DELIEVRY AFTER DELAY FINISHES
    public State(State state) {
        this.moneySpent = state.getMoneySpent();
        this.food = new Resource(state.getFood());
        this.energy = new Resource(state.getEnergy());
        this.material = new Resource(state.getMaterial());
        
        this.levelOfProsperity = state.getLevelOfProsperity();
        this.budget = state.getBudget();
        this.timeRemaining = 0;
        this.futureResource = null;
        
        if(state.getFutureResource() == null)
        {
        	return;
        }

        double addedAmount = state.getFutureResource().getAmount();
        switch (state.getFutureResource().getName()){
            case ResourceEnum.ENERGY : this.energy.setAmount(
                    addedAmount+this.energy.getAmount()
            );break;
            case ResourceEnum.FOOD:  this.food.setAmount(
                    addedAmount+this.food.getAmount()
            );break;
            case ResourceEnum.MATERIAL : this.material.setAmount(
                    addedAmount+this.material.getAmount()
            );break;
        }
    }
    @Override
    public String toString() {
        return "State{\n" +
                //"moneySpent=" + moneySpent +      // + MONEY 
                ",\n food=" + food +
                ",\n energy=" + energy +
                ",\n material=" + material +
                ",\n levelOfProsperity=" + levelOfProsperity +
                ",\n timeRemaining=" + timeRemaining +
                ",\n futureResource=" + futureResource +
               '}';
    }
    
    @Override 
    public boolean equals(Object o)
    {
        if (o == this) { 
            return true; 
        }
        if (!(o instanceof State)) { 
            return false; 
        }
        State other = (State) o;

    	// Resource f1 = futureResource == null? new Resource(-1, -1, "HUSSEINYASSER"):other.getFutureResource();

    	// Resource f2 = other.getFutureResource() == null? new Resource(-1, -1, "HUSSEINYASSER"):other.getFutureResource();
    	if(moneySpent != other.moneySpent)
    		return false;
    	if(!food.equals(other.food))
    		return false;
    	if(!material.equals(other.material))
    		return false;
    	if(!energy.equals(other.energy))
    		return false;
    	if(levelOfProsperity != other.levelOfProsperity)
    		return false;
    	if(timeRemaining != other.timeRemaining)
    		return false;
        
    	if (futureResource == null) return other.futureResource == null;
        if (other.futureResource == null) return false;
        return futureResource.equals(other.futureResource);
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(moneySpent, food, material, energy, timeRemaining, levelOfProsperity, futureResource);        
    }
    

    public double getBudget() {
        return budget;
    }
    
    public void setBudget(double x) {
        this.budget = x;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public Resource getFood() {
        return food;
    }

    public Resource getEnergy() {
        return energy;
    }

    public Resource getMaterial() {
        return material;
    }

    public double getLevelOfProsperity() {
        return levelOfProsperity;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public Resource getFutureResource() {
        return futureResource;
    }
    
    public void checkDelay()
    {
    	
    	if(this.timeRemaining <= 1 && this.futureResource != null)
    	{
    		//Here delivery done
    		double addedAmount = this.getFutureResource().getAmount();
            switch (this.getFutureResource().getName()){
                case ResourceEnum.ENERGY : this.energy.setAmount(
                        addedAmount+this.energy.getAmount()
                );break;
                case ResourceEnum.FOOD:  this.food.setAmount(
                        addedAmount+this.food.getAmount()
                );break;
                case ResourceEnum.MATERIAL : this.material.setAmount(
                        addedAmount+this.material.getAmount()
                );break;
            }
            this.futureResource = null;
    	}
    }
}
