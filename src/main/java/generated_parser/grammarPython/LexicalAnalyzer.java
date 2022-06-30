package generated_parser.grammarPython;

import exceptions.parser.*;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalAnalyzer {
    private String text;
    private int point;

    private Token curToken;
    private String name;

    private Set<Character> skipChars = new HashSet<>(Arrays.asList('\t', '\r', ' ', '\n'));
    private List<NameToken> terms = new ArrayList<>(Arrays.asList(new NameToken("xor", Token.H_2),new NameToken("or", Token.H_3),new NameToken("and", Token.H_4),new NameToken("not", Token.H_5),new NameToken("(", Token.C_40),new NameToken(")", Token.C_41),new NameToken("in", Token.H_6),new NameToken("a", Token.C_97),new NameToken("b", Token.C_98),new NameToken("c", Token.C_99),new NameToken("d", Token.C_100),new NameToken("e", Token.C_101),new NameToken("f", Token.C_102),new NameToken("g", Token.C_103),new NameToken("h", Token.C_104),new NameToken("i", Token.C_105),new NameToken("j", Token.C_106),new NameToken("k", Token.C_107),new NameToken("l", Token.C_108),new NameToken("m", Token.C_109),new NameToken("n", Token.C_110),new NameToken("o", Token.C_111),new NameToken("p", Token.C_112),new NameToken("q", Token.C_113),new NameToken("r", Token.C_114),new NameToken("s", Token.C_115),new NameToken("t", Token.C_116),new NameToken("u", Token.C_117),new NameToken("v", Token.C_118),new NameToken("w", Token.C_119),new NameToken("x", Token.C_120),new NameToken("y", Token.C_121),new NameToken("z", Token.C_122)));

    public Token getCurrentToken() {
        return curToken;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    private void skipWhiteSpace() {
        while (point < text.length() && skipChars.contains(text.charAt(point))) {
            point++;
        }
    }

    public void nextToken() throws ParserException {
        skipWhiteSpace();
        if (point >= text.length()) {
            curToken = Token.$;
            return;
        }

        for (NameToken term : terms) {
            int i = 0;
            while (i < term.name.length() && i + point < text.length() && term.name.charAt(i) == text.charAt(point + i)) {
                i++;
            }
            if (i == term.name.length()) {
                point += i;
                curToken = term.token;
                name = term.name;
                return;
            }
        }
        throw new UnknownActionException(String.valueOf(text.charAt(point)), point);
    }

    public LexicalAnalyzer(String newText) {
        point = 0;
        text = newText;
        curToken = Token.BEGIN;
    }

    private static class NameToken{
        String name;
        Token token;

        public NameToken(String name, Token token) {
            this.name = name;
            this.token = token;
        }
    }

}