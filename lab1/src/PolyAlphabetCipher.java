public class PolyAlphabetCipher {
    private static final String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЬЫЭЮЯ ";
    private static final String A1 = "БЮГЫЕЬЗШЙЦЛФНТПРСОУМХКЧИЩЖЪДЭВЯ АЁ";
    private static final String A3 = "ОПМНХЛИЙЖЗДЕВГАБЮЯЫЭЬ ШЩЦЧФКТУРСЪЁ";
    public static String encode(String message){
        message = message.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char symbol = message.charAt(i);
            int idxA = alphabet.indexOf(symbol);
            if (i%2 == 0) {
                result.append(A1.charAt(idxA));
            } else {
                result.append(A3.charAt(idxA));
            }
        }
        return result.toString();
    }
    public static String decode(String message){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char symbol = message.charAt(i);
            if (i % 2 == 0) {
                int idx = A1.indexOf(symbol);
                result.append(alphabet.charAt(idx));
            }
            else {
                int idx = A3.indexOf(symbol);
                result.append(alphabet.charAt(idx));
            }
        }
        return result.toString();
    }
}
