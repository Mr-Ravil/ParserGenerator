package exceptions.generator;

public class GrammarParserException extends Exception {
    public GrammarParserException(String message) {
        super(message);
    }

    public GrammarParserException(String message, Exception e) {
        super(message, e);
    }

    static protected String markerOfException(int beginOfException, int sizeOfException) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < beginOfException; i++) {
            result.append(' ');
        }
        result.append('^');
        for (int i = 1; i < sizeOfException; i++) {
            result.append('~');
        }
        return result.toString();
    }

}
