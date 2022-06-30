package generated_parser.grammarPython;

import exceptions.parser.*;
import tree.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private LexicalAnalyzer lexicalAnalyzer;

    public static class skipTree extends Tree {

        public skipTree(Tree... children) {
            this.name = "skip";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public skipTree() {
            this.name = "skip";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class sTree extends Tree {

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

    public static class s5Tree extends Tree {

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

    public static class s4Tree extends Tree {

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

    public static class s5pTree extends Tree {

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

    public static class s3Tree extends Tree {

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

    public static class s4pTree extends Tree {

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

    public static class s2Tree extends Tree {

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

    public static class s3pTree extends Tree {

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

    public static class TTree extends Tree {

        public TTree(Tree... children) {
            this.name = "T";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public TTree() {
            this.name = "T";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class s1Tree extends Tree {

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

    public static class s1pTree extends Tree {

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

    public static class T_21Tree extends Tree {

        public T_21Tree(Tree... children) {
            this.name = "T_21";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public T_21Tree() {
            this.name = "T_21";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    public static class T_48Tree extends Tree {

        public T_48Tree(Tree... children) {
            this.name = "T_48";
            this.children = new ArrayList<>(Arrays.asList(children));
            id = count++;
        }

        public T_48Tree() {
            this.name = "T_48";
            this.children = new ArrayList<>();
            id = count++;
        }

    }

    private Tree s() throws ParserException {
        Tree result = new sTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_99:
            case C_100:
            case C_113:
            case C_103:
            case C_110:
            case C_117:
            case C_101:
            case C_107:
            case C_102:
            case C_115:
            case C_98:
            case C_114:
            case C_40:
            case C_121:
            case H_5:
            case C_112:
            case C_111:
            case C_116:
            case C_118:
            case C_119:
            case C_109:
            case C_122:
            case C_105:
            case C_106:
            case C_104:
            case C_108:
            case C_120:
            case C_97:
            {
                result.addChild(s5());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s5() throws ParserException {
        Tree result = new s5Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_99:
            case C_100:
            case C_113:
            case C_103:
            case C_110:
            case C_117:
            case C_101:
            case C_107:
            case C_102:
            case C_115:
            case C_98:
            case C_114:
            case C_40:
            case C_121:
            case H_5:
            case C_112:
            case C_111:
            case C_116:
            case C_118:
            case C_119:
            case C_109:
            case C_122:
            case C_105:
            case C_106:
            case C_104:
            case C_108:
            case C_120:
            case C_97:
            {
                result.addChild(s4());
                result.addChild(s5p());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s4() throws ParserException {
        Tree result = new s4Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_99:
            case C_100:
            case C_113:
            case C_103:
            case C_110:
            case C_117:
            case C_101:
            case C_107:
            case C_102:
            case C_115:
            case C_98:
            case C_114:
            case C_40:
            case C_121:
            case H_5:
            case C_112:
            case C_111:
            case C_116:
            case C_118:
            case C_119:
            case C_109:
            case C_122:
            case C_105:
            case C_106:
            case C_104:
            case C_108:
            case C_120:
            case C_97:
            {
                result.addChild(s3());
                result.addChild(s4p());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s5p() throws ParserException {
        Tree result = new s5pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case H_2:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(s5());
                break;
            }
            case $:
            case C_41:
            {
                result.addChild(new TermTree("eps"));
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s3() throws ParserException {
        Tree result = new s3Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_99:
            case C_100:
            case C_113:
            case C_103:
            case C_110:
            case C_117:
            case C_101:
            case C_107:
            case C_102:
            case C_115:
            case C_98:
            case C_114:
            case C_40:
            case C_121:
            case H_5:
            case C_112:
            case C_111:
            case C_116:
            case C_118:
            case C_119:
            case C_109:
            case C_122:
            case C_105:
            case C_106:
            case C_104:
            case C_108:
            case C_120:
            case C_97:
            {
                result.addChild(s2());
                result.addChild(s3p());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s4p() throws ParserException {
        Tree result = new s4pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case H_3:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(s4());
                break;
            }
            case $:
            case C_41:
            case H_2:
            {
                result.addChild(new TermTree("eps"));
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s2() throws ParserException {
        Tree result = new s2Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case H_5:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(s2());
                break;
            }
            case C_40:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(s5());
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_99:
            case C_100:
            case C_113:
            case C_103:
            case C_110:
            case C_117:
            case C_101:
            case C_107:
            case C_102:
            case C_115:
            case C_98:
            case C_114:
            case C_121:
            case C_112:
            case C_111:
            case C_116:
            case C_118:
            case C_119:
            case C_109:
            case C_122:
            case C_105:
            case C_106:
            case C_104:
            case C_108:
            case C_120:
            case C_97:
            {
                result.addChild(T());
                result.addChild(s1());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s3p() throws ParserException {
        Tree result = new s3pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case H_4:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(s3());
                break;
            }
            case $:
            case H_3:
            case C_41:
            case H_2:
            {
                result.addChild(new TermTree("eps"));
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree T() throws ParserException {
        Tree result = new TTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_99:
            case C_100:
            case C_113:
            case C_103:
            case C_110:
            case C_117:
            case C_101:
            case C_107:
            case C_102:
            case C_115:
            case C_98:
            case C_114:
            case C_121:
            case C_112:
            case C_111:
            case C_116:
            case C_118:
            case C_119:
            case C_109:
            case C_122:
            case C_105:
            case C_106:
            case C_104:
            case C_108:
            case C_120:
            case C_97:
            {
                result.addChild(T_21());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s1() throws ParserException {
        Tree result = new s1Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case H_5:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(s1p());
                break;
            }
            case H_6:
            {
                result.addChild(s1p());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree s1p() throws ParserException {
        Tree result = new s1pTree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case H_6:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                result.addChild(T());
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree T_21() throws ParserException {
        Tree result = new T_21Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_97:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_98:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_99:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_100:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_101:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_102:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_103:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_104:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_105:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_106:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_107:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_108:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_109:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_110:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_111:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_112:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_113:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_114:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_115:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_116:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_117:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_118:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_119:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_120:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_121:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_122:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            default:
                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);
        }
        return result;
    }

    private Tree T_48() throws ParserException {
        Tree result = new T_48Tree();
        switch (lexicalAnalyzer.getCurrentToken()) {
            case C_9:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_10:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_13:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
                lexicalAnalyzer.nextToken();
                break;
            }
            case C_32:
            {
                result.addChild(new TermTree(lexicalAnalyzer.getName()));
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