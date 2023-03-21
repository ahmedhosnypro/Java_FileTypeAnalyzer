package analyzer;

import analyzer.substring.finder.SubstringFinder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileTypeChecker {
    SubstringFinder algorithm;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    void search(String path, String pattern, String result) {
        var output = new CopyOnWriteArrayList<String>();

        File directory = new File(path);
        var fileList = directory.listFiles();
        assert fileList != null;

        // Create a list of callables for each file to invoke them in parallel using executor.invokeAll()
        // to search in it and write results to output stringbuilder
        var fileProcessors = new ArrayList<Future<?>>();

        // search in files and write results to output stringbuilder
        for (var file : fileList) {
            fileProcessors.add(executor.submit(() -> search(file, pattern, result, output)));
        }

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

    void search(File file, String pattern, String result, List output) {
        try (InputStream inputStream = new FileInputStream(file)) {
            var bytes = inputStream.readAllBytes();
            var text = new String(bytes);

            if (algorithm.search(pattern, text)) {
                output.add(file.getName() + ": " + result);
            } else {
                output.add(file.getName() + ": " + "Unknown file type");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAlgorithm(SubstringFinder algorithm) {
        this.algorithm = algorithm;
    }
}
