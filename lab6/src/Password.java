import java.util.Random;
import java.util.Scanner;

public class Password {
    private static String password;
    private static int maxAttempts;
    private static int symbolsToCheck;
    private static Random random = new Random();

    public void getPassword(int maxAttempts, int symbolsToCheck) {
        System.out.println("=== Система парольной защиты ===");
        System.out.print("Введите пароль: ");
        Scanner input = new Scanner(System.in);
        password = input.nextLine();
        this.maxAttempts = maxAttempts;
        this.symbolsToCheck = symbolsToCheck;
    }

    public static int[] generatePosition() {
        int[] positions = new int[symbolsToCheck];
        for (int i = 0; i < symbolsToCheck; i++) {
            positions[i] = random.nextInt(password.length());
        }
        return positions;
    }

    public char[] generateSymbolsToCheck(int[] positions) {
        Scanner input = new Scanner(System.in);
        char[] userInput = new char[symbolsToCheck];
        System.out.println("\nВведите символы:");
        for (int i = 0; i < symbolsToCheck; i++) {
            System.out.print("Введите символ №" + (positions[i] + 1) + ": ");
            userInput[i] = input.next().charAt(0);
        }
        return userInput;
    }

    public void checkPassword() {
        int attempts = 0;
        while (attempts < maxAttempts) {
            int[] positions = generatePosition();
            char[] userInput = generateSymbolsToCheck(positions);
            boolean accessGranted = true;

            for (int i = 0; i < symbolsToCheck; i++) {
                if (userInput[i] != password.charAt(positions[i])) {
                    accessGranted = false;
                    break;
                }
            }
            if (accessGranted) {
                System.out.println("\nДоступ разрешён!");
                return;
            } else {
                attempts++;
                System.out.println("\nНеверные символы!");
                if (attempts < maxAttempts) {
                    System.out.println("Осталось попыток: "+(maxAttempts - attempts));
                }
            }
        }
        System.out.println("\nДоступ заблокирован!");
    }

    public static void main(String[] args) {
        Password obj = new Password();
        obj.getPassword(3, 3);
        obj.checkPassword();
    }
}