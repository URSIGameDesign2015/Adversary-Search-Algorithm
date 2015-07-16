package regex.matcher;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Finite state automata state representation
 *
 */
public class State implements Visualizable {
        
        private Long number;
        
        private boolean accept;

        private Set<Transition> outgoing = new LinkedHashSet<Transition>();
        
        /**
         * Create new state
         * 
         * @param accept if true, state is accept state
         */
        public State(boolean accept) {
                this();
                this.accept = true;
        }
        
        /**
         * Create new state
         */
        public State() {}
        
        
        public Long getNumber() {
                return number;
        }

        public void setNumber(Long number) {
                this.number = number;
        }
        
        
        public boolean isAccept() {
                return accept;
        }

        public void setAccept(boolean accept) {
                this.accept = accept;
        }
        
        public Set<Transition> getOutgoing() {
                return outgoing;
        }

        public void setOutgoing(Set<Transition> outgoing) {
                this.outgoing = outgoing;
        }
        
        public void addOutgoing(Transition transition) {
                outgoing.add(transition);
        }
        
        

        @Override
        public String toString() {
                return new StringBuilder("State: ").
                        append(number).
                        toString();
        }

        
        public String toDot() {
                StringBuilder sb = new StringBuilder().
                        append(number).
                        append(" [shape=").
                        append(accept ? "doublecircle" : "circle").
                        append(",label=\"").
                        append(number).
                        append("\"]\n");
                
                for(Transition t : getOutgoing()) {
                        sb.append(t.toDot());
                }
                
                return sb.toString();
        }
        

}
