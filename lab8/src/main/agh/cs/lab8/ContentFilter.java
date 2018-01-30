package agh.cs.lab8;

import java.util.ArrayList;

public class ContentFilter {

    public static void contentFilter(Node head, ArrayList<String> args, Mode mode) throws WrongArgumentsException {
        if(args.size() == 1) {
            if (args.get(0).matches("^dział.*") || args.get(0).matches("^rozdział.*")) {
                head.print(args.get(0),mode);
            }else if (args.get(0).matches("^art[.].*")) {
                if (args.get(0).matches(".*[-].*")) {
                    String[] range = args.get(0).replaceAll("^art[.]", "").split("-");
                    head.printRange(("art." + range[0] + "."), ("art." + range[1] + "."));
                }
            }
        }else if (mode == Mode.Full) {
            if (!head.printDirect(args)) {
                throw new WrongArgumentsException();
            }
        }
    }
}
