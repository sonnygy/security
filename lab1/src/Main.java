void main() {
    String message = "ЗАЩИТА ИНФОРМАЦИИ";
    String encrypted = PolyAlphabetCipher.encode(message);
    System.out.println("Зашифрованный текст: " + encrypted);

    String decrypted = PolyAlphabetCipher.decode(encrypted);
    System.out.println("Расшифрованный текст: " + decrypted);
}
