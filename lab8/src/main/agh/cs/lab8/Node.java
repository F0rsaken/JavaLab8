package agh.cs.lab8;

import java.util.ArrayList;

/**
 * Created by student25 on 2017-12-12.
 */
public class Node {
    private ArrayList<Node> children = new ArrayList<>();
    private String index;
    private String title;
    private String content;

    public Node (String title,String index, String content) {
        this.index = index;
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addContent(String content) {
        this.content += "\n" + content;
    }

    public void putChild(Node node) {
        this.children.add(node);
    }

    public void parseFile(int i, ArrayList<String> file) {
        NodePair tmp;
        for (; i < file.size();) {
            if(file.get(i).matches("^DZIA£.*")) {
                tmp = Section.parseSection(i, file);
                this.putChild(tmp.getNode());
                i = tmp.getIndex();
            }else if(file.get(i).matches("^Rozdzia³.*")) {
                tmp = Chapter.parseChapter(i, file);
                this.putChild(tmp.getNode());
                i = tmp.getIndex();
            }
        }
    }

    public void printFull() {
        System.out.println(this.title);
        if (!this.content.equals("")) {
            System.out.println(this.content);
        }

        if (this.children.size() != 0) {
            for (Node node : this.children) {
                node.printFull();
            }
        }
    }

    public void printTableOfContent() {
        for (Node node : this.children) {
            if (node.title.matches("^DZIA£.*")) {
                System.out.println(node.title + " - " + node.content);
                node.printTableOfContent();
            }else if (node.title.matches("^Rozdzia³.*")) {
                System.out.println(node.title + " - " + node.content);
            }
        }
    }

//    public boolean printRange(String start, String end) {
//        boolean ended;
//        if (this.children.get(0).index.matches("^art[.].*")) {
//
//        }else {
//            this.children
//        }
//    }

    public void print(String arg, Mode mode) {
        if (this.index.matches(arg)) {
            if (mode == Mode.Full) {
                this.printFull();
            }else {
                this.printTableOfContent();
            }
        }else {
            int i = 0;
            while (!this.children.get(i).index.matches(arg)) {
                i++;
            }
            this.children.get(i).print(arg, mode);
            //wywo³aæ dla wszystkich dzieci w pêtli
        }
    }

    public boolean printDirect(ArrayList<String> args) {

        return false;
    }
}