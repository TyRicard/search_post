public class MissionariesandCannibalsState {
  
    int[] startSide;
    int[] endSide;
    boolean boatStartSide;
    
    public MissionariesandCannibalsState(int[] startSide, int[] endSide, boolean boatStartSide) { 
    	this.startSide = startSide; 
        this.endSide = endSide;
        this.boatStartSide = boatStartSide;
    }
    
    // Number of missionaries will be index 0, number of cannibals will be index 1
    public MissionariesandCannibalsState(MissionariesandCannibalsState state) {
        startSide = new int[2];
        endSide = new int[2];
    
        for(int i=0; i<2; i++) {
            startSide[i] = state.startSide[i];
            endSide[i] = state.endSide[i];
        }
        boatStartSide = state.boatStartSide;
    }
    
    public boolean equals(Object o) {
        return java.util.Arrays.equals( startSide, ((MissionariesandCannibalsState) o).startSide ) &&
               java.util.Arrays.equals( endSide, ((MissionariesandCannibalsState) o).endSide ) &&
               (boatStartSide == ((MissionariesandCannibalsState) o).boatStartSide);
    }
    
    public int hashCode() {
        return (java.util.Arrays.hashCode( startSide ) + java.util.Arrays.hashCode( endSide ));
    }    
    
    public String toString() {
        if (boatStartSide) {
            String temp = "";
            temp += "B [" + startSide[0] + "M, " + startSide[1] + "C ] ";
            temp += "[" + endSide[0] + "M, " + endSide[1] + "C ] |";
            return temp;
        } else {
            String temp = "";
            temp += "[" + startSide[0] + "M, " + startSide[1] + "C ] ";
            temp += "B [" + endSide[0] + "M, " + endSide[1] + "C ] |";
            return temp;
        }
    	
    }
}
