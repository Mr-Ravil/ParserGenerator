package tree;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;

public abstract class Tree {
    public static int count = 0;
    public int id;
    public String name;
    public List<Tree> children;

//    public Tree(String name, Tree... children) {
//        this.name = name;
//        this.children = asList(children);
//        id = count++;
//    }
//
//    public Tree(String name) {
//        this.name = name;
//        this.children = new ArrayList<>();
//        id = count++;
//    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void addChild(Tree tree) {
        children.add(tree);
    }

    public Node toNode() {
        Node curNode = node(String.valueOf(id)).with(Label.html(name));
        for (Tree child : children) {
            curNode = curNode.link(child.toNode());
        }
        return curNode;
    }
}