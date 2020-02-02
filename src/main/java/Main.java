import AbstractClasses.TesterBaseClass;
import TesterImplementation.TesterClass;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("logdir", true, "Sets path for logging directory (optional)");
        options.addOption("submissiondir", true, "Sets path for submission directory");
        options.addOption("help", false, "Prints help");
        options.addOption("h", false, "Prints help");
        options.addOption("cleanbuild", false, "Deletes build folder on given submission dir");

        options.addOption("mavenpath", true, "Path to the maven executable");
        options.addOption("testpath", true, "Path to the java test file");


        options.addOption("timeout", true, "Choose lab test to run, default: 300s");
        options.addOption("threads", true, "Choose number of parallel tests to run, default: number of cores");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("help") || cmd.hasOption("h")) {
            StringBuilder help = new StringBuilder();

            help.append("-");
            help.append(options.getOption("submissiondir").getOpt());
            help.append("\t");
            help.append(options.getOption("submissiondir").getDescription());
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

            help.append("-");
            help.append(options.getOption("cleanbuild").getOpt());
            help.append("\t");
            help.append(options.getOption("cleanbuild").getDescription());
            help.append("\n");

            System.out.println(help.toString());

        } else if (cmd.hasOption("submissiondir")
                && cmd.hasOption("mavenpath")
                && cmd.hasOption("testpath")) {
            String submissionDir = (String) cmd.getParsedOptionValue("submissiondir");
            String idsFile = (String) cmd.getParsedOptionValue("ids");
            String loggingDir = "";
            String mavenPath = (String) cmd.getParsedOptionValue("mavenpath");
            String testPath = (String) cmd.getParsedOptionValue("testpath");

            long timeout = 300;
            boolean stats = false;
            boolean cleanBuild = false;
            int threads = 0;


            if (cmd.hasOption("logdir")) {
                loggingDir = (String) cmd.getParsedOptionValue("logdir");
            }

            if (cmd.hasOption("threads")) {
                threads = Integer.parseInt((String) cmd.getParsedOptionValue("threads"));
            }

            if (threads == 0) {
                 threads = Runtime.getRuntime().availableProcessors() / 2;
            }

            if (cmd.hasOption("stats")) {
                stats = true;
            }

            if (cmd.hasOption("cleanbuild")) {
                cleanBuild = true;
            }

            if (cmd.hasOption("timeout")) {
                timeout = Long.parseLong((String) cmd.getParsedOptionValue("timeout"));
            }

            File file; // = new File(idsFile);


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

            TesterBaseClass tester = new TesterClass(submissionDir, mavenPath, testPath, loggingDir, cleanBuild, timeout, threads);

            tester.run();
            tester.generateGrades();
//            tester.generateGradesPerTutorial(stats);

        } else {
            System.err.println("ALL REQUIRED OPTIONS MUST BE DEFINED, RUN WITH -help ARGUMENT TO SEE OPTIONS");
        }
//      Pattern testRegex = Pattern.compile("Tests\\s+run:\\s+\\d+");
//
//        BufferedReader reader;
//        try {
//            reader = new BufferedReader(new FileReader(Paths.get("/tmp/M1/build/922/Game1/", "maven_log.txt").toString()));
//            String line = reader.readLine();
//            Matcher match;
//
//            while (line != null) {
//                match =   testRegex.matcher(line);
////                System.out.println(line);
//                if(match.find()){
//                    System.out.println(line);
////                    this.getIdsToGradeMap().put(id, line);
//                    break;
//                }
//
//
//                // read next line
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
////            System.err.println("FAILED READIND GRADES FOR " + id);
//        } finally {
//            // faster garbage collection
//            reader = null;
//        }
    }


}
