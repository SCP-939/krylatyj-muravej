public class Hash {


    static byte[] hash_128(String s){
        char[] src = s.toCharArray();
        byte[] hashCode = new byte[16];
        push(hashCode, src[0]);
        return hashCode;
    }

    static void push(byte[] b, char ch){
        int plus = 0;
        int temp = b[0] * 37 + ch;
        plus = temp >>> 8;
        for (int i = 1; i < 16; i++) {
            b[1] *= 37;
        }
    }
}
