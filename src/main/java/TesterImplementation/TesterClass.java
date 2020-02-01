package TesterImplementation;

import AbstractClasses.ArchiveExtractor;
import AbstractClasses.FilesHandler;
import AbstractClasses.ProcessHandler;
import AbstractClasses.TesterBaseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesterClass extends TesterBaseClass {

    private String unzipDir;
    final private static Pattern srcRegex = Pattern.compile("(.+?)(src/?)");
    final private static Pattern fileRegex = Pattern.compile("submissions(\\d+)");

    private String pomPath;

    public TesterClass(String workingDir, String mavenPath, String testPath, String logDir, String idsFilePath, boolean cleanBuild, long timeOut, int threads) {
        super(workingDir, mavenPath, testPath, logDir, idsFilePath, cleanBuild, timeOut, threads);
        if (!workingDir.endsWith("/")) {
            this.setWorkingDir(workingDir + "/");
        }
        this.unzipDir = Paths.get(this.getWorkingDir(), "build").toString();

        if(cleanBuild){
            try {
                System.out.println("#########DELEETING");
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
//        Pattern regex = Pattern.compile("(\\w+)");
        Matcher match = fileRegex.matcher(file);
        String id = "";
        if (match.find()) {
            id = match.group(1);

            String path = Paths.get(this.unzipDir, id).toString();
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
//                System.out.println("$$$$$$$$$$$ " + basePath);

                for (String dir : directories) {
//                    System.out.println("MOVING " + Paths.get(basePath, dir).toString() + " TO " + Paths.get(basePath, "main/java", dir).toString());
                    FilesHandler.moveDirectory(Paths.get(basePath, dir).toString(), Paths.get(basePath, "main/java", dir).toString());
                }

                Matcher matcher = srcRegex.matcher(basePath);
                if (matcher.find()) {
                    List<String> pomFile = FilesHandler.readTestFile("pom.xml");
                    Files.write(Paths.get(matcher.group(1), "pom.xml"), pomFile);
                    this.pomPath = matcher.group(1);
                }

//                System.out.println("TEST " + testPath);
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
