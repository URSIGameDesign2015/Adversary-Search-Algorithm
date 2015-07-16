package regex.matcher;

public class RegularExpression implements Visualizable {

        /* Special characters */
        final public static char UNION_CHAR='|';
        final public static char KLEENE_CLOSURE_CHAR='*';
        final public static char ANY_CHAR='.';
        final public static char EMPTY_CHAR='ε';
        final public static char START_PARENTHESIS='(';
        final public static char END_PARENTHESIS=')';

        private String regex;

        private Alphabet alphabet;

        private Node root;

        private Parser p;

        /**
         * Constructs regular expression parse tree from regular expression string representation.
         * 
         * @param regex string representation
         */
        public RegularExpression(String regex) {
                this.regex = regex;
                this.setAlphabet(new Alphabet());
        }

        /**
         * Constructs regular expression parse tree from string representation.
         * 
         * @return parse tree root node
         */
        public Node parse() {
                p = new Parser(this);
                root = p.parse();
                return root;
        }

        /**
         * Build NFA from current regular expression 
         *  
         * @param strategy {@link RegexToNFAStrategy} to use when constructing regular expression
         * 
         * @return constructed {@link NFA}
         */
        public NFA toAutomaton(RegexToNFAStrategy strategy){
                NFA nfa = getRoot().buildNFA(strategy);
                
                nfa.recalculateStateNumbers();
                
                return nfa;
        }

        @Override
        public String toString() {
                return getRoot().toString();
        }

        /**
         * Return Graphviz dot representation of regular expression tree
         * 
         * @return Graphviz dot representation
         */
        public String toDot() {
                StringBuilder b = new StringBuilder("digraph RegularExpression {\n");
                b.append("  rankdir = LR;\n");
                b.append(getRoot().toDot());
                return b.append("}\n").toString();
        }
        
        private Node getRoot() {
                if (root == null) {
                        throw new IllegalStateException("Regular expression must be parsed before invoking any operations on it.");
                }
                return root;
        }

        /**
         * Get regular expression as string.
         * 
         * @return string representation of this regex
         */
        public String getRegex(){
                return regex;
        }

        public Alphabet getAlphabet() {
                return alphabet;
        }

        public void setAlphabet(Alphabet alphabet) {
                this.alphabet = alphabet;
        }

}