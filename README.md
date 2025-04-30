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


Example Code Highlight:

One interesting part of this project is how deposit method is used to add to the transaction.csv file: This demonstrates basic but effective use of scanner to get user input and add that with date and time to the transaction table.


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

Screenshots 

Home Screen
![1](https://github.com/user-attachments/assets/dfbcef8c-4b0c-4aff-8ff3-d6ef476f4692)

Add deposit
![2](https://github.com/user-attachments/assets/eec043dc-0c09-44b1-bdd6-4e78e09217f5)


Add payment

![3](https://github.com/user-attachments/assets/6bc57cb6-cbe9-447f-b2af-2cb07213ec98)


Ledger View

![4](https://github.com/user-attachments/assets/6adae685-f5ae-4c54-bdac-bd5dc20f9033)


Deposits only & payments only view

![5](https://github.com/user-attachments/assets/4e120c72-a57f-47b2-9ef5-7d98d0b7ace7)


Reports Menu

![6](https://github.com/user-attachments/assets/9e553c15-0ea8-4b93-a72e-cbc29448f03f)


Vendor Search Result

![7](https://github.com/user-attachments/assets/874ee00f-5699-4988-aaa3-450e33bfa3ad)


How to Run

-> Clone the repo
-> Open in your Java IDE (e.g., IntelliJ, Eclipse)
-> Run LedgerApp.java
-> Follow the on-screen instructions
-> Make sure a transactions.csv file is present in the same directory (or the program will create it automatically).
