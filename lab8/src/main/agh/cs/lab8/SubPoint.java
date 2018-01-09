package agh.cs.lab8;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubPoint {
    public static NodePair parseSubPoint(int i, ArrayList<String> file) {
        Pattern pattern = Pattern.compile("^\\d+[)][ ]?");
        Matcher matcher = pattern.matcher(file.get(i));
        String title = "";
        if (matcher.find()) title = matcher.group();
        String content = file.get(i).replaceAll("^\\d+[)][ ]?", "");
        Node node = new Node(title, ("pkt." + title.replaceAll("\\s","")), content);

        i++;
        for (; !file.get(i).matches("^\\D[)] .*") && !file.get(i).matches("^\\d+[)] .*") && !file.get(i).matches("^\\d+[.] .*") && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdzia³.*") && !file.get(i).matches("^DZIA£.*"); i++) {
            node.addContent(file.get(i));
        }

        NodePair tmp;
        for (; !file.get(i).matches("^\\d+[)] .*") && !file.get(i).matches("^\\d+[.] .*") && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdzia³.*") && !file.get(i).matches("^DZIA£.*");) {
            tmp = Letter.parseLetter(i, file);
            i = tmp.getIndex();
            node.putChild(tmp.getNode());
        }

        return new NodePair(i, node);
    }
}
