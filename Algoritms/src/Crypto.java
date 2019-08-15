public class Crypto extends Cipher {
    private String key;

    Crypto(String key){
        this.key = key;
    }

    @Override
    public String encrypt(String txt) {
        Encryptor encryptor = new Encryptor(key);
        return encryptor.encrypt(DataBlock.textToBlock(txt)).toString();
    }

    @Override
    public String decrypt(String txt) {
        Decryptor decryptor = new Decryptor(key);
        return decryptor.decrypt(DataBlock.textToBlock(txt)).toString();
    }
}
