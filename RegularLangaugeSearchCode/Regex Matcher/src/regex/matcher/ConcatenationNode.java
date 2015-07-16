package regex.matcher;

public class ConcatenationNode extends Node {
        
        private Node left;
        private Node right;

        public ConcatenationNode(Node left, Node right) {
                this.setLeft(left);
                this.setRight(right);
        }

        @Override
        protected NFA toNFAGlushkov() {
                NFA automaton = new NFA();
                automaton.setAlphabet(alphabet.getAlphabet());          
                NFA leftAutomaton = left.toNFAGlushkov();
                NFA rightAutomaton = right.toNFAGlushkov();
                automaton.addStates(leftAutomaton.getStates());
                automaton.setInitialState(leftAutomaton.getInitialState());
                for(State state : leftAutomaton.getAcceptStates()) {
                        if(!rightAutomaton.getInitialState().isAccept()) {
                                state.setAccept(false);
                        }
                        
                        for(Transition transition : rightAutomaton.getInitialState().getOutgoing()) {
                                new Transition(state, transition.getTo(), transition.getType(), transition.getCharacter());
                                transition = null;
                        }
                }
                for(State state : rightAutomaton.getStates()) {
                        if(state != rightAutomaton.getInitialState()) {
                                automaton.addState(state);
                        }
                }
                return automaton;
        }

        @Override
        protected NFA toNFAThompson() {
                NFA automaton = new NFA();
                automaton.setAlphabet(alphabet.getAlphabet());
                NFA leftAutomaton = left.toNFAThompson();
                NFA rightAutomaton = right.toNFAThompson();
                
                //1. add all existing states (and transitions) to new automaton and set initial state
                automaton.addStates(leftAutomaton.getStates());
                automaton.addStates(rightAutomaton.getStates());
                automaton.setInitialState(leftAutomaton.getInitialState());
                
                // 2. connect right automaton end states to left automaton start state
                for(State end : leftAutomaton.getAcceptStates()) {
                        end.setAccept(false);
                        new Transition(end, rightAutomaton.getInitialState(), TransitionType.EPSILON);
                }
                
                return automaton;
        }

        @Override
        public String toDot() {
                StringBuilder sb = new StringBuilder();
                sb.append(id+ " [shape=circle,label=\""+"&"+"\"]\n");
                
                sb.append(id+ " -> "+right.id+"\n");
                sb.append(right.toDot());
                
                sb.append(id+ " -> "+left.id+"\n");
                sb.append(left.toDot());

                
                return sb.toString();
        }

        public void setLeft(Node left) {
                this.left = left;
        }

        public Node getLeft() {
                return left;
        }

        public void setRight(Node right) {
                this.right = right;
        }

        public Node getRight() {
                return right;
        }

        @Override
        public String toString() {
                return left.toString()+right.toString();
        }
        
}