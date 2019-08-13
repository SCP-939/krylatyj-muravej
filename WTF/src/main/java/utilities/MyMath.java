package utilities;

import java.math.BigInteger;

public class MyMath {
    public static final BigInteger ZERO = new BigInteger("0");
    public static final BigInteger ONE = new BigInteger("1");
    public static final BigInteger TWO = new BigInteger("2");

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(a.gcd(b)).multiply(b);
    }

    public static BigInteger pow(BigInteger a, BigInteger n) {
        if(n.equals(ZERO)) return new BigInteger(ONE.toString());
        if(n.equals(ONE)) return new BigInteger(a.toString());

        if(n.remainder(TWO).equals(ZERO)) {
            BigInteger c = pow(a, n.divide(TWO));
            return c.multiply(c);
        } else {
            return a.multiply(pow(a, n.subtract(ONE)));
        }
    }
}
