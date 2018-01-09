package agh.cs.lab8;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {
    public static NodePair parsePoint(int i, ArrayList<String> file, String line) {
        Pattern pattern = Pattern.compile("^\\d+[.][ ]?");
        Matcher matcher;
        String title = "";
        String content;
        if(line.equals("")) {
            matcher = pattern.matcher(file.get(i));
            if (matcher.find()) title = matcher.group();
            content = file.get(i).replaceAll("^\\d+[.][ ]?", "");
        }else {
            matcher = pattern.matcher(line);
            if (matcher.find()) title = matcher.group();
            content = line.replaceAll("^\\d+[.][ ]?", "");
        }

        Node node = new Node(title, ("ust." + title.replaceAll("\\s","")), content);

        i++;
        for (; !file.get(i).matches("^\\d+[)] .*") && !file.get(i).matches("^\\d+[.] .*") && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdzia³.*") && !file.get(i).matches("^DZIA£.*"); i++) {
            node.addContent(file.get(i));
        }

        NodePair tmp;
        for (; i<file.size() && !file.get(i).matches("^\\d+[.] .*") && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdzia³.*") && !file.get(i).matches("^DZIA£.*");) {
            tmp = SubPoint.parseSubPoint(i, file);
            i = tmp.getIndex();
            node.putChild(tmp.getNode());
        }

        return new NodePair(i, node);
    }
}
