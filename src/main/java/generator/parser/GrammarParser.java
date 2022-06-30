package generator.parser;

import exceptions.generator.GrammarParserException;
import exceptions.generator.UnknownLineException;
import generator.token.*;

import java.util.*;

public class GrammarParser {
    public List<Tokenizer> tokens;
    public Map<String, Tokenizer> tokensId;

    public Term EPS;
    public Term END;
    public Tokenizer SKIP;
    public Tokenizer START;

    private int termCount;

    private String grammar;
    private int point;
    private Position position;

    public GrammarParser(String grammar) throws GrammarParserException {
//        tokens = new ArrayList<>(Collections.<Token>nCopies(4, null));
        tokens = new ArrayList<>();
        tokensId = new HashMap<>();

        EPS = new Term(0, "eps", "");
        tokens.add(EPS);
        tokensId.put("eps", EPS);

        END = new Term(1, "$", "");
        tokens.add(END);
        tokensId.put("$", END);

        SKIP = new TToken(0, "skip");
        tokens.add(SKIP);
        tokensId.put("skip", SKIP);

        termCount = 2;
        this.grammar = grammar;
        point = 0;
        position = new Position();
        parse();
    }

    private void skipWhiteSpace() {
        while (point < grammar.length() && Character.isWhitespace(grammar.charAt(point))) {
            if (grammar.charAt(point) == '\n')
                position.update(point + 1);
            ++point;
        }
    }

    private char getChar() {
        return grammar.charAt(point);
    }

    private String getWord() {
        skipWhiteSpace();
        int leftEdge = point;
        while (point < grammar.length()
                && (Character.isLetter(getChar()))
                || Character.isDigit(getChar())) {
            ++point;
        }
        return grammar.substring(leftEdge, point);
    }

    private String getCode(boolean isFigureBrace) throws GrammarParserException {
        char openBrace = isFigureBrace ? '{' : '[';
        char closeBrace = isFigureBrace ? '}' : ']';

        skipWhiteSpace();
        if (getChar() != openBrace) {
            throw new GrammarParserException(openBrace + " expected at line " + position.line + " at column  " + (point - position.pastPoint));
        }

        int leftEdge = ++point;
        int balance = 1;
        while (point < grammar.length()
                && balance != 0) {
            if (getChar() == openBrace)
                ++balance;
            else if (getChar() == closeBrace)
                --balance;
            ++point;
        }

        if (point == grammar.length()) {
            throw new GrammarParserException("\'" + closeBrace + "\' expected at position " + point +
                    " or earlier to close \'" + openBrace + "\' at line " + position.line + " at column  " + (leftEdge - 1 - position.pastPoint));
        }

        return grammar.substring(leftEdge, point - 1);
    }

    private char getShieldedChar(char c) {
        switch (c) {
            case 't':
                return '\t';
            case 'b':
                return '\b';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 'f':
                return '\f';
            default:
                return c;


        }
    }

    private String getTermString() {
        ++point;
        StringBuilder result = new StringBuilder();
        while (point < grammar.length()
                && getChar() != '\'') {
            char app = getChar();
            if (getChar() == '\\') {
                ++point;
                app = getShieldedChar(getChar());
            }
            result.append(app);
            ++point;
        }
        ++point;
        return result.toString();
    }

    private Tokenizer getTermToken(String termName) {
        if (termName.length() == 1) {
            if (tokensId.containsKey(termName)) {
                return tokensId.get(termName);
            }
            String name = "C_" + Integer.toString(termName.charAt(0));
            Tokenizer term = new Term(tokens.size(), name, termName);
            tokens.add(term);
            tokensId.put(termName, term);
            return term;
        } else {
            if (tokensId.containsKey(termName)) {
                return tokensId.get(termName);
            }
            String name = "H_" + termCount++;
            Tokenizer term = new Term(tokens.size(), name, termName);
            tokens.add(term);
            tokensId.put(termName, term);
            return term;
        }
    }

