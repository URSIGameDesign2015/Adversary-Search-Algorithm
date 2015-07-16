package regex.matcher;

/**
 * Finite state automata transition representation
 *
 */
public class Transition implements Visualizable {
        
        private State from;
        private State to;
        
        private Character character;
        
        private TransitionType type;
        
        
                
        public Transition(State from, State to, TransitionType type) {
                this(from, to, type, null);
        }
        
        public Transition(State from, State to, TransitionType type, Character character) {
                setFrom(from);
                setTo(to);
                
                this.type = type;
                this.character = character;
        }

        
        public State getFrom() {
                return from;
        }
        
        public void setFrom(State from) {
                this.from = from;
                
                from.addOutgoing(this);
        }
        
        
        public State getTo() {
                return to;
        }
        
        public void setTo(State to) {
                this.to = to;
        }
        

        public Character getCharacter() {
                return character;
        }

        public void setCharacter(Character character) {
                this.character = character;
        }

        
        public TransitionType getType() {
                return type;
        }

        public void setType(TransitionType type) {
                this.type = type;
        }
        
        public boolean isCharacter() {
                return TransitionType.CHARACTER.equals(type);
        }
        
        public boolean isEpsilon() {
                return TransitionType.EPSILON.equals(type);
        }
        
        public boolean isAlphabet() {
                return TransitionType.ALPHABET.equals(type);
        }
        
        
        @Override
        public String toString() {
                return new StringBuilder("Transition: ").
                        append("From - ").append(from).
                        append(", To - ").append(to).
                        append(", Type - ").append(type).
                        append(", Character - ").append(character).
                        toString();
        }

        
        public String toDot() {
                if(isEpsilon()) {
                        return from.getNumber() + " -> " + to.getNumber() + "[label=\"ε\",style=\"dotted\"]\n";
                }
                
                if(isAlphabet()) {
                        return from.getNumber() + " -> " + to.getNumber() + "[label=\"Σ\"]\n";
                }
                
                return from.getNumber() + " -> " + to.getNumber() + "[label=\"" + character + "\"]\n";
        }
}
