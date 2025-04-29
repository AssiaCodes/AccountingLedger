package com.pluarslight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionService {

    private static final String FILE_NAME = "transactions.csv";

    // Method to save a transaction to the file
    public static void saveTransaction(Transaction transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            String line = transaction.getDate() + "|" + transaction.getTime() + "|" +
                    transaction.getDescription() + "|" + transaction.getVendor() + "|" +
                    transaction.getAmount();
            writer.println(line);
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        // Reverse to show newest first
        Collections.reverse(transactions);

        return transactions;

    }
}
