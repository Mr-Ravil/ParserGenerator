package labs.calculator;

import exceptions.parser.ParserException;
import generated_parser.calculator.Parser;
import tree.Tree;

import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws ParserException {

        Parser parser = new Parser();

        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        System.out.println(tree.result);

    }
}
