package analyzer;

import analyzer.substring.finder.SubstringFinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileTypeChecker {
    SubstringFinder algorithm;

    void search(String path, String pattern, String result) {
        try (InputStream inputStream = new FileInputStream(path)) {
            var bytes = inputStream.readAllBytes();
            var text = new String(bytes);

            // start time
            long startTime = System.nanoTime();

            if (algorithm.search(pattern, text)) {
                System.out.println(result);
            } else {
                System.out.println("Unknown file type");
            }

            // end time
            long endTime = System.nanoTime();
            System.out.println("Time taken: " + (endTime - startTime) / 1000000000.0 + " seconds");
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
