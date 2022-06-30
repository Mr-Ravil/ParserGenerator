package exceptions.parser;

public class UnexpectedTokenException extends ParserException {
    public UnexpectedTokenException(String action, int startOfException) {
        super("Unexpected token \"" + action + "\" at position " + startOfException + "\n");
    }
}
