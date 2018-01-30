package agh.cs.lab8;

import java.util.ArrayList;

public class Section {
    public static NodePair parseSection(int i, ArrayList<String> file) {
        String index = file.get(i).replaceAll("\\s","").toLowerCase();
        Node node = new Node(file.get(i), index, file.get(i+1));

        i += 2;
        for (; !file.get(i).matches("^Rozdział.*") && !file.get(i).matches("^Art[.].*"); i++) {
            node.addContent(file.get(i));
        }

        NodePair tmp;
        if (file.get(i).matches("^Rozdział.*")) {
            for (; i<file.size() && !file.get(i).matches("^DZIAŁ.*");) {
                tmp = Chapter.parseChapter(i, file);
                i = tmp.getIndex();
                node.putChild(tmp.getNode());
            }
        }else if (file.get(i).matches("^Art[.].*")) {
            for (; i<file.size() && !file.get(i).matches("^DZIAŁ.*");) {
                tmp = Article.parseArticle(i, file);
                i = tmp.getIndex();
                node.putChild(tmp.getNode());
            }
        }

        return new NodePair(i, node);
    }
}
