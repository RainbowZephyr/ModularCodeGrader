package Milestone1;

import AbstractClasses.ArchiveExtractor;
import AbstractClasses.FilesHandler;
import AbstractClasses.TesterBaseClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M1Tester extends TesterBaseClass {

    private String unzipDir;

    public M1Tester(String workingDir, String mavenPath, String logDir, String idsFilePath, boolean cleanBuild, long timeOut, int threads) {
        super(workingDir, mavenPath, logDir, idsFilePath, cleanBuild, timeOut, threads);
        if (!workingDir.endsWith("/")) {
            this.setWorkingDir(workingDir + "/");
        }
        this.unzipDir =  this.getWorkingDir() + "build/";

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
            if (this.cleanBuild) {
                this.unzipAndMoveFiles(file, path, id);


            }
        }


    }

    private void unzipAndMoveFiles(String file, String path, String id) {
        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }
            ArchiveExtractor.extractArchiveByExtension(this.getWorkingDir() + file, path);
//            this.moveFiles(path, path, "py");
            FilesHandler.moveDirectory();
            System.out.println("OUTPUT PATH " + path +" " + file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.logEntry(id + " ERROR CANNOT CREATE FOLDER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
