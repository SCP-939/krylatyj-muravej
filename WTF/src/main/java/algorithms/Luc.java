package algorithms;

import utilities.Key;
import utilities.MyMath;
import utilities.Pair;

import java.math.BigInteger;

public class Luc {
    private static final BigInteger P = new BigInteger("1949");
    private static final BigInteger Q = new BigInteger("2089");


    public static Pair<Key, Key> generateKeys() {
        BigInteger N = P.multiply(Q);

        BigInteger e = new BigInteger("1103");

        BigInteger s = (MyMath.lcm((P.add(MyMath.ONE)), (Q.add(MyMath.ONE))));

        BigInteger ed = new BigInteger("1");
        while (!ed.remainder(e).equals(MyMath.ZERO)) {
            ed = ed.add(s);
        }

        BigInteger d = ed.divide(e);

        return new Pair<>(new Key(e, N), new Key(d, N));
    }

    public static String encipher(String text, Key e) {
        StringBuilder sb = new StringBuilder();

        for(char c : text.toCharArray()) {
            sb.append(encipherSymbol(c, e)).append(' ');
        }

        return sb.toString();
    }

    private static String encipherSymbol(char c, Key e) {
        return getLucNum(new BigInteger(((int) c) + ""), MyMath.ONE, (BigInteger) e.getKey()).remainder((BigInteger) e.getN()).toString();
    }

    public static String decipher(String text, Key d) {
        String[] s = text.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            sb.append((char) Integer.parseInt(decipherSymbol(s[i], d)));
        }

        return sb.toString();
    }

    private static String decipherSymbol(String c, Key d) {
        return getLucNum(new BigInteger(c), MyMath.ONE, (BigInteger) d.getKey()).remainder((BigInteger) d.getN()).toString();
    }

    /*
     * The Luke Sequence ( Vn(P, Q) )
     *
     * Basement:
     * V0 = 2
     * V1 = P
     *
     * Recurrent formula:
     * Vn+2(P, Q) = P * Vn+1(P, Q) - Q * Vn(P, Q) , n ≥ 0
     *
     * Property 1:
     * Vn(P mod N, Q mod N) = Vn(P, Q) mod N
     *
     * Property 2:
     * Vn(Vk(P, Q), Q^k) = Vnk(P, Q)
     */

    private static BigInteger getLucNum(BigInteger p, BigInteger q, BigInteger n) {
        BigInteger va = new BigInteger("2");
        BigInteger vb = new BigInteger(p.toString());
        BigInteger vc = new BigInteger(p.multiply(vb).subtract(q.multiply(va)).toString());

        if(n.equals(MyMath.ZERO)) return new BigInteger(va.toString());
        if(n.equals(MyMath.ONE)) return new BigInteger(vb.toString());
        if(n.equals(MyMath.TWO)) return new BigInteger(vc.toString());

        BigInteger m = new BigInteger("2");
        while (m.compareTo(n) < 0) {
            va = vb;
            vb = vc;
            vc = p.multiply(vb).subtract(q.multiply(va));

            m = m.add(MyMath.ONE);
        }

        return new BigInteger(vc.toString());
    }
}
