package generator;

import exceptions.generator.GeneratorException;
import exceptions.generator.GrammarParserException;
import generator.parser.GrammarParser;
import generator.token.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Generator {
    private final String grammarName;
    private final Path grammarPath;
    private final Path resultDir;
    private GrammarParser parser;

    private String packageString;

    public Generator(String grammarName, Path grammarPath) {
        this.grammarName = grammarName;
        this.grammarPath = grammarPath;
        this.resultDir = Paths.get("C:\\Users\\idea\\Desktop\\ITMO\\МТ\\lab4\\src\\main\\java\\generated_parser\\"
                + grammarName + "\\");
        this.packageString = "package generated_parser." + grammarName + ";\n\n";
    }

    private void parseGrammar() throws GrammarParserException, GeneratorException {
        String grammar = null;//.reduce("", String::concat);
        try {
            grammar = Files.lines(grammarPath).reduce("", (a, b) -> a + "\n" + b);
        } catch (IOException e) {
            throw new GeneratorException("Something wrong with file on the path " + grammarPath.toString(), e);
        }
        parser = new GrammarParser(grammar);
    }

    private void constructFIRST() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Tokenizer token : parser.tokens) {
                for (Rule rule : token.rules) {
                    Tokenizer tokenA = rule.children.get(0).token;
//                    if (tokenA instanceof Term) {
//                        changed |= token.FIRST.add((Term) tokenA);
//                    } else if (rule.children.size() > 1) {
//                        if (tokenA.FIRST.contains(parser.EPS)) {
//                            changed |= token.FIRST.addAll(tokenA.FIRST);
//                        } else {
//                            tokenA.FIRST.remove(parser.EPS);
//                            changed |= token.FIRST.addAll(tokenA.FIRST);
//                            changed |= token.FIRST.addAll(rule.children.get(1).token.FIRST);
//                            tokenA.FIRST.add(parser.EPS);
//                        }
//                    }
                    if (!tokenA.FIRST.contains(parser.EPS) || tokenA instanceof Term) {
                        changed |= token.FIRST.addAll(tokenA.FIRST);
                    } else if (rule.children.size() > 1) {
                        tokenA.FIRST.remove(parser.EPS);
                        changed |= token.FIRST.addAll(tokenA.FIRST);
                        changed |= token.FIRST.addAll(rule.children.get(1).token.FIRST);
                        tokenA.FIRST.add(parser.EPS);
                    } else {
                        changed |= token.FIRST.addAll(tokenA.FIRST);
                    }
                }
            }
        }
    }

    private void constructFOLLOW() {
        parser.START.FOLLOW.add(parser.END);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Tokenizer tokenA : parser.tokens) {
                for (Rule rule : tokenA.rules) {
                    for (int i = rule.children.size() - 1; i >= 0; i--) {
                        Tokenizer tokenB = rule.children.get(i).token;
                        if (tokenB instanceof Term)
                            continue;
                        if (i + 1 == rule.children.size()) {
                            changed |= tokenB.FOLLOW.addAll(tokenA.FOLLOW);
                        } else {
                            Tokenizer tokenY = rule.children.get(i + 1).token;
                            if (tokenY.FIRST.remove(parser.EPS)) {
                                changed |= tokenB.FOLLOW.addAll(tokenA.FOLLOW);
                                changed |= tokenB.FOLLOW.addAll(tokenY.FIRST);
                                tokenY.FIRST.add(parser.EPS);
                            } else {
                                changed |= tokenB.FOLLOW.addAll(tokenY.FIRST);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean intersectionIsEmpty(Set a, Set b) {
        for (Object e : b) {
            if (a.contains(e))
                return false;
        }
        return true;
    }

    private boolean checkLL1() {
        for (Tokenizer token : parser.tokens) {
            for (Rule ruleA : token.rules) {
                Tokenizer tokenA = ruleA.children.get(0).token;
                for (Rule ruleB : token.rules) {
                    if (ruleA == ruleB)
                        continue;
                    Tokenizer tokenB = ruleB.children.get(0).token;
                    if (!intersectionIsEmpty(tokenA.FIRST, tokenB.FIRST))
                        return false;
                    if (tokenA.FIRST.contains(parser.EPS) && !intersectionIsEmpty(tokenB.FIRST, tokenA.FOLLOW))
                        return false;
                }
            }
        }
        return true;
    }


    ////////////////////////////////////////////////////////////

    private void generateTokens() throws GeneratorException {
        Path tokenPath = Paths.get(resultDir.toString() + "\\" + "Token.java");
        try {
            Files.createFile(tokenPath);
        } catch (FileAlreadyExistsException e) {
            // ignore
        } catch (IOException e) {
            throw new GeneratorException("Failed to create File \"" + tokenPath.toString() + "\"", e);
        }

        StringBuilder code = new StringBuilder(packageString);
        code.append("public enum Token {\n\tBEGIN");
        for (Tokenizer token : parser.tokens) {
            if (token instanceof Term) {
                code.append(", ").append(((Term) token).name);
            }
        }
        code.append("\n}\n");

        try (FileWriter writer = new FileWriter(tokenPath.toFile())) {
            writer.write(code.toString());
        } catch (IOException e) {
            throw new GeneratorException("Failed to write code to File \"" + tokenPath.toString() + "\"", e);
        }
    }

    private void generateLexicalAnalyzer() throws GeneratorException {
        Path lexicalAnalyzerPath = Paths.get(resultDir.toString() + "\\" + "LexicalAnalyzer.java");
        try {
            Files.createFile(lexicalAnalyzerPath);
        } catch (FileAlreadyExistsException e) {
            // ignore
        } catch (IOException e) {
            throw new GeneratorException("Failed to create File \"" + lexicalAnalyzerPath.toString() + "\"", e);
        }

        StringBuilder code = new StringBuilder(packageString);

        code.append("import exceptions.parser.*;\n\n");
        code.append("import java.util.HashSet;\n" +
                "import java.util.Set;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n\n");
        code.append("public class LexicalAnalyzer {\n");
        code.append("    private String text;\n");
        code.append("    private int point;\n\n");
        code.append("    private Token curToken;\n");
        code.append("    private String name;\n\n");
        code.append("    private Set<Character> skipChars = new HashSet<>(Arrays.asList(");
        boolean isFirst = true;
        Map<String, String> shieldedChar = new HashMap<>();
        shieldedChar.put("\t", "\\t");
        shieldedChar.put("\b", "\\b");
        shieldedChar.put("\n", "\\n");
        shieldedChar.put("\r", "\\r");
        shieldedChar.put("\f", "\\f");
        shieldedChar.put("\'", "\\\'");
        shieldedChar.put("\"", "\\\"");
        shieldedChar.put("\\", "\\\\");
        for (Term term : parser.SKIP.FIRST) {
            if (!isFirst) {
                code.append(", ");
            }
            isFirst = false;
            code.append("\'");
            if (shieldedChar.containsKey(term.term))
                code.append(shieldedChar.get(term.term));
            else
                code.append(term.term);
            code.append("\'");
        }
        code.append("));\n");
        code.append("    private List<NameToken> terms = new ArrayList<>(Arrays.asList(");
        isFirst = true;
        for (Tokenizer token : parser.tokens) {
            if (token instanceof Term && !parser.SKIP.FIRST.contains(token) && token != parser.END && token != parser.EPS) {
                if (!isFirst) {
                    code.append(",");
                }
                isFirst = false;
                code.append("new NameToken(\"");
                code.append(((Term) token).term);
                code.append("\", Token.");
                code.append(((Term) token).name);
                code.append(")");
            }
        }
        code.append("));\n\n");
        code.append("    public Token getCurrentToken() {\n" +
                "        return curToken;\n" +
                "    }\n\n");
        code.append("    public String getName() {\n" +
                "        return name;\n" +
                "    }\n\n");
        code.append("    public int getPoint() {\n" +
                "        return point;\n" +
                "    }\n\n");
        code.append("    private void skipWhiteSpace() {\n" +
                "        while (point < text.length() && skipChars.contains(text.charAt(point))) {\n" +
                "            point++;\n" +
                "        }\n" +
                "    }\n\n");
        code.append("    public void nextToken() throws ParserException {\n" +
                "        skipWhiteSpace();\n" +
                "        if (point >= text.length()) {\n" +
                "            curToken = Token.$;\n" +
                "            return;\n" +
                "        }\n\n" +
                "        for (NameToken term : terms) {\n" +
                "            int i = 0;\n" +
                "            while (i < term.name.length() && i + point < text.length() && term.name.charAt(i) == text.charAt(point + i)) {\n" +
                "                i++;\n" +
                "            }\n" +
                "            if (i == term.name.length()) {\n" +
                "                point += i;\n" +
                "                curToken = term.token;\n" +
                "                name = term.name;\n" +
                "                return;\n" +
                "            }\n" +
                "        }\n" +
                "        throw new UnknownActionException(String.valueOf(text.charAt(point)), point);\n" +
                "    }\n\n");
        code.append("    public LexicalAnalyzer(String newText) {\n" +
                "        point = 0;\n" +
                "        text = newText;\n" +
                "        curToken = Token.BEGIN;\n" +
                "    }\n" +
                "\n" +
                "    private static class NameToken{\n" +
                "        String name;\n" +
                "        Token token;\n" +
                "\n" +
                "        public NameToken(String name, Token token) {\n" +
                "            this.name = name;\n" +
                "            this.token = token;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}");

        try (FileWriter writer = new FileWriter(lexicalAnalyzerPath.toFile())) {
            writer.write(code.toString());
        } catch (IOException e) {
            throw new GeneratorException("Failed to write code to File \"" + lexicalAnalyzerPath.toString() + "\"", e);
        }
    }

    private void generateParser() throws GeneratorException {
        Path parserPath = Paths.get(resultDir.toString() + "\\" + "Parser.java");
        try {
            Files.createFile(parserPath);
        } catch (FileAlreadyExistsException e) {
            // ignore
        } catch (IOException e) {
            throw new GeneratorException("Failed to create File \"" + parserPath.toString() + "\"", e);
        }

        StringBuilder code = new StringBuilder(packageString);

        code.append("import exceptions.parser.*;\n" +
                "import tree.*;\n\n");
        code.append("import java.util.ArrayList;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n\n");
        if (parser.tokensId.containsKey("@header")) {
            code.append(((TCode) parser.tokensId.get("@header")).code);
            code.append("\n\n");
        }
        code.append("public class Parser {\n" +
                "    private LexicalAnalyzer lexicalAnalyzer;\n\n");

        if (parser.tokensId.containsKey("@members")) {
            code.append(((TCode) parser.tokensId.get("@members")).code);
            code.append("\n\n");
        }

        // create classes
        for (Tokenizer curToken : parser.tokens) {
            if (!(curToken instanceof TRule || curToken instanceof TToken))
                continue;
            code.append("    public static class ");
            if (curToken instanceof TToken)
                code.append("T");
            code.append(curToken.name);
            code.append("Tree extends Tree {\n");
            if (curToken instanceof TRule) {
                TRule token = (TRule) curToken;
                String[] attrs = token.inheritedAttributes.toString().split(",");
                for (String attr : attrs) {
                    if (attr.isEmpty())
                        continue;
                    code.append("        public ");
                    code.append(attr);
                    code.append(";\n");
                }
                attrs = token.synthesizedAttributes.toString().split(",");
                for (String attr : attrs) {
                    if (attr.isEmpty())
                        continue;
                    code.append("        public ");
                    code.append(attr);
                    code.append(";\n");
                }
            }

            if (curToken instanceof TToken) {
                code.append("        public StringBuilder text = new StringBuilder();\n");
            }

            code.append("\n");
            code.append("        public ");
            if (curToken instanceof TToken)
                code.append("T");
            code.append(curToken.name);
            code.append("Tree(Tree... children) {\n" +
                    "            this.name = \"");
            code.append(curToken.name);
            code.append("\";\n" +
                    "            this.children = new ArrayList<>(Arrays.asList(children));\n" +
                    "            id = count++;\n" +
                    "        }\n\n");
            code.append("        public ");
            if (curToken instanceof TToken)
                code.append("T");
            code.append(curToken.name);
            code.append("Tree() {\n" + "            this.name = \"");
            code.append(curToken.name).append("\";\n");
            code.append("            this.children = new ArrayList<>();\n");
            code.append("            id = count++;\n");
            code.append("        }\n\n");
            if (curToken instanceof TToken) {
                code.append("        public String getText() {\n" +
                        "            return this.text.toString();\n" +
                        "        }\n\n");
            }
            code.append("    }\n\n");


//            code.append("\n");
//            code.append("        public ");
//            if (curToken instanceof TToken)
//                code.append("T");
//            code.append(curToken.name);
//            code.append("Tree(");
////            if (curToken instanceof TToken)
////                code.append("String test");
//            code.append(") {\n            this.name = \"");
//            code.append(curToken.name).append("\";\n");
//            if (curToken instanceof TToken)
//                code.append("            this.text = new StringBuilder();\n");
//            code.append("            this.children = new ArrayList<>();\n");
//            code.append("            id = count++;\n");
//            code.append("        }\n\n");
//            code.append("    }\n\n");

        }

        // create parsers
        for (Tokenizer tokenA : parser.tokens) {
            if (!(tokenA instanceof TToken || tokenA instanceof TRule) || tokenA == parser.SKIP)
                continue;
            StringBuilder inAttr = new StringBuilder();
            if (tokenA instanceof TRule) {
                inAttr = ((TRule) tokenA).inheritedAttributes;
            }
            code.append("    private Tree ").append(tokenA.name).append("(").append(inAttr).append(") throws ParserException {\n" +
                    "        ");

            if (tokenA instanceof TToken)
                code.append("T");
            code.append(tokenA.name).append("Tree result = new ");

            if (tokenA instanceof TToken)
                code.append("T");
            code.append(tokenA.name).append("Tree();\n" +
                    "        switch (lexicalAnalyzer.getCurrentToken()) {\n");
            Rule epsRule = null;
            for (Rule rule : tokenA.rules) {
                boolean isEps = true;
                Set<Term> FIRSTp = new HashSet<>();
                for (Rule.Child child : rule.children) {
                    FIRSTp.addAll(child.token.FIRST);
                    if (!FIRSTp.remove(parser.EPS)) {
                        isEps = false;
                        break;
                    }
                }
                if (isEps) {
                    epsRule = rule;
                }
                if (FIRSTp.isEmpty()) {
                    continue;
                }
                for (Term term : FIRSTp) {
                    code.append("            case ").append(term.name).append(":\n");
                }
                code.append("            {\n");

                if (tokenA instanceof TToken) {
                    for (Rule.Child child : rule.children) {
                        if (child.token instanceof Term) {
                            code.append("                result.addChild(new TermTree(lexicalAnalyzer.getName()));\n");
                            code.append("                result.text.append(result.children.get(result.children.size() - 1).getName());\n");
                            code.append("                lexicalAnalyzer.nextToken();\n");
                        } else {
                            code.append("                result.addChild(").append(child.token.name).append("());\n");
                            code.append("                result.text.append(((T").append(child.token.name)
                                    .append("Tree)result.children.get(result.children.size() - 1)).text);\n");
                        }
                    }
                } else {
                    for (Rule.Child child : rule.children) {
                        if (child.name == null) {
                            code.append("                result.addChild(");
                        } else {
                            code.append("                ");
                            if (child.token instanceof TToken)
                                code.append("T");
                            code.append(child.token.name).append("Tree ")
                                    .append(child.name).append(" = (");

                            if (child.token instanceof TToken)
                                code.append("T");
                            code.append(child.token.name).append("Tree)");
                        }

                        if (child.token instanceof Term) {
                            code.append("new TermTree(lexicalAnalyzer.getName()));\n");
                        } else if (child.name == null) {
                            if (child.token instanceof TToken) {
                                code.append(child.token.name).append("());\n");
                            } else {
                                if (((TRule) child.token).inheritedAttributes.length() != 0) {
                                    code.append(child.token.name).append("(").append(child.code).append("));\n");
                                } else {
                                    code.append(child.token.name).append("());\n");
                                }
                            }
                        } else {
                            if (child.token instanceof TToken) {
                                code.append(child.token.name).append("();\n");
                            } else {
                                if (((TRule) child.token).inheritedAttributes.length() != 0) {
                                    code.append(child.token.name).append("(").append(child.code).append(");\n");
                                } else {
                                    code.append(child.token.name).append("();\n");
                                }
                            }
                        }

                        if (child.name != null) {
                            code.append("                result.addChild(").append(child.name).append(");\n");
                        }
                        if (child.token instanceof Term) {
                            code.append("                lexicalAnalyzer.nextToken();\n");
                        }
                    }
                }

                code.append(rule.code.toString().replace("$" + tokenA.name, "result"));
                code.append("                break;\n");
                code.append("            }\n");
            }

            if (epsRule != null) {
                for (Term term : tokenA.FOLLOW) {
                    code.append("            case ").append(term.name).append(":\n");
                }
                code.append("            {\n");
                code.append("                result.addChild(new TermTree(\"eps\"));\n");
                code.append(epsRule.code.toString().replace("$" + tokenA.name, "result"));
                code.append("                break;\n");
                code.append("            }\n");
            }

            code.append("            default:\n" +
                    "                throw new UnexpectedTokenException(lexicalAnalyzer.getName(), lexicalAnalyzer.getPoint() - 1);\n" +
                    "        }\n" +
                    "        return result;\n" +
                    "    }\n\n");
        }

        code.append("\n" + "    public Tree parse(String newText) throws ParserException {\n" +
                "        lexicalAnalyzer = new LexicalAnalyzer(newText);\n" +
                "        lexicalAnalyzer.nextToken();\n" +
                "        return ").append(parser.START.name).append("();\n" +
                "    }\n" +
                "}");

        try (FileWriter writer = new FileWriter(parserPath.toFile())) {
            writer.write(code.toString());
        } catch (IOException e) {
            throw new GeneratorException("Failed to write code to File \"" + parserPath.toString() + "\"", e);
        }
    }

    public void generate() throws GrammarParserException, GeneratorException {
        parseGrammar();
//        eliminationOfLeftRecursion();
//        elimination of left recursion
//elimination of the right branch
        constructFIRST();
        constructFOLLOW();

        if (!checkLL1()) {
            throw new GeneratorException("Grammar on the path " + grammarPath.toString() + " is not the LL(1)-grammar.");
        }

        try {
            Files.createDirectory(resultDir);
        } catch (FileAlreadyExistsException e) {
            // ignore
        } catch (IOException e) {
            throw new GeneratorException("Failed create directory \"" + resultDir.toString() + "\"", e);
        }

        generateTokens();
        generateLexicalAnalyzer();
        generateParser();
    }

}
