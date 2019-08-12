import java.util.Arrays;

public class Test {
    static int Nb, Nr;

    static String hex(byte b){
        String s = "";
        for (int i = 0; i < 2; i++){
            s = (char)((b & 0b1111)>9?(b & 0b1111) - 10 +'A':(b & 0b1111)+'0') + s;
            b >>>= 4;
        }
        return s;
    }

    public static void main(String[] args) {
        //String pass = "abcdefghi";
        String text = "abcdefghабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        //byte[] key = Hash.hash_128(pass);
        byte[][][] blocks = bytesToBlocks(StringToBytes(text));
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(hex(blocks[i][j][k]) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        byte[][] cipher = new byte[blocks.length][16];

        //for (int i = 0; i < blocks.length; i++)
        //    cipher[i] = encrypt(blocks[i], key);
    }

    //private static byte[] encrypt(byte[][] block, byte[] key) {
    //    byte[][] state = new byte[4][4];
    //    state = block;
//
    //}

    static String toBin(int i, int siz){
        String ans = "";
        while(i > 0){
            ans += (char)((i & 1) + '0');
            i >>= 1;
        }
        while(ans.length() < siz) ans += '0';
        return new StringBuilder(ans).reverse().toString();
    }

    static byte[][][] bytesToBlocks(byte[] input){
        int N = (input.length + 15) / 16 * 16;                  //One block contains 16 bytes
        input = Arrays.copyOf(input, N);

        byte[][][] out = new byte[N / 16][4][4];
        for (int i = 0; i < N / 16; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++)
                    out[i][j][k] = input[i * 16 + j * 4 + k];
        return out;
    }

    static byte[] StringToBytes(String input){
        char[] src = input.toCharArray();
        int N = src.length;
        final int charSize = 2;             //UTF-16 char size

        byte[] out = new byte[N * charSize];

        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < charSize; j++) {
                //Convert char into pair of bytes. Example 1111_1010_0101_0000 to 0101_0000 and 1111_1010
                out[i * charSize + j] = (byte)(src[i] >> (8 * j) & 0b1111_1111);
            }
        }
        return out;
    }

    static String bytesToString(byte[] src){
        int N = src.length;
        final int charSize = 2;             //UTF-16 char size

        char[] out = new char[N / charSize];

        for (int i = 0; i < N / charSize; i++) {
            for (int j = 0; j < charSize; j++) {
                //Convert pair of bytes into char. Example 0101_0000 and 1111_1010 to 1111_1010_0101_0000
                out[i] |= (src[i * charSize + j]) << (8 * j);
            }
        }
        return new String(out);
    }
}
