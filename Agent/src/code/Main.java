package code;
public class Main {
    public static void main(String[] args) {
        String init0 = "17;" +
                "49,30,46;" +
                "7,57,6;" +
                "7,1;20,2;29,2;" +
                "350,10,9,8,28;" +
                "408,8,12,13,34;";
        
        String init1 = "50;" +
                "12,12,12;" +
                "50,60,70;" +
                "30,2;19,2;15,2;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";
        
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
               
        	}
        		
        }
        
    }
}