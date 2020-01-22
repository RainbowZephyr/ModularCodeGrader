import AbstractClasses.TesterBaseClass;
import TesterImplementation.TesterClass;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("logdir", true, "Sets path for logging directory (optional)");
        options.addOption("submissiondir", true, "Sets path for submission directory");
        options.addOption("ids", true, "Path to csv file mapping ids to tutorial groups");
        options.addOption("help", false, "Prints help");
        options.addOption("h", false, "Prints help");


        options.addOption("stats", false, "Enables statistics (optional)");
        options.addOption("mavenpath", true, "Path to the maven executable");
        options.addOption("testpath", true, "Path to the java test file");


        options.addOption("timeout", true, "Choose lab test to run, default: 300s");
        options.addOption("threads", true, "Choose number of parallel tests to run, default: number of cores");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("help") || cmd.hasOption("h")) {
            StringBuilder help = new StringBuilder();
            help.append("-");
            help.append(options.getOption("logdir").getOpt());
            help.append("\t");
            help.append(options.getOption("logdir").getDescription());
            help.append("\n");

            help.append("-");
            help.append(options.getOption("submissiondir").getOpt());
            help.append("\t");
            help.append(options.getOption("submissiondir").getDescription());
            help.append("\n");

            help.append("-");
            help.append(options.getOption("stats").getOpt());
            help.append("\t");
            help.append(options.getOption("stats").getDescription());
            help.append("\n");

            help.append("-");
            help.append(options.getOption("mavenpath").getOpt());
            help.append("\t");
            help.append(options.getOption("mavenpath").getDescription());
            help.append("\n");

            help.append("-");
            help.append(options.getOption("testpath").getOpt());
            help.append("\t");
            help.append(options.getOption("testpath").getDescription());
            help.append("\n");

            help.append("-");
            help.append(options.getOption("timeout").getOpt());
            help.append("\t");
            help.append(options.getOption("timeout").getDescription());
            help.append("\n");

            help.append("-");
            help.append(options.getOption("threads").getOpt());
            help.append("\t");
            help.append(options.getOption("threads").getDescription());
            help.append("\n");


            System.out.println(help.toString());

        } else if (cmd.hasOption("lab")
                && cmd.hasOption("ids")
                && cmd.hasOption("submissiondir")
                && cmd.hasOption("mavenpath")
                && cmd.hasOption("testpath")) {
            String submissionDir = (String) cmd.getParsedOptionValue("submissiondir");
            String idsFile = (String) cmd.getParsedOptionValue("ids");
            String loggingDir = "";
            String mavenPath = (String) cmd.getParsedOptionValue("mavenpath");
            String testPath = (String) cmd.getParsedOptionValue("testpath");

            long timeout = 300;
            boolean stats = false;
            int threads = Runtime.getRuntime().availableProcessors();

            if (cmd.hasOption("logdir")) {
                loggingDir = (String) cmd.getParsedOptionValue("logdir");
            }

            if (cmd.hasOption("threads")) {
                threads = Integer.parseInt((String) cmd.getParsedOptionValue("threads"));
            }

            if (cmd.hasOption("stats")) {
                stats = true;
            }

            if (cmd.hasOption("timeout")) {
                timeout = Long.parseLong((String) cmd.getParsedOptionValue("timeout"));
            }

            File file = new File(idsFile);
            if (!file.exists() || !file.isFile()) {
                System.err.println("IDS FILE MUST EXIST");
                return;
            }

            file = new File(submissionDir);
            if (!file.exists() && !file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                System.err.println("SUBMISSION DIRECTORY MUST EXIST");
                return;
            }


            file = new File(mavenPath);
            if (!file.exists() || file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                System.err.println("MAVEN EXECUTABLE MUST EXIST");
                return;
            }

            file = new File(testPath);
            if (!file.exists() || file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                System.err.println("TEST CLASS MUST EXIST");
                return;
            }

            TesterBaseClass tester = new TesterClass("/home/ahmed/Documents/game/M1", "/usr/bin/mvn", "/home/ahmed/Documents/game/M1/logs", "", true, timeout, 16);
            tester.run();
            tester.generateGradesPerTutorial(stats);

        } else {
            System.err.println("ALL REQUIRED OPTIONS MUST BE DEFINED, RUN WITH -help ARGUMENT TO SEE OPTIONS");
        }

    }


}
