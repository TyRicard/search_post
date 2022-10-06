public class WolfGoatCabbageState 
{    
    String[] startSide;
    String[] endSide;
    boolean boatStartSide;
    
    public WolfGoatCabbageState(String[] startSide, String[] endSide, boolean boatStartSide) { 
    	this.startSide = startSide; 
        this.endSide = endSide;
        this.boatStartSide = boatStartSide;
    }
    
    // Wolf will always have index 0, Goat index 1, and cabbage index 2
    public WolfGoatCabbageState(WolfGoatCabbageState state) {
        startSide = new String[3];
        endSide = new String[3];
    
        for(int i=0; i<3; i++) {
            startSide[i] = state.startSide[i];
            endSide[i] = state.endSide[i];
        }
        boatStartSide = state.boatStartSide;
    }
    
    public boolean equals(Object o) {
        return java.util.Arrays.equals( startSide, ((WolfGoatCabbageState) o).startSide ) &&
               java.util.Arrays.equals( endSide, ((WolfGoatCabbageState) o).endSide ) &&
               (boatStartSide == ((WolfGoatCabbageState) o).boatStartSide);
    }
    
    public int hashCode() {
        return (java.util.Arrays.hashCode( startSide ) + java.util.Arrays.hashCode( endSide ));
    }    
    
    public String toString() {
        if (boatStartSide) {
            return ("B " + java.util.Arrays.toString( startSide ) + " " + java.util.Arrays.toString( endSide ) + " |");
        } else {
            return (java.util.Arrays.toString( startSide ) + " B " + java.util.Arrays.toString( endSide ) + " |");
        }
    	
    }
}