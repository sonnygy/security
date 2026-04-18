import java.math.BigInteger;
import java.util.Random;
public class RSA {
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public BigInteger generatePrimeNumber() {
        return BigInteger.probablePrime(10, new Random());
    }

    public void generateKeys() {
        BigInteger p = generatePrimeNumber();
        BigInteger q = generatePrimeNumber();
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE))
                .multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.valueOf(65537);
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.TWO);
        }
        d = e.modInverse(phi);
        System.out.println("\nОткрытый ключ (e, n): " + e + ", " + n);
        System.out.println("Закрытый ключ (d, n): " + d + ", " + n);
    }

    public BigInteger[] encode(String message) {
        BigInteger[] encrypted = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int charCode = message.charAt(i);
            BigInteger m = BigInteger.valueOf(charCode);
            encrypted[i] = m.modPow(e, n);
        }
        System.out.println("\nЗашифрованное сообщение:");
        for (BigInteger c : encrypted) {
            System.out.print(c + " ");
        }
        return encrypted;
    }

    public String decode(BigInteger[] encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (BigInteger c : encrypted) {
            BigInteger m = c.modPow(d, n);
            decrypted.append((char) m.intValue());
        }
        System.out.println("\n\nРасшифрованное сообщение: " + decrypted);
        return decrypted.toString();
    }
}
