package generated_parser.calculator;

import exceptions.parser.*;
import tree.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private LexicalAnalyzer lexicalAnalyzer;


    private int getFactorial(int a) {
        int result = 1;
        for (int i = 2; i <= a; i++) {
            result *= i;
        }
        return result;
    }


    public static class TskipTree extends Tree {
        public StringBuilder text = new StringBuilder();

        public TskipTree(Tree... children) {
            this.name = "skip";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TskipTree() {
            this.name = "skip";
            this.children = new ArrayList<>();
            id = count++;
        }

        public String getText() {
            return this.text.toString();
        }

    }

    public static class sTree extends Tree {
        public int result;

        public sTree(Tree... children) {
            this.name = "s";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public sTree() {
            this.name = "s";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s1Tree extends Tree {
        public int result;

        public s1Tree(Tree... children) {
            this.name = "s1";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s1Tree() {
            this.name = "s1";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s2Tree extends Tree {
        public int result;

        public s2Tree(Tree... children) {
            this.name = "s2";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s2Tree() {
            this.name = "s2";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s1pTree extends Tree {
        public int a;
        public int result;

        public s1pTree(Tree... children) {
            this.name = "s1p";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s1pTree() {
            this.name = "s1p";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s3Tree extends Tree {
        public int result;

        public s3Tree(Tree... children) {
            this.name = "s3";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s3Tree() {
            this.name = "s3";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s2pTree extends Tree {
        public int a;
        public int result;

        public s2pTree(Tree... children) {
            this.name = "s2p";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s2pTree() {
            this.name = "s2p";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s4Tree extends Tree {
        public int result;

        public s4Tree(Tree... children) {
            this.name = "s4";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s4Tree() {
            this.name = "s4";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s3pTree extends Tree {
        public int a;
        public int result;

        public s3pTree(Tree... children) {
            this.name = "s3p";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s3pTree() {
            this.name = "s3p";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class smTree extends Tree {
        public int result;

        public smTree(Tree... children) {
            this.name = "sm";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public smTree() {
            this.name = "sm";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s4pTree extends Tree {
        public int a;
        public int result;

        public s4pTree(Tree... children) {
            this.name = "s4p";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s4pTree() {
            this.name = "s4p";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s5Tree extends Tree {
        public int result;

        public s5Tree(Tree... children) {
            this.name = "s5";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s5Tree() {
            this.name = "s5";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s6Tree extends Tree {
        public int result;

        public s6Tree(Tree... children) {
            this.name = "s6";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s6Tree() {
            this.name = "s6";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s5pTree extends Tree {
        public int a;
        public int result;

        public s5pTree(Tree... children) {
            this.name = "s5p";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public s5pTree() {
            this.name = "s5p";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class numberTree extends Tree {
        public int result;

        public numberTree(Tree... children) {
            this.name = "number";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public numberTree() {
            this.name = "number";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class TNumbersTree extends Tree {
        public StringBuilder text = new StringBuilder();

        public TNumbersTree(Tree... children) {
            this.name = "Numbers";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TNumbersTree() {
            this.name = "Numbers";
            this.children = new ArrayList<>();
            id = count++;
        }

        public String getText() {
            return this.text.toString();
        }

    }

    public static class TNumberTree extends Tree {
        public StringBuilder text = new StringBuilder();

        public TNumberTree(Tree... children) {
            this.name = "Number";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TNumberTree() {
            this.name = "Number";
            this.children = new ArrayList<>();
            id = count++;
        }

        public String getText() {
            return this.text.toString();
        }

    }

    public static class TCurNumbersTree extends Tree {
        public StringBuilder text = new StringBuilder();

        public TCurNumbersTree(Tree... children) {
            this.name = "CurNumbers";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TCurNumbersTree() {
            this.name = "CurNumbers";
            this.children = new ArrayList<>();
            id = count++;
        }

        public String getText() {
            return this.text.toString();
        }

    }

    public static class TT_28Tree extends Tree {
        public StringBuilder text = new StringBuilder();

        public TT_28Tree(Tree... children) {
            this.name = "T_28";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TT_28Tree() {
            this.name = "T_28";
            this.children = new ArrayList<>();
            id = count++;
        }

        public String getText() {
            return this.text.toString();
        }

    }

    public static class TT_39Tree extends Tree {
        public StringBuilder text = new StringBuilder();

        public TT_39Tree(Tree... children) {
            this.name = "T_39";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TT_39Tree() {
            this.name = "T_39";
            this.children = new ArrayList<>();
            id = count++;
        }

        public String getText() {
            return this.text.toString();
        }

    }

    private Tree s() throws ParserException {
        sTree result = new sTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_45:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                s1Tree a1 = (s1Tree)s1();
                result.addChild(a1);

        result.result = a1.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s1() throws ParserException {
        s1Tree result = new s1Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_45:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                s2Tree a1 = (s2Tree)s2();
                result.addChild(a1);
                s1pTree a2 = (s1pTree)s1p(a1.result);
                result.addChild(a2);

        result.result =  a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s2() throws ParserException {
        s2Tree result = new s2Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_45:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                s3Tree a1 = (s3Tree)s3();
                result.addChild(a1);
                s2pTree a2 = (s2pTree)s2p(a1.result);
                result.addChild(a2);

        result.result = a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s1p(int a) throws ParserException {
        s1pTree result = new s1pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_45:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s1Tree a1 = (s1Tree)s1();
                result.addChild(a1);

        result.result = a - a1.result;
                    break;
            }
            case $:
            case C_41:
            {
                result.addChild(new TermTree("eps"));

        result.result = a;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s3() throws ParserException {
        s3Tree result = new s3Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_45:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                s4Tree a1 = (s4Tree)s4();
                result.addChild(a1);
                s3pTree a2 = (s3pTree)s3p(a1.result);
                result.addChild(a2);

        result.result = a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s2p(int a) throws ParserException {
        s2pTree result = new s2pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_43:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s2Tree a1 = (s2Tree)s2();
                result.addChild(a1);

        result.result = a + a1.result;
                    break;
            }
            case $:
            case C_45:
            case C_41:
            {
                result.addChild(new TermTree("eps"));

        result.result = a;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s4() throws ParserException {
        s4Tree result = new s4Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_45:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                smTree a1 = (smTree)sm();
                result.addChild(a1);
                s4pTree a2 = (s4pTree)s4p(a1.result);
                result.addChild(a2);

        result.result = a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s3p(int a) throws ParserException {
        s3pTree result = new s3pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_47:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s3Tree a1 = (s3Tree)s3();
                result.addChild(a1);

        result.result = a / a1.result;
                    break;
            }
            case $:
            case C_45:
            case C_41:
            case C_43:
            {
                result.addChild(new TermTree("eps"));

        result.result = a;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree sm() throws ParserException {
        smTree result = new smTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_45:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s5Tree a1 = (s5Tree)s5();
                result.addChild(a1);

        result.result = - a1.result;
                    break;
            }
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                s5Tree a2 = (s5Tree)s5();
                result.addChild(a2);

        result.result = a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s4p(int a) throws ParserException {
        s4pTree result = new s4pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_42:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s4Tree a1 = (s4Tree)s4();
                result.addChild(a1);

        result.result = a * a1.result;
                    break;
            }
            case $:
            case C_47:
            case C_45:
            case C_41:
            case C_43:
            {
                result.addChild(new TermTree("eps"));

        result.result = a;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s5() throws ParserException {
        s5Tree result = new s5Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_40:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                s6Tree a1 = (s6Tree)s6();
                result.addChild(a1);
                s5pTree a2 = (s5pTree)s5p(a1.result);
                result.addChild(a2);

        result.result = a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s6() throws ParserException {
        s6Tree result = new s6Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_40:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s1Tree a1 = (s1Tree)s1();
                result.addChild(a1);
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();

        result.result = a1.result;
                    break;
            }
            case C_50:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                numberTree a2 = (numberTree)number();
                result.addChild(a2);

        result.result = a2.result;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s5p(int a) throws ParserException {
        s5pTree result = new s5pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_33:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                s5pTree a1 = (s5pTree)s5p(a);
                result.addChild(a1);

        result.result = getFactorial(a1.result);
                    break;
            }
            case $:
            case C_47:
            case C_45:
            case C_42:
            case C_41:
            case C_43:
            {
                result.addChild(new TermTree("eps"));

        result.result = a;
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree number() throws ParserException {
        numberTree result = new numberTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                TNumbersTree a1 = (TNumbersTree)Numbers();
                result.addChild(a1);

        result.result = Integer.parseInt(a1.getText());
                    break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree Numbers() throws ParserException {
        TNumbersTree result = new TNumbersTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                result.addChild(Number());
                result.text.append(((TNumberTree)result.children.get(result.children.size() - 1)).text);
                result.addChild(CurNumbers());
                result.text.append(((TCurNumbersTree)result.children.get(result.children.size() - 1)).text);
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree Number() throws ParserException {
        TNumberTree result = new TNumberTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                result.addChild(T_28());
                result.text.append(((TT_28Tree)result.children.get(result.children.size() - 1)).text);
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree CurNumbers() throws ParserException {
        TCurNumbersTree result = new TCurNumbersTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_50:
            case C_54:
            case C_56:
            case C_48:
            case C_55:
            case C_49:
            case C_53:
            case C_57:
            case C_52:
            case C_51:
            {
                result.addChild(Numbers());
                result.text.append(((TNumbersTree)result.children.get(result.children.size() - 1)).text);
                break;
            }
            case $:
            case C_47:
            case C_45:
            case C_33:
            case C_42:
            case C_41:
            case C_43:
            {
                result.addChild(new TermTree("eps"));
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree T_28() throws ParserException {
        TT_28Tree result = new TT_28Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_48:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_49:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_50:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_51:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_52:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_53:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_54:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_55:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_56:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_57:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree T_39() throws ParserException {
        TT_39Tree result = new TT_39Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_9:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_10:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_13:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_32:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                result.text.append(result.children.get(result.children.size() - 1).getName());
                lexicalAnalyzer.nextToken();
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }


    public Tree parse(String newText) throws ParserException {
        lexicalAnalyzer = new LexicalAnalyzer(newText);
        lexicalAnalyzer.nextToken();
        return s();
    }
}