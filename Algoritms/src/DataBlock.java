public class DataBlock {
    private byte[] data;
    private int size;

    DataBlock(int size){
        this.size = size;
        data = new byte[size];
    }
    DataBlock(byte[] data) {
        size = data.length;
        this.data = new byte[size];
        for (int i = 0; i < size; i++) {
            this.data[i] = data[i];
        }
    }

    DataBlock xor(DataBlock ...d) {
        DataBlock result = new DataBlock(size);
        for (DataBlock block: d) {
            for (int i = 0; i < result.size; i++) {
                result.set(i, (byte) (this.get(i) ^ block.get(i)));
            }
        }
        return result;
    }

    static DataBlock copy(DataBlock d){
        return new DataBlock(d.data);
    }
    static DataBlock copy(DataBlock d, int left, int right){
        DataBlock result = new DataBlock(right - left);
        for (int i = 0; i < right - left; i++) {
            result.set(i, d.data[i + left]);
        }
        return result;
    }
    static DataBlock merge(DataBlock ... d){
        int size = 0;
        for(var block: d) size += block.size();

        DataBlock result = new DataBlock(size);
        int it = 0;
        for(var block: d){
            for (int i = 0; i < block.size(); i++) {
                result.set(it, (byte)block.get(i));
                ++it;
            }
        }
        return result;
    }
    static DataBlock textToBlock(String source, int charSize) {
        char[] src = source.toCharArray();

        DataBlock result = new DataBlock(src.length * charSize);

        for (int i = 0; i < result.size() / charSize; i++) {
            for (int j = 0; j < charSize; j++) {
                //Convert char into pair of bytes. Example 1111_1010_0101_0000 to 0101_0000 and 1111_1010
                result.set(i * charSize + j, (byte) (src[i] >> (8 * j) & 0b1111_1111));
            }
        }
        return result;
    }
    static DataBlock textToBlock(String source) {
        return textToBlock(source, 2);
    }

    void set(int index, byte val){ data[index] = val; }
    int get(int index){ return Byte.toUnsignedInt(data[index]); }
    int size() {return size; }
    byte[] getBytes(){
        byte[] result = new byte[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }

    public String toString(){ return toString(2); }
    public String toString(int charSize) {
        char[] out = new char[size / charSize];

        for (int i = 0; i < size / charSize; i++) {
            for (int j = 0; j < charSize; j++) {
                //Convert pair of bytes into char. Example 0101_0000 and 1111_1010 to 1111_1010_0101_0000
                out[i] |= (get(i * charSize + j) << (8 * j));
            }
        }
        return new String(out);
    }
}
