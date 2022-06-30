package generator.token;

public class TCode extends Tokenizer  {
    public TokenType type;
    public String code;

    public TCode(int id, TokenType type, String code) {
        this.id = id;
        this.type = type;
        this.code = code;
    }

    public enum TokenType {
//        TOKEN, RULE, TERM, HEADER, MEMBERS, GRAMMAR_NAME, SKIP
        HEADER, MEMBERS
    }

}
