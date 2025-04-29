package com.pluarslight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {
    public static void main(String[] args) {
        //Scanner function for user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Home Screen ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    // Calling add deposit method
                    addDeposit(scanner);
                    break;
                case "P":
                    // Calling make payment method
                    break;
                case "L":
                    // Calling view ledger method
                    break;
                case "X":
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void addDeposit(Scanner scanner) {
            System.out.println("Enter description: ");
            String description = scanner.nextLine();

            System.out.println("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.println("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            Transaction deposit = new Transaction(
                    LocalDate.now().toString(),
                    LocalTime.now().withNano(0).toString(),
                    description,
                    vendor,
                    amount
            );

            // Save the deposit to the file
            TransactionService.saveTransaction(deposit);

            System.out.println("Deposit saved successfully!");
        }



}