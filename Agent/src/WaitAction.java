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
    	
    	if(state.getFutureResource() == null || state.getTimeRemaining() <= 0)
    		return null;

        return new State(state);
    }
}
