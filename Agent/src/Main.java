public class Main {
    public static void main(String[] args) {
        String init = "50;"+
            "22,22,22;" +
            "50,60,70;" +
            "30,2;19,1;15,1;" +
            "300,5,7,3,20;" +
            "500,8,6,3,40;";

        LLAPSearch llapSearch = new LLAPSearch();
        llapSearch.solve(init, "BFS", true);    
    }
}