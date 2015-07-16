package regex.matcher;

/**
 * Regex tree node containing symbol
 *
 */
public class SymbolNode extends Node {
        private char symbol;

        public SymbolNode(char symbol) {
                this.setSymbol(symbol);
        }

        @Override
        protected NFA toNFAGlushkov() {
                return toNFA();
        }

        @Override
        protected NFA toNFAThompson() {
                return toNFA();
        }
        
        private NFA toNFA() {
                NFA automaton = new NFA();
                automaton.setAlphabet(alphabet.getAlphabet());
                State start = new State();
                automaton.addState(start);
                automaton.setInitialState(start);
                State end = new State(true);
                automaton.addState(end);
                if(symbol == RegularExpression.EMPTY_CHAR) {
                        new Transition(start, end, TransitionType.EPSILON);
                }
                else if(symbol == RegularExpression.ANY_CHAR) {
                        new Transition(start, end, TransitionType.ALPHABET);
                }
                else {
                        new Transition(start, end, TransitionType.CHARACTER, symbol);
                }
                return automaton;
        }

        public void setSymbol(char symbol) {
                this.symbol = symbol;
        }

        public char getSymbol() {
                return symbol;
        }

        @Override
        public String toString() {
                return String.valueOf(symbol);
        }

        @Override
        public String toDot() {
                StringBuilder sb = new StringBuilder();
                sb.append(id+ " [shape=circle,label=\""+symbol+"\"]\n");
                return sb.toString();
        }
}