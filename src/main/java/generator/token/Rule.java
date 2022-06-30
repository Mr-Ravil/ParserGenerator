package generator.token;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    public StringBuilder code;

    public List<Child> children;

    public Rule() {
        this.code = new StringBuilder();
        this.children = new ArrayList<>();
    }

    public Rule(String code) {
        this.code = new StringBuilder(code);
    }

    public Rule(List<Child> children) {
        this.children = children;
    }

    public Rule(Child child) {
        this.code = new StringBuilder();
        this.children = new ArrayList<>();
        children.add(child);
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public static class Child {
        public Tokenizer token;
        public String name = null;
        public String code;

        public Child(Tokenizer token) {
            this.token = token;
        }

        public Child(Tokenizer token, String name) {
            this.token = token;
            this.name = name;
        }

        public Child(Tokenizer token, String name, String code) {
            this.token = token;
            this.name = name;
            this.code = code;
        }


//        public Child(Tokenizer token, String code) {
//            this.token = token;
//            this.code = code;
//        }
//
//        public Child(Tokenizer token, String code, String name) {
//            this.token = token;
//            this.code = code;
//            this.name = name;
//        }
    }
}