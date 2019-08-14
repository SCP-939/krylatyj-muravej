package algorithms;

import utilities.*;

public class Main {
    public static void main(String[] args) {
        Pair<Key, Key> keys = Luc.generateKeys();

        String text = "Luc govno";

        String ciphered = Luc.encipher(text, keys.first);

        System.out.println(ciphered);
        System.out.println(Luc.decipher(ciphered, keys.second));
    }
}
