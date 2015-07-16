package regex.matcher;

public class NodeBuilder {
        
        private Alphabet alphabet;

        private int id = 0;

        public NodeBuilder(Alphabet alphabet) {
                this.alphabet = alphabet;
        }

        public Node createSymbolNode(char symbol) {
                return initNode(new SymbolNode(symbol));
        }

        public Node createParenthesesNode(Node childNode) {
                return initNode(new ParenthesesNode(childNode));
        }
        
        public Node createUnionNode(Node leftChild, Node rightChild) {
                return initNode(new UnionNode(leftChild, rightChild));
        }
        
        public Node createConcatenationNode(Node leftChild, Node rightChild) {
                return initNode(new ConcatenationNode(leftChild, rightChild));
        }
        
        public Node createKleeneClosureNode(Node childNode) {
                return initNode(new KleeneClosureNode(childNode));
        }
        
        private Node initNode(Node node) {
                node.setId(id++);
                node.setAlphabet(alphabet);
                return node;
        }
}
