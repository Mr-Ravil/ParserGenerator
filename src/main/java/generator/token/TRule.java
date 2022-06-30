package generator.token;

public class TRule extends Tokenizer {
    public StringBuilder inheritedAttributes;
    public StringBuilder synthesizedAttributes;

    public TRule(int id, String name) {
        this.id = id;
        this.name = name;
        this.inheritedAttributes = new StringBuilder();
        this.synthesizedAttributes = new StringBuilder();
    }

    public TRule(int id) {
        this.id = id;
        this.inheritedAttributes = new StringBuilder();
        this.synthesizedAttributes = new StringBuilder();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }
}
