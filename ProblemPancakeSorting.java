import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class ProblemPancakeSorting extends Problem {
    
    boolean goal_test(Object state) {
        StatePancakesSorting pancake_state = (StatePancakesSorting) state;
        
        int k=0;
        for(int i=0; i<pancake_state.N; i++){
        		if(pancake_state.pankaceArray[i] != k)
        			return false;
        		k++;
        	}
        
        return true;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StatePancakesSorting s = (StatePancakesSorting) state;
        
        StatePancakesSorting ss; //successor state
        
        //we can flip N-1 times
        for (int x= 0; x < s.N ; x++){
            ss = new StatePancakesSorting(s);
            int new_array[] = flip(s.pankaceArray, x, s.N);
            ss.pankaceArray = new_array;
            set.add(ss);
        }
        
        return set;
    }

    public static int[] flip(int[] array, int dig, int length){

        int[] new_array = new int[length];

        int f = 0;
        for(int x = 0; x <= dig; x++){
            new_array[x] = array[dig - f];
            f++;
        }

        for(int y = dig+1; y < length;y++){
            new_array[y] = array[y];
        }

        return new_array;
    }

    
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {
		ProblemPancakeSorting problem = new ProblemPancakeSorting();
		int[] pankaceArray = {1,0,3,5,2,4};
		problem.initialState = new StatePancakesSorting(pankaceArray); 
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		//System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		//System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		//System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		//System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		//System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
}
