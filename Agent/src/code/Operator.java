package code;
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



}
