package agh.cs.lab8;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Letter {
    public static NodePair parseLetter(int i, ArrayList<String> file) {
        Pattern pattern = Pattern.compile("^\\D[)][ ]?");
        Matcher matcher = pattern.matcher(file.get(i));
        String title = "";
        if (matcher.find()) title = matcher.group();
        String content = file.get(i).replaceAll("^\\D[)][ ]?", "");
        Node node = new Node(title, ("lit." + title.replaceAll("\\s","")), content);

        i++;
        for (; !file.get(i).matches("^\\D[)] .*") && !file.get(i).matches("^\\d+[.]") && !file.get(i).matches("^\\d+[)]") && !file.get(i).matches("^Art[.] .*") && !file.get(i).matches("^Rozdział.*") && !file.get(i).matches("^DZIAŁ.*"); i++) {
            node.addContent(file.get(i));
        }

        return new NodePair(i, node);
    }
}
