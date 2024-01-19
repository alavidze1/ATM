import java.sql.SQLOutput;
import java.util.Scanner;

public class ATM {
    Scanner in = new Scanner(System.in);
    public ATM(){

    }

    public void start() {
        System.out.println("Welcome to our ATM system!");
        System.out.println("Please enter your name to create a new account");
        String name = in.nextLine();
        System.out.println("Please enter a PIN security code. A 4 digit integer. Other values may cause error.");
        int pin = in.nextInt();
        in.nextLine();
        Customer customer = new Customer(name, pin);
        System.out.println("Now that you're registered, lets get you a Savings account and a Checking account.");
        Account savings = new Account("Savings", 0);
        Account checking = new Account("Checking", 0);
        savings.printAccountInfo();
        checking.printAccountInfo();
        System.out.println("There we go. Now you can access the true functions of the ATM.");
        int menuOption = 0;
        while(menuOption!=7){
            System.out.println("Enter the number corresponding to which operation you would like to do:\n1. Withdraw money\n" +
                    "2. Deposit money\n" +
                    "3. Transfer money between accounts\n" +
                    "4. Get account balances\n" +
                    "5. Get transaction history\n" +
                    "6. Change PIN\n" +
                    "7. Exit\n");
            menuOption = in.nextInt();
            in.nextLine();
            if(menuOption==1){
                System.out.println("Would you like to withdraw from your checking or savings account? Type 1 for savings and 2 for checking");
                int withdrawAccount = in.nextInt();
                in.nextLine();
                int withdrawAmount = -1;

                System.out.println("How much money would you like to deposit?");
                if(withdrawAccount==1){

                }
            }
        }
    }

}