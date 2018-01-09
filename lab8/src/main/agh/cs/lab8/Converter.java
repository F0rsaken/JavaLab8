package agh.cs.lab8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by student25 on 2017-12-12.
 */
public class Converter {
    private ArrayList<String> file = new ArrayList<>();

    public Converter (String address) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(address));
        this.file = Cleaner.clean(br, br.readLine());
        br.close();
    }

    public Node convert() throws IOException{
        Node text = new Node("", "start", file.get(0));

        //dodanie wstêpu do ustawy
        int i;
        for(i = 1; !file.get(i).matches("^DZIA£.*") && !file.get(i).matches("^Rozdzia³.*"); i++) {
            text.addContent(file.get(i));
        }

        text.parseFile(i, file);
        return text;
    }
}
