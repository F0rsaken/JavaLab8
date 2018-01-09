package agh.cs.lab8;

public class NodePair {
    private Node node;
    private int index;

    public NodePair(int index, Node node) {
        this.index = index;
        this.node = node;
    }

    public int getIndex() {
        return this.index;
    }

    public Node getNode() {
        return this.node;
    }
}
