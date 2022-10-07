import java.util.HashSet;
import java.util.Set;

public class ProblemMissionariesandCannibals extends Problem {
    
	boolean goal_test(Object state) {
		MissionariesandCannibalsState currState = (MissionariesandCannibalsState) state;
		int[] startSide = {0,0};
		int[] endSide = {3,3};
        MissionariesandCannibalsState goal = new MissionariesandCannibalsState(startSide, endSide, false);
        return currState.equals(goal);
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
		MissionariesandCannibalsState s = (MissionariesandCannibalsState) state;
		boolean boatStartSide = s.boatStartSide;
        
        MissionariesandCannibalsState ss; //successor state
        
		//Start Side
		if (boatStartSide) {

			// Check for 1 missionary to cross
			if ((s.startSide[0] > 0) 
			&& (s.startSide[0] - 1 >= s.startSide[1] || s.startSide[0] - 1 == 0) 
			&& (s.endSide[0] + 1 >= s.endSide[1]))
			{
				ss = new MissionariesandCannibalsState(s);
				ss.startSide[0] -= 1;
				ss.endSide[0] += 1;
				ss.boatStartSide = false;
				set.add(ss);
			}

			// Check For two missionary 
			if ((s.startSide[0] > 1) 
				&& (s.startSide[0] - 2 >= s.startSide[1] || s.startSide[0] - 2 == 0)  
				&& (s.endSide[0] + 2 >= s.endSide[1])) 
			{
				ss = new MissionariesandCannibalsState(s);
				ss.startSide[0] -= 2;
				ss.endSide[0] += 2;
				ss.boatStartSide = false;
				set.add(ss);
			}

			// Check For one cannibal and one missionary 
			if ((s.startSide[0] > 0 && s.startSide[1] > 0) 
				&& s.endSide[0] >= s.endSide[1])
			{
				ss = new MissionariesandCannibalsState(s);
				ss.startSide[0] -= 1;
				ss.endSide[0] += 1;
				ss.startSide[1] -= 1;
				ss.endSide[1] += 1;
				ss.boatStartSide = false;
				set.add(ss);
			}

			// Check for one cannibal to cross  
			if (s.startSide[1] > 0 
				&& ((s.endSide[1] + 1 <= s.endSide[0]) || s.endSide[0] == 0))
		 	{
			 	ss = new MissionariesandCannibalsState(s);
			 	ss.startSide[1] -= 1;
			 	ss.endSide[1] += 1;
			 	ss.boatStartSide = false;
				set.add(ss);
		 	}
			
            // Check For two cannibals
			if (s.startSide[1] > 1 
				&& ((s.endSide[1] + 2 <= s.endSide[0]) || s.endSide[0] == 0)) 
		 	{
                ss = new MissionariesandCannibalsState(s);
				ss.startSide[1] -= 2;
			 	ss.endSide[1] += 2;
			 	ss.boatStartSide = false;
				set.add(ss);
			}
		}

		// End Side
		else {

			// Check for 1 missionary to cross
			if ((s.endSide[0] > 0) 
				&& (s.endSide[0] - 1 >= s.endSide[1] || s.endSide[0] - 1 == 0) 
				&& (s.startSide[0] + 1 >= s.startSide[1]))
			{
				ss = new MissionariesandCannibalsState(s);
				ss.endSide[0] -= 1;
				ss.startSide[0] += 1;
				ss.boatStartSide = true;
				set.add(ss);
			}

			// Check For two missionary 
			if ((s.endSide[0] > 1) 
				&& (s.endSide[0] - 2 >= s.endSide[1] || s.endSide[0] - 2 == 0) 
				&& (s.startSide[0] + 2 >= s.startSide[1]))
			{
				ss = new MissionariesandCannibalsState(s);
				ss.endSide[0] -= 2;
				ss.startSide[0] += 2;
				ss.boatStartSide = true;
				set.add(ss);
			}

			// Check For one cannibal and one missionary 
			if ((s.endSide[0] > 0 && s.endSide[1] > 0) 
				&& (s.startSide[0] >= s.startSide[1]))
			{
				ss = new MissionariesandCannibalsState(s);
				ss.endSide[0] -= 1;
				ss.startSide[0] += 1;
				ss.endSide[1] -= 1;
				ss.startSide[1] += 1;
				ss.boatStartSide = true;
				set.add(ss);
			}

			// Check for one cannibal to cross  
			if (s.endSide[1] > 0 
				&& ((s.startSide[1] + 1 <= s.startSide[0]) || s.startSide[0] == 0))
			{
				ss = new MissionariesandCannibalsState(s);
				ss.endSide[1] -= 1;
				ss.startSide[1] += 1;
				ss.boatStartSide = true;
				set.add(ss);
			}

			// Check For two cannibals
			if (s.endSide[1] > 1 
				&& ((s.startSide[1] + 2 <= s.startSide[0]) || s.startSide[0] == 0)) 
			{
				ss = new MissionariesandCannibalsState(s);
				ss.endSide[1] -= 2;
				ss.startSide[1] += 2;
				ss.boatStartSide = true;
				set.add(ss);
			}
		}
        
        return set;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }


    //change 
	public double h(Object state) {
		MissionariesandCannibalsState s = (MissionariesandCannibalsState) state;
		double not_crossed = (s.startSide[0] + s.startSide[1]) / 2;
		return not_crossed;
	 }


	public static void main(String[] args) throws Exception {
		ProblemMissionariesandCannibals problem = new ProblemMissionariesandCannibals();
		int[] startSide = {3,3};
		int[] endSide = {0,0};
		problem.initialState = new MissionariesandCannibalsState(startSide, endSide, true); 
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		// System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
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
