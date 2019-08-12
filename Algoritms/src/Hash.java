public class Hash {
    static byte[] hash_128(String s){
        char[] src = s.toCharArray();
        byte[] hashCode = new byte[16];
        for (int i = 0; i < src.length; i++) {
            push(hashCode, src[i]);
        }
        return hashCode;
    }

    static void push(byte[] b, char ch){
        int plus = 0;
        int temp = b[0] * 37 + ch;
        plus = temp >>> 8;
        b[0] = (byte)(temp & 0b1111_1111);
        for (int i = 1; i < 16; i++) {
            temp = b[i] * 37 + plus;
            plus = temp >>> 8;
            b[i] = (byte)(temp & 0b1111_1111);
        }
    }
}
