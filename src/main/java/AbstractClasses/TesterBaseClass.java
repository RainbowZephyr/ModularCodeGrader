package AbstractClasses;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.nio.file.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public abstract class TesterBaseClass {

    private SortedMap<Integer, String> idsToGradeMap;

    private ConcurrentHashMap<String, String> idsToTestFile;

    private static ThreadPoolExecutor threadPool;
//    private static final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);


    private ConcurrentHashMap<String, Boolean> filesHandled;
    private String logDir;
    private String workingDir;
    private ArrayList<String> files;
    private BlockingQueue<String> logQueue;
    protected boolean cleanBuild;
    private long timeOut;
    private static int threads;
    protected String mavenPath;
    protected String testPath;

    final private static Pattern testRegex = Pattern.compile("Tests\\s+run:\\s+\\d+");

    public TesterBaseClass(String workingDir, String mavenPath, String testPath, String logDir,
                           boolean cleanBuild, long timeOut, int threads) {
        this.workingDir = workingDir;
        this.mavenPath = mavenPath;
        this.logDir = logDir;
        this.testPath = testPath;


        this.cleanBuild = cleanBuild;
        this.timeOut = timeOut;

        TesterBaseClass.threads = threads;

        File folder = new File(this.workingDir);

        this.files = Arrays.stream(folder.listFiles()).map(File::getName).filter(TesterBaseClass::fileFilter).collect(toCollection(ArrayList<String>::new));

        this.idsToGradeMap = Collections.synchronizedSortedMap(new TreeMap<>());
        this.idsToTestFile = new ConcurrentHashMap<>();

        this.filesHandled = new ConcurrentHashMap<>();


        for (String file : files) {
            this.filesHandled.put(file, false);
        }

        this.logQueue = new LinkedBlockingQueue();

    }

    private static boolean fileFilter(String file) {
        return (file.endsWith("zip") || file.endsWith("gz") || file.endsWith("rar") || file.endsWith("xz"));
    }

    public void run() {
        System.out.println("THREAD POOL THREADS " + getThreadPool().getActiveCount());
        ArrayList<Future> futures = new ArrayList<>(this.files.size());

        for (String file : this.files) {

            System.out.println("FILE " + file + " THREAD POOL THREADS " + getThreadPool().getActiveCount());
            Future future = getThreadPool().submit(() -> this.test(file));
            futures.add(future);

        }

        for (Future future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                future.cancel(true);
            }
        }

        System.out.println("SHUTTING DOWN THREAD POOL THREADS " + getThreadPool().getActiveCount());

        getThreadPool().shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!getThreadPool().awaitTermination(timeOut, TimeUnit.MINUTES)) {
                getThreadPool().shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!getThreadPool().awaitTermination(timeOut, TimeUnit.MINUTES))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            getThreadPool().shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }


        try {

            if (!Files.exists(Paths.get(this.logDir))) {
                Files.createDirectories(Paths.get(this.logDir));
            }

            File logFile = new File(this.logDir + "/log.log");


            FileWriter logFileWriter = new FileWriter(logFile, true);
            String entry;
            while (!logQueue.isEmpty()) {
                entry = logQueue.take();
                logFileWriter.write(entry + "\n");
            }

            logFileWriter.close();

            idsToGradeMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));


        } catch (Exception e) {
            System.err.println("Cannot create log directory: " + this.logDir);
        }

        threadPool = null;

    }

    public void generateGrades(){
        ArrayList<Future> futures = new ArrayList<>();

        idsToTestFile.forEach((id, logPath) ->{

            Future future = getThreadPool().submit(() -> this.extractGrade(id, logPath));
            futures.add(future);

        });

        for (Future future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                future.cancel(true);
            }
        }

        System.out.println("SHUTTING DOWN THREAD POOL THREADS " + getThreadPool().getActiveCount());

        getThreadPool().shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!getThreadPool().awaitTermination(timeOut, TimeUnit.MINUTES)) {
                getThreadPool().shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!getThreadPool().awaitTermination(timeOut, TimeUnit.MINUTES))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            getThreadPool().shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(Paths.get(workingDir,"grades.txt").toString());
            BufferedWriter bw = new BufferedWriter(writer);

            Set keySet = idsToGradeMap.keySet();
            Iterator iterator = keySet.iterator();
            String v;
            int k;
            while (iterator.hasNext()) {
                k = (int) iterator.next();
                v = idsToGradeMap.get(k);

                bw.write("Team `" + k +"`: " + v);
                bw.write("\n");

            }
//            for (Map.Entry<String, String> entry : idsToGradeMap.entrySet()) {
//                String k = entry.getKey();
//                String v = entry.getValue();
//                bw.write("Team `" + k +"`: " + v);
//                bw.write("\n");
//            }

//            idsToGradeMap.forEach((k, v) -> {
//                try {
//                    bw.write("Team `" + k +"`: " + v);
//                    bw.write("\n");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });

            bw.flush();
            bw.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void extractGrade(String id, String logPath) {
        BufferedReader reader;
        try {

            reader = new BufferedReader(new FileReader(new File(Paths.get(logPath).toString())));
            String line = reader.readLine();
            Matcher match;

            while (line != null) {
                match =  testRegex.matcher(line);

                if(match.find()){
                    this.getIdsToGradeMap().put(Integer.parseInt(id), line);
                    break;
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FAILED READIND GRADES FOR " + id);
        } finally {
            // Faster garbage collection
            reader = null;
        }
    }


    protected void logEntry(String entry) {
        try {
            this.logQueue.put(new Timestamp(System.currentTimeMillis()) + " " + entry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public abstract void test(String file);


    public SortedMap<Integer, String> getIdsToGradeMap() {
        return idsToGradeMap;
    }

    public static ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
        }

        return threadPool;
    }


    public String getWorkingDir() {
        return workingDir;
    }

    public BlockingQueue<String> getLogQueue() {
        return logQueue;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public long getTimeOut() {
        return this.timeOut;
    }

    public ConcurrentHashMap<String, String> getIdsToTestFile() {
        return idsToTestFile;
    }


}
