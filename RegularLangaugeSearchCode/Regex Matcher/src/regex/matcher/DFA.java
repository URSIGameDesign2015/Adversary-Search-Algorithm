package regex.matcher;

/**
 * Deterministic finite automaton
 *
 */
public class DFA extends Automaton {

        public State getState(State state, Character character) {
                for(Transition transition : state.getOutgoing()) {
                        if(transition.isCharacter() && 
                                        transition.getCharacter().equals(character)) {
                                return transition.getTo();
                        } 
                }               
                return null;
        }

}