package code;
public class WaitAction extends Operator {
    private static final int deductAmount = 1;

    public WaitAction(String name) {
        super(name);
    }

    @Override
    public State apply(State state) {
    	return applyInternal(
            state,
            state.getFood().getCost() + state.getMaterial().getCost() + state.getEnergy().getCost(), 
            -deductAmount, 
            -deductAmount, 
            -deductAmount, 
            0);
    }
}
