package analyzer.string.hash;

import java.math.BigInteger;

public class LinearHash implements StringHash {

    /*
     * Linear hash function is defined as a sum of the symbols' associated values:
     * h = s0 + s1 + ... + sn-1
     * A corresponds to 1, B corresponds to 2, ..., Z corresponds to 26 and so on.
     * That is, each symbol is associated with its order number in the alphabet.
     * The hash function is case-insensitive.
     */

    public BigInteger hash(String s) {
        BigInteger hash = BigInteger.ZERO;
        for (int i = 0; i < s.length(); i++) {
//            hash += s.charAt(i) - 'A' + 1;
            hash = hash.add(BigInteger.valueOf(s.charAt(i) - 'A' + 1));
        }
        return hash;
    }





    /*
     * If we know a hash value h (si si+1 ... sj) , a hash value for the next substring can be calculated in
     * constant time using the following formula:
     * h (si+1 si+2 ... sj+1) = h (si si+1 ... sj) - s[i] + s[j+1]
     */

    @Override
    public BigInteger roll(BigInteger prevHash, char prevChar, char nextChar, int prevIndex, int nextIndex) {
        return prevHash.subtract(BigInteger.valueOf(prevChar)).add(BigInteger.valueOf(nextChar));
    }
}
