import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game { 
    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;    
    
    public GameNim() {
    	currentState = new StateNim();
    }
    

    public boolean isWinState(State state)
    {
        StateNim tstate = (StateNim) state;
        if (tstate.rem_coins == 1) return true;
        return false;
    }
    

    public boolean isStuckState(State state) {
    	
        if (isWinState(state)) 
            return false;
        
        StateNim tstate = (StateNim) state;
        
        if (tstate.rem_coins != 0) {
            return false;
        }
        return true;
    }
	
	
	public Set<State> getSuccessors(State state)
    {
		if(isWinState(state) || isStuckState(state))
			return null;
		
		Set<State> successors = new HashSet<State>();
        StateNim tstate = (StateNim) state;
        
        StateNim successor_state;

        int iterator = Math.min(tstate.rem_coins, 3);
        
        for (int i = 1; i <= iterator; i++) {
            successor_state = new StateNim(tstate);
            successor_state.rem_coins = tstate.rem_coins - i;
            successor_state.player = (state.player== 0 ? 1 : 0); 
    
            successors.add(successor_state);
        }
    
        return successors;
    }	
    

    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player== 0 ? 1 : 0);
    	
	    	if (previous_player == 0) //computer wins
	            return WinningScore;
	    	else //human wins
	            return LosingScore;
    	}

        return NeutralScore;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        Game game = new GameNim(); 
        Search search = new Search(game);
        // Depth is set to 12 for the 12 possible coins accessible to the computer
        // at its turn. The tic-tac-toe has 8 because at least one of the squares must 
        // be occupied.
        int depth = 12;
        
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	
        	StateNim nextState = null;
            int take_coins = -1; // Initialize to -1 to prevent possible issues
        	
            switch ( game.currentState.player ) {
              case 1: //Human
                  
            	  //get human's move
                  // As is the case with the Tic-Tac-Toe example, it is assumed
                  // valid input will be provided. 
                  System.out.print("Enter your *valid* move> ");
                  take_coins = Integer.parseInt( in.readLine() );
            	  
                  nextState = new StateNim( (StateNim) game.currentState);
                  nextState.player = 1;
                  nextState.rem_coins -= take_coins;
                  System.out.println("Player took "+ String.valueOf(take_coins) + " coins\n" + nextState);
                  break;
                  
              case 0: //Computer
                  // The printState is only used for printing the computer's move
                  StateNim printState = (StateNim) game.currentState;
            	  nextState = (StateNim) search.bestSuccessorState(depth);
            	  nextState.player = 0;
                  take_coins =  printState.rem_coins - nextState.rem_coins;
            	  System.out.println("Computer took " + String.valueOf(take_coins) + " coins\n" + nextState);
                  break;
            }
                        
            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player== 0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("Player must take the last coin. Computer wins!");
            	else
            		System.out.println("Computer must take the last coin. You win!");
            	break;
            }

            if ( game.isStuckState(game.currentState) ) {
                
                if (game.currentState.player == 0) {
                    System.out.println("Player unnecessarily took the last coin. Computer wins!");
                } else {
                    // This statement should never be printed.
                    System.out.println("Computer unnecessarily took the last coin. You win!");
                }
            	break;
            }
        }
    }
}