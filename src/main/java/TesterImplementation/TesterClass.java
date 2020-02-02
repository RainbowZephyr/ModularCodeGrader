package TesterImplementation;

import AbstractClasses.ArchiveExtractor;
import AbstractClasses.FilesHandler;
import AbstractClasses.ProcessHandler;
import AbstractClasses.TesterBaseClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesterClass extends TesterBaseClass {

    private String unzipDir;
    private String pomPath;
    final private static Pattern srcRegex = Pattern.compile("(.+?)(src/?)");
    final private static Pattern fileRegex = Pattern.compile("t(\\d+)");



    public TesterClass(String workingDir, String mavenPath, String testPath, String logDir,
                       boolean cleanBuild, long timeOut, int threads) {
        super(workingDir, mavenPath, testPath, logDir, cleanBuild, timeOut, threads);

        if (!workingDir.endsWith("/")) {
            this.setWorkingDir(workingDir + "/");
        }
        this.unzipDir = Paths.get(this.getWorkingDir(), "build").toString();

        if (cleanBuild) {
            try {
                Files.walk(Paths.get(this.unzipDir))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void test(String file) {
        Matcher match = fileRegex.matcher(file);
        String id = "";
        if (match.find()) {
            id = match.group(1);

            String path = Paths.get(this.unzipDir, id).toString();
            this.handleMavenProject(file, path, id);

            String command[] = {this.mavenPath, "-l", "maven_log.txt", "clean", "compile", "test"};

            Map<String, String> env = new HashMap<>();
            env.put("MAVEN_OPTS", "-XX:CICompilerCount=2 -XX:ParallelGCThreads=2");

            ProcessHandler pHandler = new ProcessHandler(command, this.getTimeOut(), TimeUnit.SECONDS, pomPath, env);

            boolean converged = pHandler.spawn();

            System.out.println("FINISHED COMPILING TEAM " + id);
            this.getIdsToTestFile().put(id, Paths.get(this.pomPath, "maven_log.txt").toString());

        }
    }

    private void handleMavenProject(String file, String path, String id) {
        try {

            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }

            ArchiveExtractor.extractArchiveByExtension(this.getWorkingDir() + file, path);
            List<String> srcPath = FilesHandler.searchDirectories(path, "src");

            // Check for non empty submissions
            if (!srcPath.isEmpty()) {
                String directories[] = new File(srcPath.get(0)).list();
                String basePath = srcPath.get(0);

                // Move each directory to match the maven format
                for (String dir : directories) {

                    FilesHandler.moveDirectory(Paths.get(basePath, dir).toString(), Paths.get(basePath, "main/java", dir).toString());
                }

                // Scan the new path for the pom file to set maven location later when testing
                Matcher matcher = srcRegex.matcher(basePath);
                if (matcher.find()) {
                    List<String> pomFile = FilesHandler.readTestFile("pom.xml");
                    Files.write(Paths.get(matcher.group(1), "pom.xml"), pomFile);
                    this.pomPath = matcher.group(1);
                }

                String testFileName = new File(this.testPath).getName();
                FilesHandler.copyFile(this.testPath, Paths.get(basePath, "test/java").toString(), testFileName, true);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.logEntry(id + " ERROR CANNOT CREATE FOLDER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

