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
                    makePayment(scanner);
                    break;
                case "L":
                    // Calling view ledger method
                    viewLedger(scanner);
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

    public static void makePayment(Scanner scanner) {
        System.out.println("=== Make a Payment ===");

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > 0) {
            amount = -amount; // payments should be NEGATIVE
        }

        //using  transaction object to send payment info
        Transaction payment = new Transaction(
                LocalDate.now().toString(),
                LocalTime.now().withNano(0).toString(),
                description,
                vendor,
                amount
        );

        TransactionService.saveTransaction(payment);

        System.out.println("Payment recorded successfully!");
    }


    public static void viewLedger(Scanner scanner) {
        List<Transaction> transactions = TransactionService.loadTransactions();

        boolean viewing = true;
        while (viewing) {
            System.out.println("=== Ledger Menu ===");
            System.out.println("A) All");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    System.out.println("=== All Transactions ===");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;
                case "D":
                    System.out.println("=== Deposits Only ===");
                    for (Transaction t : transactions) {
                        if (t.getAmount() >= 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "P":
                    System.out.println("=== Payments Only ===");
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "R":
                    viewReports(scanner, transactions);
                    break;
                case "H":
                    viewing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void viewReports(Scanner scanner, List<Transaction> transactions) {
        boolean reporting = true;

        while (reporting) {
            System.out.println("=== Reports Menu ===");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose a report option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showMonthToDate(transactions);
                    break;
                case "2":
                    showPreviousMonth(transactions);
                    break;
                case "3":
                    showYearToDate(transactions);
                    break;
                case "4":
                    showPreviousYear(transactions);
                    break;
                case "5":
                    searchByVendor(scanner, transactions);
                    break;
                case "0":
                    reporting = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

    }

    private static void showMonthToDate(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getDate());
            if (transactionDate.getYear() == today.getYear() && transactionDate.getMonth() == today.getMonth()) {
                System.out.println(t);
            }
        }

    }

    public static void showPreviousMonth(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);

        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getDate());
            if (transactionDate.getYear() == previousMonth.getYear() && transactionDate.getMonth() == previousMonth.getMonth()) {
                System.out.println(t);
            }
        }
    }
    public static void showYearToDate(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getDate());
            if (transactionDate.getYear() == today.getYear()) {
                System.out.println(t);
            }
        }
    }
    public static void showPreviousYear(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        int previousYear = today.getYear() - 1;

        for (Transaction t : transactions) {
            LocalDate transactionDate = LocalDate.parse(t.getDate());
            if (transactionDate.getYear() == previousYear) {
                System.out.println(t);
            }
        }
    }
    public static void searchByVendor(Scanner scanner, List<Transaction> transactions) {
        System.out.print("Enter vendor name to search: ");
        String vendorName = scanner.nextLine().toLowerCase();

        for (Transaction t : transactions) {
            if (t.getVendor().toLowerCase().contains(vendorName)) {
                System.out.println(t);
            }
        }
    }




}