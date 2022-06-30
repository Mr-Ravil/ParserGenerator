@header {
    import java.lang.StringBuilder;
    import java.util.*;
}

@members {
    private final String INITIAL_NAME = "IIOIOIOIOIOIOIOIOIOIOIOIOIOIOIOIOIO";
    private Map<String, StringBuilder> obfuscatedNames = new HashMap<String, StringBuilder>();
    private Random random = new Random();

    private StringBuilder getNewName(StringBuilder curName) {
        StringBuilder newName;
        if (obfuscatedNames.containsKey(curName.toString()))
            newName = obfuscatedNames.get(curName.toString());
        else {
            int number = obfuscatedNames.size();
            newName = new StringBuilder(INITIAL_NAME);
            for (int i = newName.length() - 1; i > 0; i--) {
                newName.setCharAt(i, (number & 1) == 0 ? newName.charAt(i) : newName.charAt(i) == 'I' ? '1' : '0');
                number >>= 1;
            }
            obfuscatedNames.put(curName.toString(), newName);
        }

        return newName;
    }

    private StringBuilder getSomethingStrange() {
        StringBuilder stringBuilder = new StringBuilder();

        if (random.nextBoolean()) {
            StringBuilder randName = new StringBuilder(" = ").append(Integer.toString(random.nextInt())).append(";");
            //            if (!obfuscatedNames.isEmpty() && random.nextBoolean())
            //                randName = new StringBuilder(" = ")
            //                        .append(new ArrayList<StringBuilder>(obfuscatedNames.values()).get(random.nextInt(obfuscatedNames.size()))).append(";");

            stringBuilder.append("\nint ").append(getNewName(new StringBuilder(Integer.toString(obfuscatedNames.size())))).append(randName);
        }

        return stringBuilder;
    }

    private StringBuilder makeNice(StringBuilder bodyStr) {
        bodyStr = new StringBuilder("\t").append(bodyStr.toString().replaceAll("\n", "\n\t"));
        bodyStr.setLength(bodyStr.length() - 1);
        return bodyStr;
    }

}

program returns [StringBuilder code]:
    a1 = libraries
    a2 = blocks
    {
        $code = new StringBuilder();
        $code.append($a1.code).append("\n").append($a2.code);
    }
;

libraries returns [StringBuilder code]:
    {
        $code = new StringBuilder();
    }
    |
    a1 = librarie
    a2 = libraries
    {
        $code = new StringBuilder();
        $code.append($a1.code).append($a2.code);
    }
;

blocks returns [StringBuilder code]:
    {
        $code = new StringBuilder();
    }
    |
    a1 = block
    a2 = blocks
    {
        $code = new StringBuilder();
        $code.append($a1.code).append("\n\n").append($a2.code);
    }
;

librarie returns [StringBuilder code]:
    a1 = Include
    '<'
    a2 = librarieName
    '>'
    {
        $code = new StringBuilder();
        $code.append($a1.getText()).append(" <").append($a2.code).append(">\n");
    }
    |
    a3 = Include
    '"'
    a4 = librarieName
    '"'
    {
        $code = new StringBuilder();
        $code.append($a3.getText()).append(" \"").append($a4.code).append("\"\n");
    }
;

librarieName returns [StringBuilder code]:
    a1 = libName
    '.'
    a2 = librarieName
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(".").append($a2.code);
    }
    |
    a3 = libName
    {
        $code = new StringBuilder();
        $code.append($a3.code);
    }
;

libName returns [StringBuilder code]:
    a1 = name
    {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
    |
    a2 = type
    {
        $code = new StringBuilder();
        $code.append($a2.code);
    }
    |
    a3 = Vector
    {
        $code = new StringBuilder();
        $code.append($a3.getText());
    }
;

block returns [StringBuilder code]:
    a1 = function
    {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
    |
    a2 = using
    {
        $code = new StringBuilder();
        $code.append($a2.code);
    }
    |
    a3 = variableDeclaration
    {
        $code = new StringBuilder();
        $code.append($a3.code);
    }
;

using returns [StringBuilder code]:
    a1 = Using
    a2 = Name
    a3 = Name
    a4 = End
    {
        $code = new StringBuilder();
        $code.append($a1.getText()).append(" ").append($a2.getText()).append(" ").append($a3.getText()).append($a4.getText());
    }
;

variableDeclaration returns [StringBuilder code]:
    a1 = type
    a2 = variables
    a3 = End
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(" ").append($a2.code).append($a3.getText());
    }
;

variables returns [StringBuilder code]:
    a1 = variableAssign
    ','
    a2 = variables
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(", ").append($a2.code);
    }
    |
    a3 = variableAssign
    {
        $code = new StringBuilder();
        $code.append($a3.code);
    }
;

variableAssign returns [StringBuilder code]:
    a2 = variableName
    a3 = AssignOperation
    a4 = expression
    {
        $code = new StringBuilder();
        $code.append($a2.code).append(" ").append($a3.getText()).append(" ").append($a4.code);
    }
    |
    a1 = variableName
    {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
;

function returns [StringBuilder code]:
    a1 = type
    a2 = name
    a3 = argBrace
    a4 = funBody
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(" ").append($a2.code).append($a3.code).append(" ").append($a4.code).append("\n");
    }
