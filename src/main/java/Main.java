import exceptions.generator.GeneratorException;
import exceptions.generator.GrammarParserException;
import generator.Generator;

import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws GrammarParserException, GeneratorException {
        Generator generator = new Generator(args[0], Paths.get(args[1]));
        generator.generate();
    }
}
