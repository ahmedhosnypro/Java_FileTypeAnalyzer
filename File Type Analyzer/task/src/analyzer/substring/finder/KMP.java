package analyzer.substring.finder;

import analyzer.string.border.PrefixFunction;

// The Knuth-Morris-Pratt algorithm
public class KMP implements SubstringFinder {
    /**
     * The Knuth-Morris-Pratt algorithm
     * For arbitrary pattern s and text t, the KMP algorithm can be formulated as follows:
     * 1. Calculate the prefix function p for the pattern s
     * 2. Set the first substring of the text with length |s| as current.
     * 3. Compare the pattern with the current substring of the text. If all symbols match, save the index of the found occurrence.
     * Otherwise, shift the pattern by L - p[L - 1] symbols, where L is the length of the matched substring of the pattern.
     * 4. Continue step 3 until all substrings of the text are processed. Then, return the found occurrences.
     */
    @Override
    public Boolean search(String pattern, String text) {
        int[] prefixFunction = PrefixFunction.calculate(pattern);
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefixFunction[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return true;
            }
        }
        return false;
    }
}
