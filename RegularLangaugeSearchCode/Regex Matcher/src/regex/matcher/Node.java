package regex.matcher;

public abstract class Node implements Visualizable {

        /** unique node identifier */
        protected int id;
        
        protected Alphabet alphabet;

        public NFA buildNFA(RegexToNFAStrategy strategy) {
        switch (strategy) {
                case THOMPSON:
                        return toNFAThompson();
                case GLUSHKOV:
                        return toNFAGlushkov();
        }
        throw new IllegalArgumentException("The strategy " + strategy + " is not recognized.");
        }

        /**
         * Build Glushkov {@link NFA} from regular expression
         *  
         * @return {@link NFA}
         */
        protected abstract NFA toNFAGlushkov();

        /**
         * Build Thompson {@link NFA} from regular expression
         *  
         * @return {@link NFA}
         */
        protected abstract NFA toNFAThompson();

        public abstract String toDot();

        @Override
        public abstract String toString();

        public void setId(int id) {
                this.id = id;
        }

        public int getId() {
                return id;
        }

        public Alphabet getAlphabet() {
                return alphabet;
        }

        public void setAlphabet(Alphabet alphabet) {
                this.alphabet = alphabet;
        }
}
