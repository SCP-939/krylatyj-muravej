public class Encryptor {
    private DataBlock key;
    private static final int rounds = 80;

    Encryptor(String password){
        key = Hash.hash_128(password);
    }

    DataBlock encrypt(DataBlock data) {
        DataBlock[] roundKeys = generateKeys(key);

        DataBlock left = DataBlock.copy(data, 0, data.size() / 2);
        DataBlock right = DataBlock.copy(data, data.size() / 2, data.size());

        for (int i = 0; i < rounds; i++) {
            right = right.xor(right, f(left, roundKeys[i]));
            if(i != rounds - 1){
                 DataBlock temp = left;
                 left = right;
                 right = temp;
            }
        }

        DataBlock result = DataBlock.merge(left, right);
        return result;
    }

    DataBlock f(DataBlock inputBlock, DataBlock key) {
        DataBlock answer = new DataBlock(inputBlock.size());
        for (int i = 0; i < inputBlock.size(); i++) {
            answer.set(i, (byte) ((inputBlock.get(i) ^ key.get(i % key.size())) * 37));
        }
        return answer;
    }

    static DataBlock[] generateKeys(DataBlock key) {
        DataBlock[] keys = new DataBlock[rounds];
        DataBlock prev = key;
        for (int i = 0; i < rounds; i++) {
            keys[i] = nextKey(prev);
            prev = keys[i];
        }
        return keys;
    }
    static DataBlock nextKey(DataBlock key) {
        DataBlock result = DataBlock.copy(key);
        Hash.push(result, 'z');
        return result;
    }
}
