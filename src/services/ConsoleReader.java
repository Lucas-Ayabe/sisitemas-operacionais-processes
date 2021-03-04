package services;

import java.util.Scanner;

public class ConsoleReader {

    private Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public double askToDouble(String message) {
        System.out.print(message);
        return nextDouble();
    }

    public double nextDouble() {
        return scanner.nextDouble();
    }

    public int askToInt(String message) {
        System.out.print(message);
        return nextInt();
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public String ask(String message) {
        System.out.print(message);
        return nextLine();
    }

    public String nextLine() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}
