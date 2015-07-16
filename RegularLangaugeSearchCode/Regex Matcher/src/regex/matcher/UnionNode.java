package regex.matcher;

import java.util.Set;

/**
 * Regex tree node representing union
 *
 */
public class UnionNode extends Node {
        
        private Node left;
        private Node right;

        public UnionNode(Node left, Node right) {
                setLeft(left);
                setRight(right);
        }

        @Override
        protected NFA toNFAGlushkov() {
                NFA leftAutomaton = left.toNFAGlushkov();
                NFA rightAutomaton = right.toNFAGlushkov();
                NFA unionAutomaton = new NFA();
                unionAutomaton.setAlphabet(alphabet.getAlphabet());
                State leftStart = leftAutomaton.getInitialState();
                State rightStart = rightAutomaton.getInitialState();
                
                //1. Add all states from left and right automaton except right start state
                unionAutomaton.addStates(leftAutomaton.getStates());
                for(State state : rightAutomaton.getStates()) {
                        if(state != rightStart) {
                                unionAutomaton.addState(state);
                        }
                }
                
                //2. Add right start state transitions to new start state
                for(Transition transition : rightStart.getOutgoing()) {
                        transition.setFrom(leftStart);
                }
                unionAutomaton.setInitialState(leftStart);
                return unionAutomaton;
        }

        @Override
        protected NFA toNFAThompson() {
                NFA automaton = new NFA();
                automaton.setAlphabet(alphabet.getAlphabet());
                //1. add start state
                State start = new State();
                automaton.addState(start);
                automaton.setInitialState(start);
                
                //1. add all existing states (and transitions) to new automaton
                NFA leftAutomaton = left.toNFAThompson();
                automaton.addStates(leftAutomaton.getStates());
                NFA rightAutomaton = right.toNFAThompson();
                automaton.addStates(rightAutomaton.getStates());
                
                //2. add end state to automaton
                State end = new State(true);
                automaton.addState(end);
                
                // 2. connect old start states to new start state
                new Transition(start, leftAutomaton.getInitialState(), TransitionType.EPSILON);
                new Transition(start, rightAutomaton.getInitialState(), TransitionType.EPSILON);
                
                // 2. connect old end states to new end state
                Set<State> finalStates = leftAutomaton.getAcceptStates();
                finalStates.addAll(rightAutomaton.getAcceptStates());
                for (State state : finalStates) {
                        state.setAccept(false);
                        new Transition(state, end, TransitionType.EPSILON);
                }                       
                
                return automaton;
        }

        @Override
        public String toDot() {
                StringBuilder sb = new StringBuilder();
                sb.append(id+ " [shape=circle,label=\""+RegularExpression.UNION_CHAR+"\"]\n");

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
                return left.toString()+RegularExpression.UNION_CHAR+right.toString();
        }
        
}
