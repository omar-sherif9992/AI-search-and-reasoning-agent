package code;
public class Main {
    public static void main(String[] args) {
        String init0 = "50;"+
            "22,22,22;" +
            "50,60,70;" +
            "30,2;19,1;15,1;" +
            "300,5,7,3,20;" +
            "500,8,6,3,40;";
        
        String init1 = "1;" +
    			"6,10,7;" +
    			"2,1,66;" +
    			"34,2;22,1;14,2;" +
    			"1500,5,9,9,26;" +
    			"168,13,13,14,46;";

        //LLAPSearch llapSearch = new LLAPSearch();
        //String res = LLAPSearch.solve(init1, "BF", false);  
        //System.out.println("===================");
        //System.out.println(res);
        
        for(int i = 0; i < 2; ++i)
        {
        	if(i == 0)
        	{
        		//String res = LLAPSearch.solve(init0, "BF", false);  
                //System.out.println("===================");
                //System.out.println(res);
        	}
        	else
        	{
        		String res = LLAPSearch.solve(init1, "BF", false);  
                System.out.println("===================");
                System.out.println(res);
        	}
        		
        }
        
    }
}