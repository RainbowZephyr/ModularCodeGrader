package AbstractClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class FilesHandler {

    public static void moveDirectory(String sourcePath, String destinationPath) {
        try {
            File destinationDirectory = new File(destinationPath);
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs();
            }

            Files.move(new File(sourcePath).toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String sourcePath, String destinationPath, String destinationName, boolean createDirs) {
        try {
            File destinationDirectory = new File(destinationPath);
            if(createDirs) {
                if (!destinationDirectory.exists()) {
                    destinationDirectory.mkdirs();
                }
            }
            Files.copy(Paths.get(sourcePath), Paths.get(destinationPath, destinationName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveFiles(String sourceDirectory, String destinationDirectory, String suffix) {
        final Pattern fileNameRegex = Pattern.compile("(\\w+)\\." + suffix);
        Matcher matcher;
        try {
            ArrayList<String> files = Files.walk(Paths.get(sourceDirectory))
                    .filter(Files::isRegularFile)
                    .map(f -> f.toAbsolutePath().toString())
                    .filter(p -> p.endsWith(suffix))
                    .collect(Collectors.toCollection(ArrayList::new));

            for (String file : files) {
                matcher = fileNameRegex.matcher(file);

                if (Paths.get(file).getParent().toAbsolutePath().equals(Paths.get(destinationDirectory).toAbsolutePath())) {
                    continue;
                }

                if (matcher.find()) {
                    Files.move(Paths.get(file), Paths.get(destinationDirectory + matcher.group(1) + "." + suffix));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static List<String> search(String directory, String suffix) {

        ArrayList<String> files = new ArrayList<>();
        try {
            files = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .map(f -> f.toAbsolutePath().toString())
                    .filter(p -> p.endsWith(suffix))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }

    public static List<String> searchDirectories(String directory, String suffix) {

        ArrayList<String> files = new ArrayList<>();
        try {
            files = Files.walk(Paths.get(directory))
                    .filter(Files::isDirectory)
                    .map(f -> f.toAbsolutePath().toString())
                    .filter(p -> p.endsWith(suffix))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }

    public static List<String> readTestFile(String path) {
        try {
            String absolutePath;
            if (!path.startsWith("/")) {
                absolutePath = "/" + path;
            } else {
                absolutePath = path;
            }

            InputStream resourceStream = FilesHandler.class.getResourceAsStream(absolutePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceStream));

            List<String> lines = bufferedReader.lines().collect(Collectors.toList());
            lines = lines.stream().filter(v -> !v.isEmpty()).collect(toCollection(ArrayList::new));

            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void moveBinaryFile(String sourcePath, String destinationPath) {
        try {
            String absolutePath;
            if (!sourcePath.startsWith("/")) {
                absolutePath = "/" + sourcePath;
            } else {
                absolutePath = sourcePath;
            }

            File destinationFile = new File(destinationPath);

            if (destinationFile.exists()) {
                throw new IOException("File already exists");
            }


            InputStream inputStream = FilesHandler.class.getResourceAsStream(absolutePath);
            BufferedInputStream biStream = new BufferedInputStream(inputStream);

            OutputStream outputStream = new FileOutputStream(destinationPath);
            BufferedOutputStream boStream = new BufferedOutputStream(outputStream);


            int byteRead;

            while ((byteRead = biStream.read()) != -1) {
                boStream.write(byteRead);
            }

            boStream.flush();

            biStream.close();
            inputStream.close();

            boStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
