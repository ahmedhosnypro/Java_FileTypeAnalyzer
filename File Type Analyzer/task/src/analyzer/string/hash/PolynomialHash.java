package analyzer.string.hash;

import java.math.BigInteger;

public class PolynomialHash implements StringHash {
    private static final int DIFFERENT_SYMBOLS_NUM = 3;
    private static final int MOD = 31;

    /*
     * For a string s = s0s1...sn-1, the polynomial hash function is defined as:
     * h = (s0 * a^0 + s1 * a^1 + ... + sn-1 * a^n-1) % m
     * where 'a' is a constant usually a prime number approximately equal to the total number of different symbols in the alphabet
     * 'm' is a constant as well, usually a big prime number.
     */

    public BigInteger hash(String s) {
        BigInteger hash = BigInteger.ZERO;
        for (int i = 0; i < s.length(); i++) {
            hash = hash.add(BigInteger.valueOf(s.charAt(i)).multiply(BigInteger.valueOf((long) Math.pow(DIFFERENT_SYMBOLS_NUM, i))));

        }
        hash = hash.mod(BigInteger.valueOf(MOD));
        return hash;
    }


    /*
     * we need to start the calculations with the last substring of the string s.
     * If we know the hash value h (si+1  si+2 ... sj) , a hash value for the next substring can be calculated as follows:
     * h (si si+1 ... sj-1) = ((h (si+1 si+2 ... sj) - s[j] * a^j-i-1) * a + s[i]) % m
     * 'j' is the index of the last symbol of the previous substring, 'i' is the index of the first symbol of the new substring.
     */

    public BigInteger roll(BigInteger oldHash,
                           char prevSubstrLastSymbol,
                           char newSubstrFstSymbol,
                           int newSubstrFstSymbolIndex,
                           int prevSubstrLastSymbolIndex) {

        BigInteger newHash = oldHash.subtract(BigInteger.valueOf(prevSubstrLastSymbol)
                        .multiply(BigInteger.valueOf((long) Math.pow(DIFFERENT_SYMBOLS_NUM, prevSubstrLastSymbolIndex - newSubstrFstSymbolIndex - 1f))))
                .multiply(BigInteger.valueOf(DIFFERENT_SYMBOLS_NUM))
                .add(BigInteger.valueOf(newSubstrFstSymbol));

        newHash = newHash.mod(BigInteger.valueOf(MOD));
        return newHash;
    }
}
