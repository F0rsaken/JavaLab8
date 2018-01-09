package agh.cs.lab8;

import org.apache.commons.cli.*;
import java.io.IOException;

/**
 * Created by student22 on 2017-11-28.
 */

public class MainReader {
    public static void main(String[] args) {
        try {
            Options options = new Options();
            Option localisationOpt = new Option("l", true, "defines localisation of file");
            Option modeOpt = new Option("m", true, "defines, whether to display full text (1) or just table of contents (2)");
            Option contentOpt = new Option("c", true, "set what content should be displayed, single articles list without white signs and separated with comas, range with '-' and if empty, will list full file");
            contentOpt.setOptionalArg(true);

            options.addOption(localisationOpt);
            options.addOption(modeOpt);
            options.addOption(contentOpt);
            options.addOption("h", "help", false, "show possible options");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("h")) {
                HelpFormatter helper = new HelpFormatter();
                helper.printHelp("myApp", options);
            }

            if(!cmd.hasOption("l")) {
                throw new ParseException("missing option: l");
            }else if(!cmd.hasOption("m")) {
                throw new ParseException("missing option: m");
            }else if(!cmd.hasOption("c")) {
                throw new ParseException("missing option: c");
            }

            Converter file = new Converter(cmd.getOptionValue("l"));
            Node text = file.convert();

            Mode mode;
            if (cmd.getOptionValue("m").equals("1")) {
                mode = Mode.Full;
            }else if (cmd.getOptionValue("m").equals("2")) {
                mode = Mode.Table;
            }else {
              throw new WrongArgumentsException();
            }

            if (mode == Mode.Full && cmd.getOptionValue("c") == null) {
                text.printFull();
            }

            if (mode == Mode.Table && cmd.getOptionValue("c") == null) {
                text.printTableOfContent();
            }

            ArgumentParser parsedArgs = new ArgumentParser(args);
            parsedArgs.filterArg();
            ContentFilter.contentFilter(text, parsedArgs.getParsedArg(), mode);

        }catch (ParseException | IOException | WrongArgumentsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
