package labs.lab2;

import exceptions.parser.ParserException;
import generated_parser.grammarPython.Parser;
import tree.Tree;

import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) throws ParserException {

        Visualizer visualizer = new Visualizer();
        Parser parser = new Parser();


        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        String name = in.nextLine();
        Tree tree = parser.parse(text);
        visualizer.visualize(tree, name);

//        Tree tree1 = parser.parse("(a in b) and (b not in a) xor not (d in f)");
//
//        visualizer.visualize(tree1, "testSimple");
//
//        Tree tree2 = parser.parse("             (\n        a in b         ) \r  and (b not \r\r\r in \n\n\n a) xor                           not (d in f)");
//
//        visualizer.visualize(tree2, "testWhiteSpace");

    }
}
