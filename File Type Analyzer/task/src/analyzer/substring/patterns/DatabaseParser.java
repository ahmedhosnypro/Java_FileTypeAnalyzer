package analyzer.substring.patterns;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseParser {

    private DatabaseParser() {
    }

    /*
     * This class is used to parse the database file and return the patterns with their respective file types and priorities.
     *  Patterns should be sorted by priority in descending order.
     * The database file is a text file with the following format:
     * priority; pattern; file type
     * 4;"PK";"Zip archive"
     * 7;"word/_rels";"MS Office Word 2007+"
     * 7;"ppt/_rels";"MS Office PowerPoint 2007+"
     * 7;"xl/_rels";"MS Office Excel 2007+
     */

    public static List<Pattern> parse(String databasePath) {
        List<Pattern> patterns = new ArrayList<>();
        try (InputStream fileInputStream = new FileInputStream(databasePath)) {
            var bytes = fileInputStream.readAllBytes();
            var text = new String(bytes);
            var lines = text.split("\n");
            for (var line : lines) {
                var parts = line.split(";");
                var priority = Integer.parseInt(parts[0]);
                var pattern = parts[1].replace("\"", "");
                var fileType = parts[2].replace("\"", "");
                patterns.add(new Pattern(priority, pattern, fileType));
            }

            // sort the patterns by priority in descending order
            patterns.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
            return patterns;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
