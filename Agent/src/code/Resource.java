package code;
import java.util.Objects;

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
        if (o == this) return true;
        if (!(o instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) o;
        if(cost != other.getCost())
        	return false;
        if(amount != other.getAmount())
        	return false;
        return name.equals(other.getName());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cost, amount, name);
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

