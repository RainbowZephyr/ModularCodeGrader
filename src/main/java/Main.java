import AbstractClasses.ArchiveExtractor;
import AbstractClasses.FilesHandler;
import AbstractClasses.TesterBaseClass;
import Milestone1.M1Tester;
import junit.framework.Test;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("logdir", true, "Sets path for logging directory");
        options.addOption("submissiondir", true, "Sets path for submission directory");
        options.addOption("ids", true, "Path to csv file mapping ids to tutorial groups");
        options.addOption("lab", true, "Choose lab test to run");
        options.addOption("clean", false, "Cleans any previous build directories, default: n");
        options.addOption("stats", false, "Enables statsistics, default: n");
        options.addOption("maven", true, "Path to the maven executable");
        options.addOption("builtin_maven", true, "Use builtin-maven, default: y");

        options.addOption("java", true, "Path to the java executable");

        options.addOption("timeout", true, "Choose lab test to run, default: 10s");
        options.addOption("threads", true, "Choose number of parallel tests to run, default: number of cores");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);


//        if (cmd.hasOption("lab") && cmd.hasOption("ids") && cmd.hasOption("submissiondir") && cmd.hasOption("python")) {
//            String submissionDir = (String) cmd.getParsedOptionValue("submissiondir");
//            String idsFile = (String) cmd.getParsedOptionValue("ids");
//            String loggingDir = "";
//            String pythonPath = (String) cmd.getParsedOptionValue("python");
//            String javaPath = "";

//            int labNumber = Integer.parseInt((String) cmd.getParsedOptionValue("lab"));
        long timeout = 2;
        boolean clean = false;
        boolean stats = false;
        int threads = Runtime.getRuntime().availableProcessors();

//            if (cmd.hasOption("logdir")) {
//                loggingDir = (String) cmd.getParsedOptionValue("logdir");
//            }
//
//            if (cmd.hasOption("clean")) {
//                clean = true;
//            }
//
//            if (cmd.hasOption("threads")) {
//                threads = Integer.parseInt((String) cmd.getParsedOptionValue("threads"));
//            }
//
//            if (cmd.hasOption("stats")) {
//                stats = true;
//            }
//
//            if (cmd.hasOption("timeout")) {
//                timeout = Long.parseLong((String) cmd.getParsedOptionValue("timeout"));
//
//            }
//
//
//            File file = new File(idsFile);
//            if (!file.exists() || !file.isFile()) {
//                System.err.println("IDS FILE MUST EXIST");
//                return;
//            }
//
//            file = new File(submissionDir);
//
//            if (!file.exists() && !file.isDirectory()) {
//                System.out.println(file.getAbsolutePath());
//                System.err.println("SUBMISSION DIRECTORY MUST EXIST");
//                return;
//            }
//
//            file = new File(pythonPath);
//
//            if (!file.exists() || file.isDirectory()) {
//                System.out.println(file.getAbsolutePath());
//                System.err.println("PYTHON EXECUTABLE MUST EXIST");
//                return;
//            }
//
//            if (cmd.hasOption("java")) {
//                javaPath = (String) cmd.getParsedOptionValue("java");
//                file = new File(javaPath);
//
//                if (!file.exists() || file.isDirectory()) {
//                    System.out.println(file.getAbsolutePath());
//                    System.err.println("JAVA EXECUTABLE MUST EXIST");
//                    return;
//                }
//
//
//            }

//            file = null; // Allows for faster garbage collection


        int labNumber = 0;
//        FilesHandler.moveBinaryFile("maven-3.6.3.tar.xz", "/home/ahmed/Documents/game/m.tar.xz");
//        ArchiveExtractor.extractArchiveByExtension("/home/ahmed/Documents/game/m.tar.xz", "/home/ahmed/Documents/game/");

//        Files.walk(Paths.get("/home/ahmed/Documents/game/M1/build/submissions835_2019_060_20_41_15_368/")).
        switch (labNumber) {
            case 0:
                TesterBaseClass lab0 = new M1Tester("/home/ahmed/Documents/game/M1", "", "/home/ahmed/Documents/game/M1/logs", "", true, timeout, 2);
                lab0.run();
                lab0.generateGradesPerTutorial(stats);
                break;

            default:
                System.err.println("LAB TEST NOT YET IMPLEMENTED");
        }

//        } else {
//            System.err.println("ALL OPTIONS WITH THE EXCEPTION OF LOGDIR MUST BE DEFINED");
//        }

    }


//	static void Test3() {
//
//		String postRegex = Lab1.RegExInfToPostConverter.infixToPostfix("(a|b|ab)");
//
//		System.out.println(postRegex);
//
//		Lab1.ExpressionContainer ecResult = Lab1.RegexPostToNFA.constructNFAFromRegex(postRegex);
//
////		System.out.println(ecResult.exp);
////		System.out.println(ecResult.startState);
////		System.out.println(ecResult.finalState);
////		System.out.println(ecResult.allSymbols);
////		System.out.println(ecResult.states);
////		System.out.println(ecResult.transitions);
//
//		NFAReader nfaInput = new NFAReader(ecResult);
//
//		System.out.println(nfaInput.getNfaStates());
//		System.out.println(nfaInput.getNfaAlpha());
//		System.out.println(nfaInput.getNfaStartState());
//		System.out.println(nfaInput.getNfaFinalState());
//		System.out.println(nfaInput.getNfaTransition());
//
//		Lab1.DFA ttable = new Lab1.DFA();
//		ttable.convertNFA(nfaInput);
//
//		System.out.println(ttable.getNfaStates());
//		System.out.println(ttable.getDFAStates());
//		System.out.println(ttable.getAlphbetSymbols());
//		System.out.println(ttable.getAlphabetStates());
//		System.out.println(ttable.getAcceptedStates());
//
//		String input = "aaab";
//
//		System.out.println(ttable.evaluateString(input));
//
//	}

}
