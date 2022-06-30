import exceptions.parser.ParserException;
import generated_parser.calculator.Parser;
import org.junit.Test;
import tree.Tree;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Parser parser = new Parser();

    @Test
    public void testSimple() throws ParserException {
        String text = "5+8";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 13);
    }

    @Test
    public void test1() throws ParserException {
        String text = "5-8";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, -3);
    }

    @Test
    public void test2() throws ParserException {
        String text = "5*8";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 40);
    }

    @Test
    public void test3() throws ParserException {
        String text = "5/8";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 0);
    }

    @Test
    public void test4() throws ParserException {
        String text = "40/5";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 8);
    }

    @Test
    public void test5() throws ParserException {
        String text = "654*1232+124/2+(-4)-56*(80/3)";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 804330);
    }

    @Test
    public void test6() throws ParserException {
        String text = "5*(5-2)";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 15);
    }

    @Test
    public void test7() throws ParserException {
        String text = "8*0";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(tree.result, 0);
    }

    @Test
    public void testFactorial() throws ParserException {
        String text = "0!";
        Parser.sTree tree = (Parser.sTree) parser.parse(text);
        assertEquals(1, tree.result);

        text = "1!";
        tree = (Parser.sTree) parser.parse(text);
        assertEquals(1, tree.result);

        text = "5!";
        tree = (Parser.sTree) parser.parse(text);
        assertEquals(120, tree.result);

        text = "(-4!)";
        tree = (Parser.sTree) parser.parse(text);
        assertEquals(-24, tree.result);

        text = "654*1232+124/2+(-4!)";
        tree = (Parser.sTree) parser.parse(text);
        assertEquals(805766, tree.result);


        text = "3! * 5!";
        tree = (Parser.sTree) parser.parse(text);
        assertEquals( 720, tree.result);


        text = "3!!";
        tree = (Parser.sTree) parser.parse(text);
        assertEquals( 720, tree.result);
    }

}
