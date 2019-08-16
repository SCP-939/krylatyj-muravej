package cipherAlgorithms.feistel;

import app.Cipher;

import java.math.BigInteger;

public class FeistelCipher implements Cipher {
    private String key;

    public FeistelCipher(BigInteger key) {
        this.key = key.toString();
    }

    @Override
    public String encipher(String txt) {
        Encryptor encryptor = new Encryptor(key);
        return encryptor.encrypt(DataBlock.textToBlock(txt)).toString();
    }

    @Override
    public String decipher(String txt) {
        Decryptor decryptor = new Decryptor(key);
        return decryptor.decrypt(DataBlock.textToBlock(txt)).toString();
    }
}
