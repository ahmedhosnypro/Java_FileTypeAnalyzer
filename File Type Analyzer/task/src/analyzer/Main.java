package analyzer;

import analyzer.substring.finder.KMP;
import analyzer.substring.finder.NaiveSearch;

public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Error");
            System.exit(0);
        }

        String algorithm = args[0];
        String path = args[1];
        String pattern = args[2];
        String result = args[3];

        FileTypeChecker fileTypeChecker = new FileTypeChecker();
        switch (algorithm) {
            case "--naive" -> fileTypeChecker.setAlgorithm(new NaiveSearch());
            case "--KMP" -> fileTypeChecker.setAlgorithm(new KMP());
            default -> {
                System.out.println("Error");
                System.exit(0);
            }
        }

        fileTypeChecker.search(path, pattern, result);
    }
}
