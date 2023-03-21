package analyzer;

import analyzer.substring.finder.SubstringFinder;
import analyzer.substring.patterns.DatabaseParser;
import analyzer.substring.patterns.Pattern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileTypeChecker {
    SubstringFinder algorithm;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    void search(String filesParentDirPath, String databasePath) {
        //get a list of files in the directory
        File directory = new File(filesParentDirPath);
        var fileList = directory.listFiles();
        assert fileList != null;

        // get a list of patterns from the database
        var patterns = DatabaseParser.parse(databasePath);

        // Create a list of futures for each file to be processed
        // to block the main thread till all files are processed
        var fileProcessors = new ArrayList<Future<?>>();

        // add a task to the executor for each file to be processed
        // search in files and write results to output stringbuilder
        var output = new CopyOnWriteArrayList<String>();
        for (var file : fileList) {
            fileProcessors.add(executor.submit(() -> search(file, patterns, output)));
        }

        // wait for all files to be processed
        try {
            for (var fileProcessor : fileProcessors) {
                fileProcessor.get();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        output.forEach(System.out::println);
    }

    void search(File file, List<Pattern> patterns, List<String> output) {
        try (InputStream inputStream = new FileInputStream(file)) {
            var bytes = inputStream.readAllBytes();
            var text = new String(bytes);
            search(patterns, text, file.getName(), output);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void search(List<Pattern> patterns, String text, String fileName, List<String> output) {
        for (var pattern : patterns) {
            if (algorithm.search(pattern.getPatternToSearch(), text)) {
                output.add(fileName + ": " + pattern.getFileType());
                return;
            }
        }
        output.add(fileName + ": " + "Unknown file type");
    }

    public void setAlgorithm(SubstringFinder algorithm) {
        this.algorithm = algorithm;
    }
}
