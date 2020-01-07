package Milestone1;

import AbstractClasses.ArchiveExtractor;
import AbstractClasses.FilesHandler;
import AbstractClasses.ProcessHandler;
import AbstractClasses.TesterBaseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M1Tester extends TesterBaseClass {

    private String unzipDir;
    private static Pattern srcRegex = Pattern.compile("(.+?)(src/?)");
    private String pomPath;

    public M1Tester(String workingDir, String mavenPath, String logDir, String idsFilePath, boolean cleanBuild, long timeOut, int threads) {
        super(workingDir, mavenPath, logDir, idsFilePath, cleanBuild, timeOut, threads);
        if (!workingDir.endsWith("/")) {
            this.setWorkingDir(workingDir + "/");
        }
        this.unzipDir = this.getWorkingDir() + "build/";


    }

    @Override
    public void test(String file) {
        Pattern regex = Pattern.compile("(\\w+)");
        Matcher match = regex.matcher(file);
        String id = "";
        if (match.find()) {
            id = match.group(1);

            String path = this.unzipDir + id + "/";
            int taskResult = 0;

            this.handleMavenProject(file, path, id);

            String command[] = {this.mavenPath,"-l", "maven_log.txt", "clean", "compile", "test"};
            ProcessHandler pHandler = new ProcessHandler(command, this.getTimeOut(), TimeUnit.SECONDS, pomPath);

            pHandler.spawn();


        }


    }

    private void handleMavenProject(String file, String path, String id) {
        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }
            ArchiveExtractor.extractArchiveByExtension(this.getWorkingDir() + file, path);
            List<String> srcPath = FilesHandler.searchDirectories(path, "src");

            if (!srcPath.isEmpty()) {
                String directories[] = new File(srcPath.get(0)).list();
                String basePath = srcPath.get(0);

                for (String dir : directories) {
                    FilesHandler.moveDirectory(Paths.get(basePath, dir).toString(), Paths.get(basePath, "main/java", dir).toString());
                }

                Matcher matcher = srcRegex.matcher(basePath);
                if (matcher.find()) {
                    List<String> pomFile = FilesHandler.readTestFile("pom.xml");
                    Files.write(Paths.get(matcher.group(1), "pom.xml"), pomFile);
                    this.pomPath = matcher.group(1);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.logEntry(id + " ERROR CANNOT CREATE FOLDER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
