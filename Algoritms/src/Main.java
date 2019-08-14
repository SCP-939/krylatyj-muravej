import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    static String hex(byte b) {
        String s = "";
        for (int i = 0; i < 2; i++) {
            s = (char) ((b & 0b1111) > 9 ? (b & 0b1111) - 10 + 'A' : (b & 0b1111) + '0') + s;
            b >>>= 4;
        }
        return s;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            if (args[0].equals("-e")) {
                File from = new File(args[1]);
                File to = new File(args[2]);
                String password = args[3];
                setFileContent(to, encrypt(password, getFileContent(from)));
            } else if (args[0].equals("-d")) {
                File from = new File(args[1]);
                File to = new File(args[2]);
                String password = args[3];
                setFileContent(to, decrypt(password, getFileContent(from)));
            }else throw new Exception("The first argument is unavailable or invalid");
        }catch (Exception e){
            System.out.println("The right arguments for this application must be:\n"+
                    "-e input output password\n"+
                    "or\n"+
                    "-d input output password\n"+
                    "\nFirst argument sets mode of application (Encode or decode file).\n"+
                    "Second argument is the path to source file.\n"+
                    "Third argument is the path to output file.\n"+
                    "Fourth argument is the password, that will be used in encryption/decryption.\n");
        }finally {
            System.out.println("Completed in " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
        }
    }

    private static byte[] getFileContent(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    private static void setFileContent(File file, byte[] content) throws IOException {
        Files.write(file.toPath(), content);
    }

    private static byte[] encrypt(String password, byte[] txt) {
        Encryptor encryptor = new Encryptor(password);
        return encryptor.encrypt(new DataBlock(txt)).getBytes();
    }
    private static byte[] decrypt(String password, byte[] input) {
        Decryptor de = new Decryptor(password);
        var temp = de.decrypt(new DataBlock(input)).getBytes();
        return temp;
    }
}