import java.util.Scanner;

public class Encryption {

    private static int CONST_PUBLIC_KEY;
    private static int CONST_PROTECTED_KEY_P = 11;
    private static int CONST_PROTECTED_KEY_Q = 7;
    private static int M_MESSAGE = 20;

    private static int RESULT;

    public static void main(String[] args) {
        CONST_PUBLIC_KEY = CONST_PROTECTED_KEY_P * CONST_PROTECTED_KEY_Q;

        if(M_MESSAGE<CONST_PUBLIC_KEY){
            RESULT = (M_MESSAGE*M_MESSAGE)%CONST_PUBLIC_KEY;
            System.out.println(RESULT);
        } else {
            System.out.println("Something wrong here!");
            RESULT = -1;
        }
    }

    /*
        выбираются два случайных числа p и q с учётом следующих требований:
        числа должны быть большими (см. разрядность);
        числа должны быть простыми;
        должно выполняться условие: p ≡ q ≡ 3 mod 4.

        Выполнение этих требований сильно ускоряет процедуру извлечения корней по модулю р и q;

     */

    public Encryption(int CONST_PUBLIC_KEY, int CONST_PROTECTED_KEY_P, int CONST_PROTECTED_KEY_Q, int m_MESSAGE, int RESULT) {
        Encryption.CONST_PUBLIC_KEY = CONST_PUBLIC_KEY;
        Encryption.CONST_PROTECTED_KEY_P = CONST_PROTECTED_KEY_P;
        Encryption.CONST_PROTECTED_KEY_Q = CONST_PROTECTED_KEY_Q;
        M_MESSAGE = m_MESSAGE;
        Encryption.RESULT = RESULT;
    }

    void Encrypt(){
        CONST_PUBLIC_KEY = CONST_PROTECTED_KEY_P * CONST_PROTECTED_KEY_Q;

        if(M_MESSAGE<CONST_PUBLIC_KEY){
            RESULT = (M_MESSAGE*M_MESSAGE)%CONST_PUBLIC_KEY;
            System.out.println(RESULT);
        } else {
            System.out.println("Something wrong here!");
            RESULT = -1;
        }
    }


}
