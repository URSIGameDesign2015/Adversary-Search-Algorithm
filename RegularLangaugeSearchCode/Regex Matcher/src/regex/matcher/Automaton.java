package regex.matcher;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Finite state automaton base class
 *
 */
public abstract class Automaton implements Visualizable {

        protected Set<State> states = new LinkedHashSet<State>();
        
        protected Set<Character> alphabet;
        
        protected State initialState;
        
        private long stateNumber = -1;

        
        public Set<State> getStates() {
                return states;
        }
        
        /**
         * Add a new state to automaton
         * 
         * @param state state to add. Must not exist in automaton.
         */
        public void addState(State state) {
                if(states.contains(state)){
                        throw new RuntimeException("Trying to add existing state!");
                }
                states.add(state);
                if(state.getNumber() == null) {
                        state.setNumber(++stateNumber);
                }
        }
        
        /**
         * Add multiple states to automaton
         * 
         * @param states Set of states. Must not exist in automaton.
         */
        public void addStates(Set<State> states) {
                for(State state : states) {
                        addState(state);
                }
        }

        
        public Set<Character> getAlphabet() {
                return alphabet;
        }

        public void setAlphabet(Set<Character> alphabet) {
                this.alphabet = alphabet;
        }
        

        public State getInitialState() {
                return initialState;
        }

        /**
         * Mark existing state as initial
         * 
         * @param initialState initial state. Must exist in automaton.
         */
        public void setInitialState(State initialState) {
                if(!states.contains(initialState)) {
                        throw new RuntimeException("Add state to automaton before setting it to be initial");
                }
                this.initialState = initialState;
        }

        
        /**
         * @return all accept states in automaton
         */
        public Set<State> getAcceptStates() {
                Set<State> acceptStates = new LinkedHashSet<State>();
                for(State state : states) {
                        if(state.isAccept()) {
                                acceptStates.add(state);
                        }
                }
                return acceptStates;
        }
        
        
        public void recalculateStateNumbers() {
                stateNumber = -1;
                for(State state : states) {
                        state.setNumber(++stateNumber);
                }
        }
        
        
        
        public String toDot() {
                StringBuilder sb = new StringBuilder("digraph Automaton {\n");
                sb.append("  rankdir = LR;\n");
                sb.append("Start " + " -> " + initialState.getNumber() + "\n");
                for(State state : states) {
                        sb.append(state.toDot());
                }
                return sb.append("}\n").toString();
        }
        
}