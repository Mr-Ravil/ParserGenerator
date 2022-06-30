package exceptions.parser;

public class UnknownActionException extends ParserException {
    public UnknownActionException(String action, int startOfException) {
        super("Unknown action \"" + action + "\" at position " + startOfException + "\n");
    }
}
