import java.math.BigInteger;

public static void main() throws Exception {

    String message = "ЗАЩИТА ИНФОРМАЦИИ";
    System.out.println("Сообщение: " + message);

    DigitalSignature.generateKeys();

    System.out.println();
    BigInteger[] signature = DigitalSignature.sign(message);
    BigInteger r = signature[0];
    BigInteger s = signature[1];

    System.out.println("r = " + r);
    System.out.println("s = " + s);

    boolean valid = DigitalSignature.verify(message, r, s);
    System.out.println("Подпись корректна: " + valid);
}
