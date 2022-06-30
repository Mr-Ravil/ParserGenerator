package labs.lab2;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import tree.Tree;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.*;

public class Visualizer {
    public void visualize(Tree tree, String name) {
        MutableGraph g = mutGraph(name).setDirected(true).add(tree.toNode());
        try {
            Graphviz.fromGraph(g).width(1080).render(Format.PNG).toFile(new File("trees/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void visualizeCompressed(Tree tree, String name) {
//        MutableGraph g = mutGraph(name + "Compressed").setDirected(true).add(tree.toCompressedNode());
//        try {
//            Graphviz.fromGraph(g).width(1080).render(Format.PNG).toFile(new File("trees/"+name+"Compressed.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
