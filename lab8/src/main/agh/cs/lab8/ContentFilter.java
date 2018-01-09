package agh.cs.lab8;

import java.util.ArrayList;

public class ContentFilter {

    public static void contentFilter(Node head, ArrayList<String> args, Mode mode) throws WrongArgumentsException {
        if(args.size() == 1) {
            if (args.get(0).matches("^dzia³.*")) {
//                System.out.println("Hi");
                head.print(args.get(0),mode);
            }else if (args.get(0).matches("^rozdzia³.*")) {

            }else if (args.get(0).matches("^art[.].*")) {
                if (args.get(0).matches(".*[-].*")) {
                    String[] range = range(args.get(0).replaceAll("^art[.]", ""));
//                    head.printRange(range[0], range[1]);
                }
            }
        }else if (mode == Mode.Full) {
            if (!head.printDirect(args)) {
                throw new WrongArgumentsException();
            }
        }
    }

    private static String[] range(String line) {
        String[] range = new String[2];
        line.replaceAll("^art[.]", "");
        range[0] = line.replaceAll("\\d+[.]$", "");
        range[1] = line.replaceAll("^\\d+[.]", "");
        return range;
    }
}
