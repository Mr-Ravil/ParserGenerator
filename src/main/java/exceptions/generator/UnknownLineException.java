package exceptions.generator;

public class UnknownLineException extends GrammarParserException {
    public UnknownLineException(String line, int lineNumber, int columnNumber) {
        super("Unknown line \"" + line + "\" at line " + lineNumber + " at column " + columnNumber);
    }

    public UnknownLineException(String line, int lineNumber, int columnNumber, String addInf) {
        super("Unknown line \"" + line + "\" at line " + lineNumber + " at column " + columnNumber + ". " + addInf);
    }
}
