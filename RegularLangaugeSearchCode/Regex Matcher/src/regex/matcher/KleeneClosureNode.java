package regex.matcher;

/**
 * Regex tree node representing Kleene closure
 *
 */
public class KleeneClosureNode extends Node {

        private Node node;

        public KleeneClosureNode(Node node) {
                this.setNode(node);
        }

        @Override
        protected NFA toNFAGlushkov() {
                NFA automaton = node.toNFAGlushkov();
                automaton.getInitialState().setAccept(true);
                for(State state : automaton.getAcceptStates()) {
                        for(Transition transition : automaton.getInitialState().getOutgoing()) {
                                if(!state.getOutgoing().contains(transition)) {
                                        new Transition(state, transition.getTo(), transition.getType(), transition.getCharacter());
                                }
                        }
                }
                return automaton;
        }

        @Override
        protected NFA toNFAThompson() {
                NFA automaton = new NFA();
                automaton.setAlphabet(alphabet.getAlphabet());

                //1. Add new start state
                State start = new State();
                automaton.addState(start);
                automaton.setInitialState(start);
                //2. Add all existing states (and transitions)
                NFA childAutomaton = node.toNFAThompson();
                automaton.addStates(childAutomaton.getStates());
                //3. Add new end state
                State end = new State(true);
                automaton.addState(end);
                //2. Add new transitions
                new Transition(start, childAutomaton.getInitialState(), TransitionType.EPSILON);
                new Transition(start, end, TransitionType.EPSILON);
                for(State state : childAutomaton.getAcceptStates()) {
                        state.setAccept(false);
                        new Transition(state, childAutomaton.getInitialState(), TransitionType.EPSILON);
                        new Transition(state, end, TransitionType.EPSILON);
                }
                return automaton;
        }

        @Override
        public String toDot() {
                StringBuilder sb = new StringBuilder();
                sb.append(id+ " [shape=circle,label=\""+RegularExpression.KLEENE_CLOSURE_CHAR+"\"]\n");
                
                sb.append(id+ " -> "+node.id+"\n");
                sb.append(node.toDot());
                return sb.toString();
        }

        @Override
        public String toString() {
                return node.toString()+RegularExpression.KLEENE_CLOSURE_CHAR;
        }

        public void setNode(Node node) {
                this.node = node;
        }

        public Node getNode() {
                return node;
        }
}
