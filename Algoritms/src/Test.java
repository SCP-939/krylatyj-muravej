import javax.xml.crypto.Data;
import java.util.Arrays;

public class Test {
    static int Nb, Nr;
    static final int rounds = 80;

    static String hex(byte b) {
        String s = "";
        for (int i = 0; i < 2; i++) {
            s = (char) ((b & 0b1111) > 9 ? (b & 0b1111) - 10 + 'A' : (b & 0b1111) + '0') + s;
            b >>>= 4;
        }
        return s;
    }

    public static void main(String[] args) {
        String pass = "abcdefghi";
        String text = "очпочмак";
        DataBlock data = new DataBlock(text, 2);
        Encryptor encryptor = new Encryptor(pass);
        DataBlock e = encryptor.encrypt(data);
        DataBlock d = decrypt(e);
        System.out.println(bytesToString(d));
    }



    static byte[] decrypt(byte[] data, byte[] key) {
        byte[][] roundKeys = getKeys(key);
        for (int i = 0; i < roundKeys.length / 2; i++) {
            byte[] temp = roundKeys[i];
            roundKeys[i] = roundKeys[roundKeys.length - 1 - i];
            roundKeys[roundKeys.length - 1 - i] = temp;
        }

        byte[] left = Arrays.copyOfRange(data, 0, data.length / 2);
        byte[] right = Arrays.copyOfRange(data, data.length / 2, data.length);

        for (int i = 0; i < rounds; i++) {
            right = xor(right, f(left, roundKeys[i]));
            if(i != rounds - 1){
                byte[] temp = right;
                right = left;
                left = temp;
            }
        }
        byte[] output = new byte[left.length + right.length];
        for (int i = 0; i < left.length; i++) output[i] = left[i];
        for (int i = 0; i < right.length; i++) output[i + left.length] = right[i];

        return output;
    }

    static String toBin(int i, int siz) {
        i = Byte.toUnsignedInt((byte)i);
        String ans = "";
        while (i != 0) {
            ans += (char) ((i & 1) + '0');
            i >>= 1;
        }
        while (ans.length() < siz) ans += '0';
        return new StringBuilder(ans).reverse().toString();
    }
}
