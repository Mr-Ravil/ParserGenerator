package tree;

import java.util.ArrayList;

public class TermTree extends Tree {

    public TermTree(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        id = count++;
    }
}
