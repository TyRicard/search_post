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
        int depth = 13;
        
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	
        	StateNim nextState = null;
        	
            switch ( game.currentState.player ) {
              case 1: //Human
                  
            	  //get human's move
                  System.out.print("Enter your *valid* move> ");
                  int take_coins = Integer.parseInt( in.readLine() );
            	  
                  nextState = new StateNim( (StateNim) game.currentState);
                  nextState.player = 1;
                  nextState.rem_coins -= take_coins;
                  System.out.println("Human: \n" + nextState);
                  break;
                  
              case 0: //Computer
            	  
            	  nextState = (StateNim) search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("Computer: \n" + nextState);
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
                
                if (game.currentState.player == 1) {
                    System.out.println("Player unnecessarily took the last coin. Computer wins!");
                } else {
                    System.out.println("Computer unnecessarily took the last coin. You win!");
                }
            	break;
            }
        }
    }
}