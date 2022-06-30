package generator.token;

public class Term extends Tokenizer {
    public String term;

    public Term(int id, String name, String term) {
        this.id = id;
        this.name = name;
        this.term = term;
        FIRST.add(this);
    }
}
