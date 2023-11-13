package code;
import java.util.Objects;

import enums.ResourceEnum;

public class Resource {

    private double cost;
    private double amount;

    private final int MAX=50;

    
    private String name;

    public Resource( double cost, double amount, String name) {
        this.cost = cost;
        this.amount = amount;
        this.name = name;
    }


    public Resource( Resource resource) {
        this.cost = resource.getCost();
        this.amount = resource.getAmount();
        this.name = resource.getName();
    }

    @Override
    public String toString() {
        return "Resource{" +
                "cost=" + cost +
                ", amount=" + amount +
                ", MAX=" + MAX +
                ", name=" + name +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if(cost != ((Resource)o).getCost())
        	return false;
        if(amount != ((Resource)o).getAmount())
        	return false;
        if(name != ((Resource)o).getName())
        	return false;
        return true;
    }
    
    @Override
    public int hashCode() {
    	final int prime = 31; // Choose a prime number for multiplication
        int result = 17; // Another prime number, acts as a starting value

        // Include the hash codes of each field
        result = prime * result + Double.hashCode(cost);
        result = prime * result + Double.hashCode(amount);
        result = prime * result + Objects.hashCode(name);
        
        return result;
    }
    
    
    
    public double getMAX() {
        return MAX;
    }

    public double getCost() {
        return cost;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setAmount(double amount) {
        this.amount = Math.min(amount,this.MAX);
        
    }
}

