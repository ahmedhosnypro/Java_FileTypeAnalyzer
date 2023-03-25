package analyzer;

import analyzer.substring.finder.KMP;
import analyzer.substring.finder.NaiveSearch;
import analyzer.substring.finder.RabinKarp;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Error");
            System.exit(0);
        }

//        String algorithm = "--KMP";
//        String algorithm = "--naive";
        String algorithm = "--RK";
        String databasePath = args[0];
        String filesParentDirPath = args[1];

        FileTypeChecker fileTypeChecker = new FileTypeChecker();
        switch (algorithm) {
            case "--naive" -> fileTypeChecker.setAlgorithm(new NaiveSearch());
            case "--KMP" -> fileTypeChecker.setAlgorithm(new KMP());
            case "--RK" -> fileTypeChecker.setAlgorithm(new RabinKarp());
            default -> {
                System.out.println("Error");
                System.exit(0);
            }
        }

        fileTypeChecker.search(databasePath, filesParentDirPath);
    }
}
