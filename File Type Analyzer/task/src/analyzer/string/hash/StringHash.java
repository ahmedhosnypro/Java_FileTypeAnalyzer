package analyzer.string.hash;

import java.math.BigInteger;

public interface StringHash {
    BigInteger hash(String s);
    BigInteger roll(BigInteger prevHash, char prevChar, char nextChar, int prevIndex, int nextIndex);
}