    private Tokenizer getSqTerm() {
        ++point;
        boolean exist = getChar() != '^';
        if (!exist) {
            ++point;
        }
        List<Boolean> result = new ArrayList<>(Collections.nCopies(256, !exist));
        while (getChar() != ']') {
            char start = getChar();
            if (getChar() == '\\') {
                ++point;
                start = getShieldedChar(getChar());
            }
            result.set(start, exist);

            ++point;
            if (getChar() != '-')
                continue;

            ++point;
            char end = getChar();
            if (getChar() == '\\') {
                ++point;
                end = getShieldedChar(getChar());
            }
            while (start++ != end) {
                result.set(start, exist);
            }
            ++point;
        }
        ++point;

        TToken token = new TToken(tokens.size(), "T_" + tokens.size());
        tokens.add(token);

        for (int i = 0; i < 256; i++) {
            if (result.get(i)) {
                token.addRule(new Rule(new Rule.Child(getTermToken(Character.toString(i)))));
            }
        }
        return token;
    }

    private TToken getTToken(String tokenName) {
        if (!tokensId.containsKey(tokenName)) {
            TToken token = new TToken(tokens.size(), tokenName);
            tokens.add(token);
            tokensId.put(tokenName, token);
            return token;
        }

        return (TToken) tokensId.get(tokenName);
    }

    private void parseToken() throws GrammarParserException {
        String tokenName = getWord();
        boolean isSkip = false;
        if (getChar() == '~') {
            isSkip = true;
            ++point;
        }

        skipWhiteSpace();

        if (getChar() != ':') {
            throw new UnknownLineException(tokenName, position.line, point - tokenName.length() - position.pastPoint,
                    "If it is a token, it is expected after the name \':\'");
        }
        ++point;

        if (isSkip) {
            tokenName = "skip";
        }

        TToken token = getTToken(tokenName);

        Rule rule = new Rule();
        token.addRule(rule);
        skipWhiteSpace();
        while (getChar() != ';') {
            switch (getChar()) {
                case '|':
                    if (isSkip && rule.children.size() != 1) {
                        throw new GrammarParserException("Skip tokens can have only char. Check it out at line "
                                + position.line + " at column  " + (point - position.pastPoint));
                    }
                    if (rule.children.isEmpty()) {
                        rule.addChild(new Rule.Child(EPS));
                    }
                    rule = new Rule();
                    token.addRule(rule);
                    ++point;
                    break;
                case '\'':
                    String term = getTermString();
                    if (isSkip && term.length() != 1) {
                        throw new GrammarParserException("Skip tokens can have only char. Check it out at line "
                                + position.line + " at column  " + (point - term.length() - position.pastPoint));
                    }
                    rule.addChild(new Rule.Child(getTermToken(term)));
                    break;
                case '[':
                    rule.addChild(new Rule.Child(getSqTerm()));
                    break;
                default:
                    if (Character.isUpperCase(getChar())) {
                        if (isSkip) {
                            throw new GrammarParserException("Skip tokens can have only char. Check it out at line "
                                    + position.line + " at column  " + (point - position.pastPoint));
                        }
                        rule.addChild(new Rule.Child(getTToken(getWord())));
                    } else {
                        throw new UnknownLineException(tokenName, position.line, point - position.pastPoint);
                    }
            }
            skipWhiteSpace();
        }
        ++point;
        if (rule.children.isEmpty()) {
            rule.addChild(new Rule.Child(EPS));
        }
    }

    private TRule getTRule(String ruleName) {
        if (!tokensId.containsKey(ruleName)) {
            TRule token = new TRule(tokens.size(), ruleName);
            tokens.add(token);
            tokensId.put(ruleName, token);
            return token;
        }

        return (TRule) tokensId.get(ruleName);
    }