;

funBody returns [StringBuilder code]:
    a1 = End
    {
        $code = new StringBuilder();
        $code.append($a1.getText());
    }
    |
    a2 = OpenFigureBrace
    a3 = body
    a4 = CloseFigureBrace
    {
        $code = new StringBuilder();
        $code.append($a2.getText()).append("\n").append(makeNice($a3.code)).append($a4.getText());
    }
;

argBrace returns [StringBuilder code]:
    a1 = OpenBrace
    a2 = args
    a3 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a1.getText()).append($a2.code).append($a3.getText());
    }
    |
    a4 = OpenBrace
    a5 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a4.getText()).append($a5.getText());
    }
;

args returns [StringBuilder code]:
    a1 = type
    a2 = variableName
    ','
    a3 = args
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(" ").append($a2.code).append(", ").append($a3.code);
    }
    |
    a4 = type
    a5 = variableName
    {
        $code = new StringBuilder();
        $code.append($a4.code).append(" ").append($a5.code);
    }
;

body returns [StringBuilder code]:
    {
        $code = new StringBuilder();
    }
    |
    a1 = line
    a2 = body
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(getSomethingStrange()).append("\n").append($a2.code);
    }
;

line returns [StringBuilder code]:
    a1 = variableDeclaration
    {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
    |
    a2 = expression
    a3 = End
    {
        $code = new StringBuilder();
        $code.append($a2.code).append($a3.getText());
    }
    |
    a4 = cppIf
    {
        $code = new StringBuilder();
        $code.append($a4.code);
    }
    |
    a5 = loop
    {
        $code = new StringBuilder();
        $code.append($a5.code);
    }
    |
    a6 = Return
    a7 = End
    {
        $code = new StringBuilder();
        $code.append($a6.getText()).append($a7.getText());
    }
    |
    a8 = Return
    a9 = expression
    a10 = End
    {
        $code = new StringBuilder();
        $code.append($a8.getText()).append(" ").append($a9.code).append($a10.getText());
    }
;

cppIf returns [StringBuilder code]:
    If
    OpenBrace
    a3 = expression
    CloseBrace
    a5 = loopBody
    a6 = Else
    a7 = cppElse
    {
        $code = new StringBuilder();
        $code.append("if (").append($a3.code).append(") ").append($a5.code).append("else ").append($a7.code);
    }
    |
    If
    OpenBrace
    a10 = expression
    CloseBrace
    a12 = loopBody
    {
        $code = new StringBuilder();
        $code.append("if (").append($a10.code).append(") ").append($a12.code);
    }
;

cppElse returns [StringBuilder code]:
    a1 = cppIf
    {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
    |
    a2 = loopBody
    {
        $code = new StringBuilder();
        $code.append($a2.code);
    }
;

loop returns [StringBuilder code]:
    a1 = whileHead
    a2 = loopBody
    {
        $code = new StringBuilder();
        $code.append($a1.code).append(" ").append($a2.code);
    }
    |
    a3 = forHead
    a4 = loopBody
    {
        $code = new StringBuilder();
        $code.append($a3.code).append(" ").append($a4.code);
    }
;

loopBody returns [StringBuilder code]:
    a1 = line
    {
        $code = new StringBuilder();
        $code.append($a1.code).append("\n");
    }
    |
    a2 = OpenFigureBrace
    a3 = body
    a4 = CloseFigureBrace
    {
        $code = new StringBuilder();
        $code.append($a2.getText()).append("\n").append(makeNice($a3.code)).append($a4.getText()).append("\n");
    }
;

whileHead returns [StringBuilder code]:
    a1 = While
    a2 = OpenBrace
    a3 = expression
    a4 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a1.getText()).append(" ").append($a2.getText()).append($a3.code).append($a4.getText());
    }
;

forHead returns [StringBuilder code]:
    a1 = For
    a2 = OpenBrace
    a3 = maybeDec
    a4 = maybeExpr
    a5 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a1.getText()).append(" ").append($a2.getText()).append($a3.code).append($a4.code).append($a5.getText());
    }
;

