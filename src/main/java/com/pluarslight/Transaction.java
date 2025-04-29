package com.pluarslight;

//Initialize the class Transaction
public class Transaction {
    // Crating private variables
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;
//Creating class constructor with parameters
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }

    // toString method for easy printing
    @Override
    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}
