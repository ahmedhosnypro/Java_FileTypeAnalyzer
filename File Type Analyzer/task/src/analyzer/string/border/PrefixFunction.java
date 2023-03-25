package analyzer.string.border;

public class PrefixFunction {

    private PrefixFunction() {
    }

    /**
     * Calculate the prefix function of a string of a length n using these steps:
     * 1. Set p[0] = 0. Do the following for every i from 0 to n - 1:
     * 2. Define j = p[i-1]
     * 3. If s[j] = s[i], set p[i] = j + 1.
     * 4. Otherwise, take j = p[j - 1] and repeat step 3. If we reach j = 0 and there is no match,
     * we conclude that p[i] = 0.
     */
    public static int[] calculate(String pattern) {
        int[] prefixFunction = new int[pattern.length()];
        prefixFunction[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int j = prefixFunction[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixFunction[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefixFunction[i] = j;
        }
        return prefixFunction;
    }
}
