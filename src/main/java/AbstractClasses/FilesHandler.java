package AbstractClasses;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilesHandler {

    public static void moveDirectory(String sourcePath, String destinationPath){

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

}
