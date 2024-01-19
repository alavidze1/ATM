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
                double withdrawAmount = -1;
                withdrawAmount = in.nextDouble();
                in.nextLine();
                System.out.println("How much money would you like to deposit?");

                if(withdrawAccount==1){
                    if(checking.getCurrentBalance()<withdrawAmount){
                        System.out.println("Insufficient Funds!");
                    }else if(withdrawAmount%(5.00)!=0){
                        System.out.println("This ATM only has $5 Bills and $20 Bills. That amount is unable to be outputted with any combination of these bills.");
                    }else{
                        checking.decreaseBalance(withdrawAmount);
                        System.out.println("Amount withdrawn successfully");
                        checking.printAccountInfo();
                    }
                }

                else if(withdrawAccount==2){
                        if(savings.getCurrentBalance()<withdrawAmount){
                            System.out.println("Insufficient Funds!");
                        }else if(withdrawAmount%(5.00)!=0){
                            System.out.println("This ATM only has $5 Bills and $20 Bills. That amount is unable to be outputted with any combination of these bills.");
                        }else{
                            savings.decreaseBalance(withdrawAmount);
                            System.out.println("Amount withdrawn successfully");
                            savings.printAccountInfo();
                        }
                }

            }else if(menuOption==2){
                System.out.println("Which account are you depositing money into? 1 for checking 2 for savings.");
                int depositAccount = in.nextInt();
                in.nextLine();
                System.out.println("How much money would you like to Deposit?");
                double deposit = in.nextDouble();
                in.nextLine();
                if(depositAccount==1){
                    checking.increaseBalance(deposit);
                    System.out.println("$ "+ deposit + " succesfully deposited into the checkings account.");
                    checking.printAccountInfo();
                }else{
                    savings.increaseBalance(deposit);
                    System.out.println("$ "+ deposit + " succesfully deposited into the savings account.");
                    savings.printAccountInfo();
                }


            }

        }
    }

}