maybeDec returns [StringBuilder code]:
    a1 = variableDeclaration {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
    |
    a2 = End {
        $code = new StringBuilder();
        $code.append($a2.getText());
    }
;

maybeExpr returns [StringBuilder code]:
    a1 = expression
    a2 = End
    a3 = expression
    {
        $code = new StringBuilder();
        $code.append(" ").append($a1.code).append($a2.getText()).append(" ").append($a3.code);
    }
    |
    a4 = End
    a5 = expression
    {
        $code = new StringBuilder();
        $code.append($a4.getText()).append(" ").append($a5.code);
    }
    |
    a6 = expression
    a7 = End
    {
        $code = new StringBuilder();
        $code.append(" ").append($a6.code).append($a7.getText());
    }
    |
    a8 = End
    {
        $code = new StringBuilder();
        $code.append($a8.getText());
    }
;

expression returns [StringBuilder code]:
    a1 = value {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
    |
    a2 = variableName
    a3 = OpenBrace
    a4 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a2.code).append($a3.getText()).append($a4.getText());
    }
    |
    a5 = variableName
    a6 = OpenBrace
    a7 = exprArgs
    a8 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a5.code).append($a6.getText()).append($a7.code).append($a8.getText());
    }
    |
    a18 = variableName
    a19 = AssignOperation
    a20 = expression {
        $code = new StringBuilder();
        $code.append($a18.code).append(" ").append($a19.getText()).append(" ").append($a20.code);
    }
    |
    a9 = OpenBrace
    a10 = expression
    a11 = CloseBrace
    {
        $code = new StringBuilder();
        $code.append($a9.getText()).append($a10.code).append($a11.getText());
    }
    |
    a11 = PrefixUnaryOperation
    a12 = expression
    {
        $code = new StringBuilder();
        $code.append($a11.getText()).append($a12.code);
    }
    |
    a13 = expression
    a14 = SuffixUnaryOperatiodn
    {
        $code = new StringBuilder();
        $code.append($a13.code).append($a14.getText());
    }
    |
    a15 = expression
    a16 = BinaryOperation
    a17 = expression
    {
        $code = new StringBuilder();
        $code.append($a15.code).append(" ").append($a16.getText()).append(" ").append($a17.code);
    }
;

exprArgs returns [StringBuilder code]:
    a2 = expression
    ','
    a3 = exprArgs
    {
        $code = new StringBuilder();
        $code.append($a2.code).append(", ").append($a3.code);
    }
    |
    a1 = expression {
        $code = new StringBuilder();
        $code.append($a1.code);
    }
;

value returns [StringBuilder code]:
    a1 = Number {
        $code = new StringBuilder();
        $code.append($a1.getText());
    }
    |
    a3 = variableName
    a4 = OpenSqBrace
    a5 = expression
    a6 = CloseSqBrace {
        $code = new StringBuilder();
        $code.append($a3.code).append($a4.getText()).append($a5.code).append($a6.getText());
    }
    |
    a2 = variableName {
        $code = new StringBuilder();
        $code.append($a2.code);
    }
    |
    '&'
    a8 = value
    {
        $code = new StringBuilder();
        $code.append("&").append($a8.code);
    }
    |
    '*'
    a9 = value
    {
        $code = new StringBuilder();
        $code.append("*").append($a9.code);
    }
    |
    a10 = value
    '->'
    a11 = value
    {
        $code = new StringBuilder();
        $code.append($a10.code).append("->").append($a11.code);
    }
    |
    a12 = value
    '.'
    a13 = value
    {
        $code = new StringBuilder();
        $code.append($a12.code).append(".").append($a13.code);
    }
    |
    a7 = String {
        $code = new StringBuilder();
        $code.append($a7.getText());
    }
;

type returns [StringBuilder code]:
    a1 = Type
    {
        $code = new StringBuilder();
        $code.append($a1.getText());
    }
    |
    Vector
    '<'
    a2 = type
    '>'
    {
        $code = new StringBuilder();
        $code.append("vector <").append($a2.code).append(">");
    }
;

variableName returns [StringBuilder code]:
    a1 = name
    {
        $code = new StringBuilder();
        $code.append(getNewName($a1.code));
    }
;

name returns [StringBuilder code]:
    a1 = Name
    {
        $code = new StringBuilder();
        $code.append($a1.getText());
    }
;

Include : '#include';
Using : 'using';

Return : 'return';

While : 'while';
For : 'for';

If : 'if';
Else : 'else';

Vector : 'vector';

Number : Boolean | Digits | FloatNumber;

Type : 'bool' | 'short int' | 'int' | 'long long' |
        'float' | 'double' | 'long double' |
        'char' | 'string' | 'auto';

DualOperation : '++' | '--';

AssignOperation : Assign | '+=' | Minus Assign | '*=' | '/=' | '%=' | '^=' | '&=' | '|=' | '>>=' | '<<=';

BinaryOperation : '+' | Minus | '*' | '/' | '%' | '^' | '&' | '|' | '&&' | '||' | '>>' | '<<' |
                '>' | '<' | '>=' | '<=' | '==' | '!=';

Minus : '-';
Assign : '=';

PrefixUnaryOperation : '~' | '!' | Minus | DualOperation;

SuffixUnaryOperatiodn : DualOperation;

Boolean : 'true' | 'false';
FloatNumber : Digits '.' Digits;
Digits : Digit | Digit Digits;
Digit : [0-9];

Name : Letter CurNames;
CurNames : CurName CurNames |;
CurName : Letter | Digit | '_' ;

String : '"'AnyString'"';

Letter : [a-zA-Z];

OpenBrace : '(';
CloseBrace : ')';
OpenFigureBrace : '{';
CloseFigureBrace : '}';
OpenSqBrace : '[';
CloseSqBrace : ']';

End : ';';

WS~ : [ \t\n\r];

AnyString : AnyChar | AnyChar AnyString;

AnyChar : [^"];