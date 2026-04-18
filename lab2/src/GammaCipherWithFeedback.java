import java.util.*;

public class GammaCipherWithFeedback {
    static final int A = 5;
    static final int C = 3;
    static final int b = 8;
    static final int M = (int) Math.pow(2, b);
    static final int T0 = 7;

    public static int[] textToBits(String message) {
        int[] result = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            result[i] = message.charAt(i);
        }
        return result;
    }

    public static String bitsToText(int[] bits) {
        StringBuilder result = new StringBuilder();
        for (int b : bits) {
            result.append((char) b);
        }
        return result.toString();
    }

    public static int countOnes(int value) {
        return Integer.bitCount(value);
    }

    public static int nextT(int T) {
        return (A * T + C) % M;
    }

    public static int[] encode(int[] data) {
        int[] result = new int[data.length];

        int T = T0;

        for (int i = 0; i < data.length; i++) {
            int gamma = nextT(T);

            result[i] = data[i] ^ gamma;

            T = countOnes(result[i]);
        }

        return result;
    }
    public static int[] decode(int[] encrypted) {
        int[] result = new int[encrypted.length];

        int T = T0;

        for (int i = 0; i < encrypted.length; i++) {
            int gamma = nextT(T);

            result[i] = encrypted[i] ^ gamma;

            T = countOnes(encrypted[i]);
        }

        return result;
    }
    public static void printArray(int[] arr, String label) {
        System.out.println(label + ":");
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println("\n");
    }
    public static void main(String[] args) {

        String input = "абв";

        int[] data = textToBits(input);

        int[] encrypted = encode(data);
        printArray(encrypted, "Зашифрованный текст (коды)");

        String encryptedText = bitsToText(encrypted);
        System.out.println("Зашифрованный текст: " + encryptedText);

        int[] decrypted = decode(encrypted);
        String decryptedText = bitsToText(decrypted);

        System.out.println("Дешифрованный текст: " + decryptedText);
    }
}