    private void parseRule() throws GrammarParserException {
        String ruleName = getWord();
        skipWhiteSpace();

        TRule token = getTRule(ruleName);

        skipWhiteSpace();
        if (getChar() == '[') {
            token.inheritedAttributes.append(getCode(false));
        }

        skipWhiteSpace();
        if (getChar() == 'r') {
            if (getWord().equals("returns")) {
                skipWhiteSpace();
                if (getChar() == '[') {
                    token.synthesizedAttributes.append(getCode(false));
                } else {
                    throw new UnknownLineException(ruleName, position.line, point - position.pastPoint,
                            "Synthesized attribute in \'[\'\']\' is expected after \"returns\"");
                }
            } else {
                throw new UnknownLineException(ruleName, position.line, point - position.pastPoint,
                        "If it is a rule, it is expected after the name and attributes \':\'");
            }
        }

        skipWhiteSpace();
        if (getChar() != ':') {
            throw new UnknownLineException(ruleName, position.line, point - ruleName.length() - position.pastPoint,
                    "If it is a rule, it is expected after the name and attributes \':\'");
        }
        ++point;

        Rule rule = new Rule();
        token.addRule(rule);
        skipWhiteSpace();
        while (getChar() != ';') {
            switch (getChar()) {
                case '|':
                    if (rule.children.isEmpty()) {
                        rule.addChild(new Rule.Child(EPS));
                    }
                    rule = new Rule();
                    token.addRule(rule);
                    ++point;
                    break;
                case '{':
                    rule.code.append(getCode(true));
                    break;
                case '\'':
                    String term = getTermString();
                    rule.addChild(new Rule.Child(getTermToken(term)));
                    break;
                case '[':
                    rule.addChild(new Rule.Child(getSqTerm()));
                    break;
                default:
                    if (Character.isUpperCase(getChar())) {
                        rule.addChild(new Rule.Child(getTToken(getWord())));
                    } else if (Character.isLowerCase(getChar())) {
                        String name = getWord();
                        skipWhiteSpace();
                        if (getChar() == '=') {
                            ++point;
                            skipWhiteSpace();
                            if (Character.isUpperCase(getChar())) {
                                rule.addChild(new Rule.Child(getTToken(getWord()), name));
                            } else if (Character.isLowerCase(getChar())) {
                                String curName = getWord();
                                skipWhiteSpace();
                                String ruleCode = null;
                                if (getChar() == '[') {
                                    ruleCode = getCode(false);
                                }
                                rule.addChild(new Rule.Child(getTRule(curName), name, ruleCode));
                            } else {
                                throw new UnknownLineException(ruleName, position.line, point - position.pastPoint, "Rule or Token is excepted.");
                            }
                        } else {
                            skipWhiteSpace();
                            String ruleCode = null;
                            if (getChar() == '[') {
                                ruleCode = getCode(false);
                            }
                            rule.addChild(new Rule.Child(getTRule(name), null, ruleCode));
                        }
                    } else {
                        throw new UnknownLineException(Character.toString(getChar()), position.line, point - position.pastPoint);
                    }
            }
            skipWhiteSpace();
        }
        ++point;
        if (rule.children.isEmpty()) {
            rule.addChild(new Rule.Child(EPS));
        }
    }

    private void parse() throws GrammarParserException {
        skipWhiteSpace();
        if (getChar() != '@') {
            throw new GrammarParserException("The first line should contain the name of the start token like \"@start <token name>\"");
        }
        ++point;
        if (!getWord().equals("start")) {
            throw new GrammarParserException("The first line should contain the name of the start token like \"@start <token name>\"");
        }
        START = getTRule(getWord());

        skipWhiteSpace();
        while (getChar() == '@') {
            int curPoint = point++;
            String line = getWord();
            switch (line) {
                case "header":
                    Tokenizer header = new TCode(tokens.size(), TCode.TokenType.HEADER, getCode(true));
                    tokens.add(header);
                    tokensId.put("@header", header);
                    break;
                case "members":
                    Tokenizer members = new TCode(tokens.size(), TCode.TokenType.MEMBERS, getCode(true));
                    tokens.add(members);
                    tokensId.put("@members", members);
                    break;
                default:
                    throw new UnknownLineException(line, position.line, curPoint - position.pastPoint);
            }
            skipWhiteSpace();
        }

        while (point != grammar.length()) {
            if (Character.isUpperCase(getChar())) {
                parseToken();
            } else if (Character.isLowerCase(getChar())) {
                parseRule();
            } else {
                throw new UnknownLineException(Character.toString(getChar()), position.line, point - position.pastPoint);
            }
            skipWhiteSpace();
        }
    }

    private static class Position {
        int line;
        int pastPoint;

        Position() {
            this.line = 0;
            this.pastPoint = 0;
        }

        void set(int line, int pastPoint) {
            this.line = line;
            this.pastPoint = pastPoint;
        }

        void update(int pastPoint) {
            ++this.line;
            this.pastPoint = pastPoint - 1;
        }
    }
}
