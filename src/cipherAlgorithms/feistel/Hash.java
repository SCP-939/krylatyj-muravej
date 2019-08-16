package cipherAlgorithms.feistel;

class Hash {
    static DataBlock hash_128(String s){
        char[] src = s.toCharArray();
        DataBlock hashCode = new DataBlock(16);
        for (int i = 0; i < src.length; i++) {
            push(hashCode, src[i]);
        }
        return hashCode;
    }

    static void push(DataBlock block, char ch){
        int plus = 0;
        int temp = block.get(0) * 37 + ch;
        plus = temp >>> 8;
        block.set(0, (byte)(temp & 0b1111_1111));
        for (int i = 1; i < block.size(); i++) {
            temp = block.get(i) * 37 + plus;
            plus = temp >>> 8;
            block.set(i, (byte)(temp & 0b1111_1111));
        }
    }
}
