import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
public class DigitalSignature {
    static BigInteger p, q, a;
    static BigInteger x; // секретный ключ
    static BigInteger y; // открытый ключ
    static Random rand = new Random();

    public static BigInteger hash(String message) {
        int totalOnes = 0;

        for (char c : message.toCharArray()) {
            totalOnes += Integer.bitCount(c);  // Подсчитывает количество единиц в бинарном представлении
        }

        return BigInteger.valueOf(totalOnes).mod(q);
    }

    public static BigInteger modPow(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }

    public static void generateKeys() {
        p = new BigInteger("23");
        q = new BigInteger("11");
        a = new BigInteger("2");

        x = new BigInteger(q.bitLength(), rand).mod(q); // секретный
        y = modPow(a, x, p);

        System.out.println("x (secret) = " + x);
        System.out.println("y (public) = " + y);
    }

    public static BigInteger[] sign(String message) throws Exception {
        BigInteger h = hash(message);
        if (h.equals(BigInteger.ZERO)) h = BigInteger.ONE;

        BigInteger k = BigInteger.ZERO;
        BigInteger r = BigInteger.ZERO;
        BigInteger s = BigInteger.ZERO;

        do {
            k = new BigInteger(q.bitLength(), rand).mod(q);

            r = modPow(a, k, p).mod(q);
            if (r.equals(BigInteger.ZERO)) continue;

            s = (x.multiply(r).add(k.multiply(h))).mod(q);

        } while (r.equals(BigInteger.ZERO) || s.equals(BigInteger.ZERO));

        return new BigInteger[]{r, s};
    }

    public static boolean verify(String message, BigInteger r, BigInteger s) throws Exception {

        if (r.compareTo(BigInteger.ZERO) <= 0 || r.compareTo(q) >= 0) return false;
        if (s.compareTo(BigInteger.ZERO) <= 0 || s.compareTo(q) >= 0) return false;

        BigInteger h = hash(message);
        if (h.equals(BigInteger.ZERO)) h = BigInteger.ONE;

        BigInteger v = h.modPow(q.subtract(BigInteger.TWO), q);

        BigInteger z1 = s.multiply(v).mod(q);
        BigInteger z2 = (q.subtract(r)).multiply(v).mod(q);

        BigInteger u = (modPow(a, z1, p).multiply(modPow(y, z2, p))).mod(p).mod(q);

        return u.equals(r);
    }
}