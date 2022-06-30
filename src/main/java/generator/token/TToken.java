package generator.token;

import java.util.ArrayList;
import java.util.HashSet;

public class TToken extends Tokenizer {

    public TToken(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TToken(int id) {
        this.id = id;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }
}
