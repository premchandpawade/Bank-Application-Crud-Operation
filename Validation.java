package bank;

import java.util.Scanner;

public class Validation {

    public static int validateAccountNumber(Scanner sc) {
        int acc;
        while (true) {
            System.out.print("Enter account number: ");
            if (sc.hasNextInt()) {
                acc = sc.nextInt();
                if (acc > 0) break;
            } else sc.next();
            System.out.println("Invalid account number.");
        }
        return acc;
    }

    public static String validateName(Scanner sc) {
        String name;
        while (true) {
            System.out.print("Enter name: ");
            name = sc.next();
            if (name.matches("[A-Za-z]+")) break;
            System.out.println("Name must contain alphabets only.");
        }
        return name;
    }

    public static String validateEmail(Scanner sc) {
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = sc.next();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) break;
            System.out.println("Invalid email.");
        }
        return email;
    }

    public static long validateContact(Scanner sc) {
        long c;
        while (true) {
            System.out.print("Enter 10 digit contact number: ");
            if (sc.hasNextLong()) {
                c = sc.nextLong();
                if (String.valueOf(c).length() == 10) break;
            } else sc.next();
            System.out.println("Invalid contact.");
        }
        return c;
    }

    public static String validateAddress(Scanner sc) {
        sc.nextLine();
        String a;
        while (true) {
            System.out.print("Enter address: ");
            a = sc.nextLine();
            if (!a.trim().isEmpty()) break;
            System.out.println("Address cannot be empty.");
        }
        return a;
    }

    public static double validateAmount(Scanner sc, String label) {
        double amt;
        while (true) {
            System.out.print("Enter " + label + " amount: ");
            if (sc.hasNextDouble()) {
                amt = sc.nextDouble();
                if (amt >= 0) break;
            } else sc.next();
            System.out.println("Amount cannot be negative.");
        }
        return amt;
    }
}
