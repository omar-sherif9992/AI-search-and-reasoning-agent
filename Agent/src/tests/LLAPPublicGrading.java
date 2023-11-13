package tests;
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import code.LLAPSearch;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LLAPPublicGrading {

	String initialState0 = "17;" +
                "49,30,46;" +
                "7,57,6;" +
                "7,1;20,2;29,2;" +
                "350,10,9,8,28;" +
                "408,8,12,13,34;";
	String initialState1 = "50;" +
                "12,12,12;" +
                "50,60,70;" +
                "30,2;19,2;15,2;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";
    String initialState2 = "30;" +
                "30,25,19;" +
                "90,120,150;" +
                "9,2;13,1;11,1;" +
                "3195,11,12,10,34;" +
                "691,7,8,6,15;";
    String initialState3 = "0;" +
                "19,35,40;" +
                "27,84,200;" +
                "15,2;37,1;19,2;" +
                "569,11,20,3,50;"+
                "115,5,8,21,38;" ;

    String initialState4 = "21;" +
                "15,19,13;" +
                "50,50,50;" +
                "12,2;16,2;9,2;" +
                "3076,15,26,28,40;" +
                "5015,25,15,15,38;";
    String initialState5 = "72;" +
                "36,13,35;" +
                "75,96,62;" +
                "20,2;5,2;33,2;" +
                "30013,7,6,3,36;" +
                "40050,5,10,14,44;";
    String initialState6 = "29;" +
                "14,9,26;" +
                "650,400,710;" +
                "20,2;29,2;38,1;" +
                "8255,8,7,9,36;" +
                "30670,12,12,11,36;";
	String initialState7= "1;" +
			"6,10,7;" +
			"2,1,66;" +
			"34,2;22,1;14,2;" +
			"1500,5,9,9,26;" +
			"168,13,13,14,46;";
	String initialState8 = "93;" +
			"46,42,46;" +
			"5,32,24;" +
			"13,2;24,1;20,1;" +
			"155,7,5,10,7;" +
			"5,5,5,4,4;";
	String initialState9 = "50;" +
			"20,16,11;" +
			"76,14,14;" +
			"7,1;7,1;7,1;" +
			"359,14,25,23,39;" +
			"524,18,17,17,38;";
	String initialState10= "32;" +
			"20,16,11;" +
			"76,14,14;" +
			"9,1;9,2;9,1;" +
			"358,14,25,23,39;" +
			"5024,20,17,17,38;";



	
	@Test(timeout = 120000)
	public void testg9() throws Exception {
		String solution = LLAPSearch.solve(initialState1, "AS1", false);
		solution = solution.replace(" ", "");
		LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
		assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
	}
	


	
	@Test(timeout = 120000)
	public void testh9() throws Exception {
		String solution = LLAPSearch.solve(initialState1, "AS2", false);
		solution = solution.replace(" ", "");
		LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
		assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
	}
	
	@Test(timeout = 120000)
	public void testh15() throws Exception {
		String solution = LLAPSearch.solve(initialState1, "UC", false);
		solution = solution.replace(" ", "");
		LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
		assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
	}
	
	

}