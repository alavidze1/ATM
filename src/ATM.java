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
                            if(withdrawAmount<20){
                                System.out.println("The requested amount will be given in " + (int)(withdrawAmount/5) + "$5 bills.");
                            }
                            else{
                                System.out.println("How many $20 bills would you like?");
                                int twenties = in.nextInt();
                                in.nextLine();
                                if(twenties==0){
                                    System.out.println("Successfully outputted " + (withdrawAmount-(twenties*20))/5 + " $5 bills.");
                                }
                                else if(twenties ==1){
                                    System.out.println("Successfully outputted 1 $20 bill and " + (withdrawAmount-(twenties*20))/5 + " $5 bills.");
                                }
                                else{
                                    System.out.println("Successfully outputted " + twenties + " $20 bills and " + (withdrawAmount-(twenties*20))/5 + " $5 bills.");
                                }
                            }
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


            }else if(menuOption==3){
                System.out.println("From which account are you transferring money? 1 For checking 2 for savings");
                int transferFrom = in.nextInt();
                in.nextLine();
                double transferAmount = in.nextDouble();
                in.nextLine();
                if(transferFrom==1){
                    if(checking.getCurrentBalance()<transferAmount){
                        System.out.println("insufficient funds");
                    }else{
                        checking.decreaseBalance(transferAmount);
                        savings.increaseBalance(transferAmount);
                        checking.printAccountInfo();
                        savings.printAccountInfo();
                    }

                }
                else{
                    if(savings.getCurrentBalance()<transferAmount){
                        System.out.println("insufficient funds");
                    }else{
                        checking.increaseBalance(transferAmount);
                        savings.decreaseBalance(transferAmount);
                        savings.printAccountInfo();
                        checking.printAccountInfo();
                    }
                }
            }
            else if(menuOption==4){
                savings.printAccountInfo();
                checking.printAccountInfo();
            }else if(menuOption==5){
                System.out.println("Work in progress. Need to implement transaction history.");
            }
            else if(menuOption==6){
                System.out.println("What would you like to be your new PIN?");
                int newPin = in.nextInt();
                in.nextLine();
                customer.updatePin(newPin);
            }

        }
    }

}