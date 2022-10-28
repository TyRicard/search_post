import java.util.HashSet;
import java.util.Set;

public class ProblemWolfGoatCabbage extends Problem {
    
	boolean goal_test(Object state) {
		WolfGoatCabbageState currState = (WolfGoatCabbageState) state;
		String[] startSide = {"", "", ""};
		String[] endSide = {"W", "G", "C"};
        WolfGoatCabbageState goal = new WolfGoatCabbageState(startSide, endSide, false);
        return currState.equals(goal);
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
		WolfGoatCabbageState s = (WolfGoatCabbageState) state;
		boolean boatStartSide = s.boatStartSide;
        
        WolfGoatCabbageState ss; //successor state
        
		//Start Side
		if (boatStartSide) {

			// Check For Wolf
			if (s.startSide[0].equals("W") && 
			   (s.startSide[1] == "" || s.startSide[2] == "")) 
			{
				ss = new WolfGoatCabbageState(s);
				ss.startSide[0] = "";
				ss.endSide[0] = "W";
				ss.boatStartSide = false;
				set.add(ss);
			}

			// Check for Goat
			if (s.startSide[1].equals("G")) 
		 	{
			 	ss = new WolfGoatCabbageState(s);
			 	ss.startSide[1] = "";
			 	ss.endSide[1] = "G";
			 	ss.boatStartSide = false;
				set.add(ss);
		 	}

			// Check For Cabbage
			if (s.startSide[2].equals("C") && 
			   (s.startSide[0] == "" || s.startSide[1] == "")) 
		 	{
			 	ss = new WolfGoatCabbageState(s);
				ss.startSide[2] = "";
			 	ss.endSide[2] = "C";
			 	ss.boatStartSide = false;
				set.add(ss);
			}
			
			// Can move empty boat across if the goat is alone or the goat is not present
			if ((s.startSide[1] == "G" && s.startSide[0] == "" && s.startSide[2] == "") ||
			    (s.startSide[1] == "")) {
					ss = new WolfGoatCabbageState(s);
					ss.boatStartSide = false;
					set.add(ss);
				}

		}

		// End Side
		else {

			// Check For Wolf
			if (s.endSide[0].equals("W") && 
			   (s.endSide[1] == "" || s.endSide[2] == "")) 
			{
				ss = new WolfGoatCabbageState(s);
				ss.endSide[0] = "";
				ss.startSide[0] = "W";
				ss.boatStartSide = true;
				set.add(ss);
			}

			// Check for Goat
			if (s.endSide[1].equals("G")) 
		 	{
			 	ss = new WolfGoatCabbageState(s);
			 	ss.endSide[1] = "";
			 	ss.startSide[1] = "G";
			 	ss.boatStartSide = true;
				set.add(ss);
		 	}

			// Check For Cabbage
			if (s.endSide[2].equals("C") && 
			   (s.endSide[0] == "" || s.endSide[1] == "")) 
		 	{
			 	ss = new WolfGoatCabbageState(s);
				ss.endSide[2] = "";
			 	ss.startSide[2] = "C";
			 	ss.boatStartSide = true;
				set.add(ss);
			}
			
			// Can move empty boat across if the goat is alone or the goat is not present
			if ((s.endSide[1] == "G" && s.endSide[0] == "" && s.endSide[2] == "") ||
				(s.endSide[1] == "")) {
				ss = new WolfGoatCabbageState(s);
				ss.boatStartSide = true;
				set.add(ss);
			}
		}
        
        return set;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) {
		WolfGoatCabbageState s = (WolfGoatCabbageState) state;
		double not_crossed = 0.0;
		for (int i = 0; i < 3; i++) {
			if (s.endSide[i] == "") {
				not_crossed += 1.0;
			}
		}
		return not_crossed;
	 }


	public static void main(String[] args) throws Exception {
		ProblemWolfGoatCabbage problem = new ProblemWolfGoatCabbage();
		String[] startSide = {"W", "G", "C"};
		String[] endSide = {"", "", ""};
		problem.initialState = new WolfGoatCabbageState(startSide, endSide, true); 
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
