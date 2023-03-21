package analyzer;

import analyzer.substring.finder.KMP;
import analyzer.substring.finder.NaiveSearch;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Error");
            System.exit(0);
        }

//        String algorithm = args[0];
        // stage 3 use only kmp algorithm
        String algorithm = "--KMP";
        String path = args[0];
        String pattern = args[1];
        String result = args[2];

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
