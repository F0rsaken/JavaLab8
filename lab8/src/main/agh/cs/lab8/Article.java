package agh.cs.lab8;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article {
    public static NodePair parseArticle(int i, ArrayList<String> file) {
        Pattern pattern = Pattern.compile("^Art[.] \\d+\\D?[.]");
        Matcher matcher = pattern.matcher(file.get(i));
        String title = "";
        if (matcher.find()) title = matcher.group();
        String index = title.replaceAll("\\s","").toLowerCase();
        Node node = new Node(title, index, "");
        String content = file.get(i).replaceAll("^Art[.] \\d+\\D?[.][ ]?", "");

        NodePair tmp;
        if(!content.equals("")) {
            if(content.matches("^\\d+[.] .*")) {
                tmp = Point.parsePoint(i, file, content);
                i = tmp.getIndex();
                node.putChild(tmp.getNode());
            }else {
                node.setContent(content);
                i++;
            }
        }else {
            i++;
        }

        for (; i<file.size() && !file.get(i).matches("^\\d+[.] .*") && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdział.*") && !file.get(i).matches("^DZIAŁ.*"); i++) {
            if(node.getContent().equals("")) {
                node.setContent(file.get(i));
            }else {
                node.addContent(file.get(i));
            }
        }

        for (; i<file.size() && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdział.*") && !file.get(i).matches("^DZIAŁ.*");) {
            tmp = Point.parsePoint(i, file, "");
            i = tmp.getIndex();
            node.putChild(tmp.getNode());
        }

        return new NodePair(i, node);
    }
}
