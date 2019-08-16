import java.util.ArrayList;

public class RSA extends Cipher {
    public static long pow(long a, long power, long mod) {
        if(power <= 0) return 1;
        long prev = pow(a, power >> 1, mod);
        long answer = prev * prev % mod;
        if(power % 2 == 1) answer *= a;
        return answer % mod;
    }

    public String decipher(String text) {
        ArrayList<Long> closed_message = new ArrayList<>();
        for(String s: text.split(" ")) closed_message.add(Long.parseLong(s));

        long key_p = 163, key_q = 167;
        long open_n = key_p * key_q;
        long f_n = (key_p - 1) * (key_q - 1);
        long open_e = 11;
        long kof_k = 4;
        long key_d = (kof_k * f_n + 1) / open_e;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < closed_message.size(); i++) {
            long a = closed_message.get(i);
            a = pow(a, key_d, open_n);
            result.append((char)a);
        }
        return result.toString();
    }

    public String encipher(String text){
        StringBuilder closed_message = new StringBuilder();
        long open_n = 27221;
        long open_e = 11;
        for (int i = 0; i < text.length(); i++) {
            long a = text.charAt(i);
            a = pow(a, open_e, open_n);
            closed_message.append(a);
        }
        return closed_message.toString();
    }
}
