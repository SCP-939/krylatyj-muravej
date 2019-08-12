public class Cryptography {
    private static final int XOR_KEY = 37;

    /**
     * XOR-method of Symmetric coding
     * @param text Text needed to be encipher\decipher
     * @return Enciphered\deciphered text
     */
    public static String xorcipher(String text) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append((char) (text.charAt(i) ^ XOR_KEY));
        }

        return sb.toString();
    }
}
