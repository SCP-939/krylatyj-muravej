package app;

import java.math.BigInteger;

public class Cipher {
    private BigInteger key;

    public Cipher(BigInteger key) {
        this.key = key;
    }

    public String encipher(String text) {
        StringBuilder sb = new StringBuilder();

        String key = this.key.toString();

        for (int i = 0, j = 0; i < text.length(); i++, j++) {
            j %= key.length();

            sb.append((char) (text.charAt(i) ^ key.charAt(j)));
        }

        return sb.toString();
    }

    public String decipher(String text) {
        return encipher(text);
    }
}
