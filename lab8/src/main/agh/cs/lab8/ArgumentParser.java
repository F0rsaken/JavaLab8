package agh.cs.lab8;

import java.util.ArrayList;

public class ArgumentParser {
    private ArrayList<String> parsedArg = new ArrayList<>();
    private String[] args;

    public ArgumentParser (String[] args) {
        this.args = args;
    }

    public void filterArg() throws WrongArgumentsException {
        int i = 0;
        while (!this.args[i].equals("-c")) { i++; }
        i++;
        for (String a : args) { a = a.toLowerCase(); }

        for (; i < args.length; i++) {
            if (!(i+1 < args.length)) {
                throw new WrongArgumentsException();
            }else {
                if(args[i].matches("^dzia³.*")) {
                    this.parsedArg.add(args[i] + args[i+1]);
                    i++;
                }else if (args[i].matches("^rozdzia³.*")) {
                    this.parsedArg.add(args[i] + args[i+1]);
                    i++;
                }else if (args[i].matches("^art.*")) {
                    this.parsedArg.add(args[i] + args[i+1]);
                    i++;
                }else if (args[i].matches("^ust.*")) {
                    this.parsedArg.add(args[i] + args[i+1]);
                    i++;
                }else if (args[i].matches("^pkt.*")) {
                    this.parsedArg.add(args[i] + args[i+1]);
                    i++;
                }else if (args[i].matches("^lit.*")) {
                    this.parsedArg.add(args[i] + args[i+1]);
                    i++;
                }else {
                    throw new WrongArgumentsException();
                }
            }
        }
    }

    public ArrayList<String> getParsedArg() {
        return parsedArg;
    }
}
