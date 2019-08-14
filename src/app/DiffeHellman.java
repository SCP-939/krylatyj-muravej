package app;

import java.math.BigInteger;
import java.util.Random;

public class DiffeHellman {
    private static final BigInteger ZERO = new BigInteger("0");
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger TEN = new BigInteger("10");

    public static final BigInteger G;
    public static final BigInteger P;

    private static final Random r = new Random();

    static {
        G = new BigInteger("3");
        P = new BigInteger("8898199069817463678485389638799365096445734366414310908146394441886257763314814694132756649349862080358649646715301686855872526564977748675270525991334417821163649595510684930533567969741758639331220811624890982043252252030506142462628405367284468533134177441847935287453260053001628534081324521561719334341469203780264552866117126788663478943406695856441045072200465069636407887744975870851859256147210295593537700149175042959838535761853986838117503318535310276768048808579464991979711294242497017339742191380499112415541073854655507853798858229019843280139381110828005945232776036169838095760099342800553467547764138603666927567112236650425363497763106995003778810850083678609210348566258788398480624827343771706506691916003149849269092089583639647045608464400830592634062114775132397323505991531147687618855536497662080875770075494636325618920070586310833009095146064834825353264551464839504676401945122073085191387232343395663511474107613862890573420421471010623388708829055570660239172404510721");
    }

    public static BigInteger pow(BigInteger a, BigInteger n, BigInteger p) {
        if (n.equals(ZERO)) return new BigInteger(ONE.toString());
        if (n.equals(ONE)) return new BigInteger(a.toString()).remainder(p);

        if (n.remainder(TWO).equals(ZERO)) {
            BigInteger c = pow(a, n.divide(TWO), p);
            return c.multiply(c).remainder(p);
        } else {
            return a.multiply(pow(a, n.subtract(ONE), p)).remainder(p);
        }
    }

    public static BigInteger generate(int len) {
        BigInteger a = new BigInteger("0");

        BigInteger[] arr = new BigInteger[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new BigInteger(i + "");
        }

        a = a.add(arr[(r.nextInt(9) + 1)]);

        for (int i = 1; i < len; i++) {
            a = a.multiply(TEN);
            a = a.add(arr[r.nextInt(10)]);
        }

        return a;
    }
}