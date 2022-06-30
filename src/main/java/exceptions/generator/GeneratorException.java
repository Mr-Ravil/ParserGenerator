package exceptions.generator;

public class GeneratorException extends Exception {
    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Exception e) {
        super(message, e);
    }

}

