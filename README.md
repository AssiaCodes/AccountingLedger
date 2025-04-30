Capstone 1:  
Accounting Ledger Application 
Java CLI Application 

This is a command-line Java application that helps users track financial transactions such as deposits and payments.
All data is stored in a CSV file (transactions.csv) and includes information such as date, time, description, vendor, and amount. Users can view ledgers, generate reports, and perform searches — all directly from the command line.

Features
-> Add Deposit – Record positive income entries(credits)

-> Make Payment – Record negative expenses (debits)

-> Ledger – View transactions -Filter by:

    All entries

    Deposits only

    Payments only

    Reports -further filter by:

        Month-to-date

        Previous month

        Year-to-date

        Previous year

        Vendor search

All transactions saved to transactions.csv


Example Code Highlight
One interesting part of this project is how deposit method is used to add to the transaction.csv file:


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
This demonstrates basic but effective use of scanner to get user input and add that with date and time.

Screenshots 

Home Screen --> ![1](https://github.com/user-attachments/assets/dfbcef8c-4b0c-4aff-8ff3-d6ef476f4692)


Ledger View

Reports Menu

Vendor Search Result

How to Run

-> Clone the repo
-> Open in your Java IDE (e.g., IntelliJ, Eclipse)
-> Run LedgerApp.java
-> Follow the on-screen instructions
-> Make sure a transactions.csv file is present in the same directory (or the program will create it automatically).
