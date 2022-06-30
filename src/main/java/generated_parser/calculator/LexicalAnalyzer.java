package generated_parser.calculator;

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

    private Set<Character> skipChars = new HashSet<>(Arrays.asList('\t', ' ', '\n', '\r'));
    private List<NameToken> terms = new ArrayList<>(Arrays.asList(new NameToken("-", Token.C_45),new NameToken("+", Token.C_43),new NameToken("/", Token.C_47),new NameToken("*", Token.C_42),new NameToken("!", Token.C_33),new NameToken("(", Token.C_40),new NameToken(")", Token.C_41),new NameToken("0", Token.C_48),new NameToken("1", Token.C_49),new NameToken("2", Token.C_50),new NameToken("3", Token.C_51),new NameToken("4", Token.C_52),new NameToken("5", Token.C_53),new NameToken("6", Token.C_54),new NameToken("7", Token.C_55),new NameToken("8", Token.C_56),new NameToken("9", Token.C_57)));

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