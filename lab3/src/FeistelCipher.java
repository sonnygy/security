public class FeistelCipher {
    private static final int ROUNDS = 18;
    private static final int KEY = 12345;
    private static int[] generateRoundKeys() {

        int[] keys = new int[ROUNDS];
        for (int i = 0; i < ROUNDS; i++) {
            keys[i] = KEY + i;
        }
        return keys;
    }
    private static int F(int value, int key) {
        return (value << 1) ^ key;
    }

    private static char[] encodeBlock(char left, char right) {
        int L = left;
        int R = right;
        int[] keys = generateRoundKeys();
        for (int i = 0; i < ROUNDS; i++) {
            int temp = R;
            R = L ^ F(R, keys[i]);
            L = temp;
        }
        return new char[]{(char) L, (char) R};
    }

    private static char[] decodeBlock(char left, char right) {
        int L = left;
        int R = right;
        int[] keys = generateRoundKeys();
        for (int i = ROUNDS - 1; i >= 0; i--) {
            int temp = L;
            L = R ^ F(L, keys[i]);
            R = temp;
        }
        return new char[]{(char) L, (char) R};
    }

    public static String encode(String input) {
        if (input.length() % 2 != 0) {
            input += " ";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            char L = input.charAt(i);
            char R = input.charAt(i + 1);
            char[] block = encodeBlock(L, R);
            result.append(block[0]);
            result.append(block[1]);
        }
        return result.toString();
    }

    public static String decode(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i += 2) {
            char L = input.charAt(i);
            char R = input.charAt(i + 1);
            char[] block = decodeBlock(L, R);
            result.append(block[0]);
            result.append(block[1]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "ЗАЩИТА ИНФОРМАЦИИ";

        String encrypted = encode(text);
        System.out.println("Зашифрованный:");
        System.out.println(encrypted);

        String decrypted = decode(encrypted);
        System.out.println("\nДешифрованный:");
        System.out.println(decrypted);
    }
}