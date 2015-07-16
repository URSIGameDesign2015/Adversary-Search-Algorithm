package regex.matcher;

public class Parser {

        private RegularExpression regularExpression;
        
        private NodeBuilder nodeBuilder;

        private int index;
        
        public Parser(RegularExpression regularExpression) {
                this.regularExpression = regularExpression;
                nodeBuilder = new NodeBuilder(regularExpression.getAlphabet());
        }
        
        private String getRegex() {
                return regularExpression.getRegex();
        }
        
        private void move() {
                index++;
        }
        
        private char next() {
                if(more()) {
                        return getRegex().charAt(index);
                }
                throw new RuntimeException("Unexpected end of regex");
        }
        
        private boolean more() {
                return index < getRegex().length();
        }
        
        private boolean match(char c) {
                return more() ? getRegex().charAt(index) == c : false;
        }
        
        private boolean matchAndMove(char c) {
                if (match(c)) {
                        move();
                        return true;
                }
                return false;
        }
        
        private boolean isInAlphabet() {
                return regularExpression.getAlphabet().checkAlphabet(getRegex().charAt(index));
        }
        
        private boolean isSpecialChar() {
                return regularExpression.getAlphabet().checkIsReserved(getRegex().charAt(index));
        }

        public Node parse() {
                return parseUnion();
        }
        
        private Node parseUnion() {
        Node node = parseConcatenation();
        if (matchAndMove(RegularExpression.UNION_CHAR))
            node = nodeBuilder.createUnionNode(node, parseUnion());
        return node;
                
        }
        
        private Node parseConcatenation() {
                Node node = parseKleeneClosure();
                if(more() && ( next() != RegularExpression.END_PARENTHESIS && next() != RegularExpression.UNION_CHAR) ) {
                        node = nodeBuilder.createConcatenationNode(node, parseConcatenation());
                }
                return node;
                
        }

        private Node parseKleeneClosure() {
                Node node = parseSymbol();
                if(more() && matchAndMove(RegularExpression.KLEENE_CLOSURE_CHAR)) {
                        node = nodeBuilder.createKleeneClosureNode(node);
                }
                if(match(RegularExpression.KLEENE_CLOSURE_CHAR)){
                        throw new RuntimeException("Unexpected character");
                }
                return node;
                
        }
        
        private Node parseSymbol() {
                char c = getRegex().charAt(index);
                if(matchAndMove(RegularExpression.ANY_CHAR)) {
                        return nodeBuilder.createSymbolNode(RegularExpression.ANY_CHAR);
                }
                else if (matchAndMove(RegularExpression.EMPTY_CHAR)) {
                        return nodeBuilder.createSymbolNode(RegularExpression.EMPTY_CHAR);
                }
                else if (matchAndMove(RegularExpression.START_PARENTHESIS)) {
                        Node node = parseUnion();
                        if(!matchAndMove(RegularExpression.END_PARENTHESIS)) {
                                throw new RuntimeException("Expected "+RegularExpression.END_PARENTHESIS+" instead of "+c+" in position "+index);
                        }
                        node = nodeBuilder.createParenthesesNode(node);
                        return node;
                }
                else if (isSpecialChar()) {
                        throw new RuntimeException("Illegal character");
                }
                else if (!isInAlphabet()) {
                        throw new RuntimeException("Character not in alphabet");
                }
                else {
                        move();
                        return nodeBuilder.createSymbolNode(c);
                }                       
        }
}
