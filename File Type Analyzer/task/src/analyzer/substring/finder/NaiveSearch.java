package analyzer.substring.finder;

// Just use the built-in String.contains() method
public class NaiveSearch implements SubstringFinder {
    @Override
    public Boolean search(String pattern, String text) {
        return text.contains(pattern);
    }
}
