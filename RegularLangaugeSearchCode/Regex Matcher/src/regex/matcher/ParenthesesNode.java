package regex.matcher;

/**
 * Regex tree node representing parentheses
 *
 */
public class ParenthesesNode extends Node {
        private Node node;

        public ParenthesesNode(Node node) {
                this.node = node;
        }

        @Override
        public String toDot() {
                StringBuilder sb = new StringBuilder();
                sb.append(id+ " [shape=circle,label=\""+RegularExpression.START_PARENTHESIS+RegularExpression.END_PARENTHESIS+"\"]\n");
                
                sb.append(id+ " -> "+node.id+"\n");
                sb.append(node.toDot());
                return sb.toString();
        }

        @Override
        protected NFA toNFAGlushkov() {
                return node.toNFAGlushkov();
        }

        @Override
        protected NFA toNFAThompson() {
                return node.toNFAThompson();
        }

        @Override
        public String toString() {
                return RegularExpression.START_PARENTHESIS+node.toString()+RegularExpression.END_PARENTHESIS;
        }

        public void setNode(Node node) {
                this.node = node;
        }

        public Node getNode() {
                return node;
        }
}
