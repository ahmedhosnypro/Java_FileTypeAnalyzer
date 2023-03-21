package analyzer.substring.patterns;

public class Pattern {
    private final int priority;
    private final String fileType;
    private final String patternToSearch;

    public Pattern(int priority, String pattern, String fileType) {
        this.priority = priority;
        this.patternToSearch = pattern;
        this.fileType = fileType;
    }

    public int getPriority() {
        return priority;
    }

    public String getFileType() {
        return fileType;
    }

    public String getPatternToSearch() {
        return patternToSearch;
    }
}
