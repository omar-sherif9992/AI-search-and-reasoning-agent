public class WaitAction extends Operator {

    @Override
    public State apply(State state) {
        System.out.println(state);
        System.out.println(" |\n" +
                "WAIT ACTION\n"+
                "V \n");

        return new State(state);
    }
}
