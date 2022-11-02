public class StateNim extends State {
	
    public int rem_coins;
    
    public StateNim() {
    	rem_coins = 13;
        player = 1;
    }
    
    public StateNim(StateNim state) {
        rem_coins = state.rem_coins;
        player = state.player;
    }
    
    public String toString() {
    	return "Remaining Coins: " + String.valueOf(rem_coins) + "\n";
    }
}
