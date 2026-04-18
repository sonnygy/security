void main() {
    String message = "ЗАЩИТА ИНФОРМАЦИИ";
    System.out.println("Исходное сообщение: " + message);

    RSA rsa = new RSA();
    rsa.generateKeys();
    BigInteger[] encrypted = rsa.encode(message);
    String decrypted = rsa.decode(encrypted);
}
