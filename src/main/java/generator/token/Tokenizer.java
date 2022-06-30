package generator.token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Tokenizer {
    public int id;
    public String name;

    public List<Rule> rules = new ArrayList<>();
    public Set<Term> FIRST = new HashSet<>();
    public Set<Term> FOLLOW = new HashSet<>();
}
