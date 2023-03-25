package analyzer.substring.finder;

import analyzer.string.hash.PolynomialHash;
import analyzer.string.hash.StringHash;

import java.math.BigInteger;

public class RabinKarp implements SubstringFinder {

    /*
     * The algorithm uses the polynomial hash functions and their rolling hash property. As follows:
     * 1. Calculate the hash value for a pattern.
     * 2. Moving along the text (from right to left),
     *    calculate the hash value for the current substring of the text using the rolling hash property.
     * 3. If hash values for the pattern and the current substring are not equal,
     *    move to the next substring. Otherwise, perform a symbol-by-symbol comparison of the strings.
     *    If the strings are indeed equal, return true.
     *
     * 4. Repeat steps 2 and 3 until the end of the text is reached.
     */
    @Override
    public Boolean search(String pattern, String text) {
        StringHash stringHasher = new PolynomialHash();
        int patternLength = pattern.length();
        int textLength = text.length();
        if (patternLength > textLength) {
            return false;
        }

        BigInteger patternHash = stringHasher.hash(pattern);


        BigInteger textSubStrHash = BigInteger.ZERO;
        for (int i = textLength - patternLength; i >= 0; i--) {
            if (i == textLength - patternLength) {
                // calculate the hash value for the last substring of the text of the same length as the pattern
                String substring = text.substring(textLength - patternLength);
                textSubStrHash = stringHasher.hash(substring);
            } else {
                // calculate the hash value for the next substring of the text
                textSubStrHash = stringHasher.roll(textSubStrHash, text.charAt(i + patternLength), text.charAt(i), i, i + patternLength);
            }
            if (patternHash.equals(textSubStrHash)) {
                // Perform a symbol-by-symbol comparison of the strings.
                for (int j = 0; j < patternLength; j++) {
                    if (pattern.charAt(j) != text.charAt(i + j)) {
                        break;
                    }
                    if (j == patternLength - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}