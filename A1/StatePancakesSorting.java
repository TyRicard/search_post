public class StatePancakesSorting 
{    
	int N;
    int[] pankaceArray;

    
    public StatePancakesSorting(int[] pankaceArray) { 
    	this.pankaceArray = pankaceArray; 
    	N = pankaceArray.length;

    }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StatePancakesSorting(StatePancakesSorting state) {
    	N = state.N;
    	pankaceArray = new int[N];
    	
        for(int i=0; i<N; i++)
            pankaceArray[i] = state.pankaceArray[i];
        
    }
    //Needed to compare states. This is needed only for GRAPH serach!
    public boolean equals(Object o) {
        return java.util.Arrays.equals( pankaceArray, ((StatePancakesSorting) o).pankaceArray );
    }
    
    //Generate hash function value given content of array. This is needed only for GRAPH serach!
    public int hashCode() {
        return java.util.Arrays.hashCode( pankaceArray );
    }    
    
    //to print out solution as a string
    public String toString() {
    	return java.util.Arrays.toString( pankaceArray );
    }
}