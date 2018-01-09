package agh.cs.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cleaner {
    public static ArrayList<String> clean(BufferedReader br, String line) throws IOException {
        ArrayList<String> tab = new ArrayList<>();
        String tmp;
        while (line != null) {
            if (line.matches("^.Kancelaria Sejmu.*")) {
                line = br.readLine();
                line = br.readLine();
            }

            if (line.matches(".*[-]$")) {
                line = line.replaceAll("[-]$","");
                tmp = br.readLine();
                line += tmp;
            }

            if (!line.matches(".*[-]$")) {
                tab.add(line);
                line = br.readLine();
            }
        }

        return tab;
    }
}
