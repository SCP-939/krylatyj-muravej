package algorithms;

import utilities.Pair;

public class Luc {
    private static final int V0 = 2;

    public static String encipher(String text) {
        return null;
    }

    public static String decipher(String text) {
        return null;
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

    private static Pair<Integer, Integer> getLucSequence(int p, int q) { // TODO: 2019-08-12 Integer Overflow
        int V1 = p;

        return null;
    }
}
