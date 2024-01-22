import java.sql.SQLOutput;
import java.util.Scanner;

public class ATM {
    //private instance variables
    private TransactionHistory transactionHistory;
    Scanner in = new Scanner(System.in);
    private int STransaction;
    private int ATransaction;
    private Account savings;
    private Account checking;
    private String name;
    private int pin;
    private boolean doAnythingElse;
    private String anythingElse;
    private Customer customer;
    private int menuOption;
    private int checkpin;
    //colors for printing later
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public ATM(){
        //introduction and setting values
        this.transactionHistory = new TransactionHistory();
        this.STransaction = 0;
        this.ATransaction = 0;
        System.out.println(GREEN + "Welcome to our ATM system!" + RESET);
        System.out.println(YELLOW + "Please enter your name to create a new account");
        this.name = in.nextLine();
        System.out.println("Please enter a PIN security code. A 4 digit integer."+RESET + RED + " Other values may cause error." + RESET);
        this.pin = in.nextInt();
        this.customer = new Customer(name, pin);
        this.savings = new Account("Savings", 0);
        this.checking = new Account("Checking", 0);
        System.out.println(GREEN + "Now that you're registered, lets get you a Savings account and a Checking account." + RESET);
        savings.printAccountInfo();
        checking.printAccountInfo();
        this.doAnythingElse = true;
        this.menuOption = 0;
        this.checkpin = pin;
        System.out.println(GREEN + "There we go. Now you can access the true functions of the ATM." + RESET);

        start();
    }

    public void start() {
        while((menuOption!=7 && checkpin==pin)&doAnythingElse){

            System.out.println(GREEN+"Enter the number corresponding to which operation you would like to do:" + YELLOW +"\n1. Withdraw money\n"+
                    "2. Deposit money\n" +
                    "3. Transfer money between accounts\n" +
                    "4. Get account balances\n" +
                    "5. Get transaction history\n" +
                    "6. Change PIN\n" +
                    "7. Exit\n");
            menuOption = in.nextInt();
            in.nextLine();
            if(menuOption==1){
                System.out.println(YELLOW + "Would you like to withdraw from your checking or savings account? Type 1 for checking and 2 for savings" + RESET);
                int withdrawAccount = in.nextInt();
                in.nextLine();
                double withdrawAmount = -1;
                System.out.println(YELLOW + "How much money would you like to withdraw?" + RESET);
                withdrawAmount = in.nextDouble();
                in.nextLine();


                if(withdrawAccount==1){
                    if(checking.getCurrentBalance()<withdrawAmount){
                        System.out.println(RED + "Insufficient Funds!" + RESET);
                    }else if(withdrawAmount%(5.00)!=0){
                        System.out.println("This ATM only has $5 Bills and $20 Bills. That amount is unable to be outputted with any combination of these bills.");
                    }else{
                        checking.decreaseBalance(withdrawAmount);
                        System.out.println(GREEN + "Amount withdrawn successfully"+ RESET);
                        System.out.println(YELLOW + "Transaction ID: A" + String.format("%04d", ATransaction) +RESET);
                        transactionHistory.addTransaction("A" + String.format("%04d", ATransaction));
                        ATransaction++;
                        checking.printAccountInfo();
                        if (withdrawAmount < 20) {
                            System.out.println("The requested amount will be given in " + (int) (withdrawAmount / 5) + "$5 bills.");
                        } else {
                            System.out.println(YELLOW + "How many $20 bills would you like?"+RESET);
                            int twenties = in.nextInt();
                            in.nextLine();
                            if (twenties == 0) {
                                System.out.println(GREEN + "Successfully outputted " + (withdrawAmount - (twenties * 20)) / 5 + " $5 bills." + RESET);
                            } else if (twenties == 1) {
                                System.out.println(GREEN + "Successfully outputted 1 $20 bill and " + (withdrawAmount - (twenties * 20)) / 5 + " $5 bills." + RESET);
                            }else if((twenties*20)>withdrawAmount){
                                System.out.println("Invalid amount of 20s. Outputting " + (int)(withdrawAmount/20) + "$20s and " + (int)(withdrawAmount%5.00) + "$5 bills.");
                            } else {
                                System.out.println(GREEN + "Successfully outputted " + twenties + " $20 bills and " + (withdrawAmount - (twenties * 20)) / 5 + " $5 bills."+ RESET);
                            }
                        }

                    }
                }else if(withdrawAccount==2) {
                    if (savings.getCurrentBalance() < withdrawAmount) {
                        System.out.println(RED + "Insufficient Funds!" + RESET);
                    } else if (withdrawAmount % (5.00) != 0) {
                        System.out.println("This ATM only has $5 Bills and $20 Bills. That amount is unable to be outputted with any combination of these bills.");
                    } else {
                        savings.decreaseBalance(withdrawAmount);
                        System.out.println("Amount withdrawn successfully");

                        System.out.println(YELLOW + "Transaction ID: A" + String.format("%04d", ATransaction)+ RESET);
                        transactionHistory.addTransaction("A" + String.format("%04d", ATransaction));
                        ATransaction++;
                        savings.printAccountInfo();
                        if (withdrawAmount < 20) {
                            System.out.println("The requested amount will be given in " + (int) (withdrawAmount / 5) + "$5 bills.");
                        } else {
                            System.out.println(YELLOW + "How many $20 bills would you like?"+RESET);
                            int twenties = in.nextInt();
                            in.nextLine();
                            if (twenties == 0) {
                                System.out.println(GREEN + "Successfully outputted " + (withdrawAmount - (twenties * 20)) / 5 + " $5 bills." + RESET);
                            } else if (twenties == 1) {
                                System.out.println(GREEN + "Successfully outputted 1 $20 bill and " + (withdrawAmount - (twenties * 20)) / 5 + " $5 bills." + RESET);
                            }else if((twenties*20)>withdrawAmount){
                                System.out.println("Invalid amount of 20s. Outputting " + (int)(withdrawAmount/20) + " $20s and " + (int)(withdrawAmount%5.00) + " $5 bills.");
                            } else {
                                System.out.println(GREEN + "Successfully outputted " + twenties + " $20 bills and " + (withdrawAmount - (twenties * 20)) / 5 + " $5 bills."+ RESET);
                            }
                        }
                    }
                }


            }else if(menuOption==2){
                System.out.println(YELLOW + "Which account are you depositing money into? 1 for checking 2 for savings." + RESET);
                int depositAccount = in.nextInt();
                in.nextLine();
                System.out.println(YELLOW +"How much money would you like to Deposit?"+ RESET);
                double deposit = in.nextDouble();
                in.nextLine();
                if(depositAccount==1){
                    checking.increaseBalance(deposit);
                    System.out.println(GREEN + "$"+ deposit + " succesfully deposited into the checkings account." + RESET);
                    System.out.println(YELLOW + "Transaction ID: A" + String.format("%04d", ATransaction) + RESET);
                    transactionHistory.addTransaction("A" + String.format("%04d", ATransaction));
                    ATransaction++;
                    checking.printAccountInfo();
                }else{
                    savings.increaseBalance(deposit);
                    System.out.println(GREEN + "$"+ deposit + " succesfully deposited into the savings account." + RESET);
                    System.out.println(YELLOW + "Transaction ID: A" + String.format("%04d", ATransaction) + RESET);
                    transactionHistory.addTransaction("A" + String.format("%04d", ATransaction));
                    ATransaction++;
                    savings.printAccountInfo();
                }


            }else if(menuOption==3){
                System.out.println(YELLOW + "From which account are you transferring money? 1 For checking 2 for savings"+ RESET);
                int transferFrom = in.nextInt();
                in.nextLine();
                System.out.println(YELLOW + "How much money are you transferring?"+ RESET);
                double transferAmount = in.nextDouble();
                in.nextLine();
                if(transferFrom==1){
                    if(checking.getCurrentBalance()<transferAmount){
                        System.out.println(RED + "Insufficient funds" + RESET);
                    }else{
                        checking.decreaseBalance(transferAmount);
                        savings.increaseBalance(transferAmount);
                        System.out.println("Transaction ID: A" + String.format("%04d", ATransaction));
                        transactionHistory.addTransaction(YELLOW + "A" + String.format("%04d", ATransaction) + RESET);
                        ATransaction++;
                        savings.printAccountInfo();
                        checking.printAccountInfo();
                        savings.printAccountInfo();
                    }

                }
                else{
                    if(savings.getCurrentBalance()<transferAmount){
                        System.out.println(RED + "Insufficient funds" + RESET);
                    }else{
                        checking.increaseBalance(transferAmount);
                        savings.decreaseBalance(transferAmount);
                        System.out.println(YELLOW + "Transaction ID: A" + String.format("%04d", ATransaction)+RESET);
                        transactionHistory.addTransaction("A" + String.format("%04d", ATransaction));
                        ATransaction++;
                        savings.printAccountInfo();
                        savings.printAccountInfo();
                        checking.printAccountInfo();
                    }
                }
            }
            else if(menuOption==4){
                savings.printAccountInfo();
                checking.printAccountInfo();
                System.out.println(GREEN + "Account info succesfully displayed." + RESET);
                System.out.println(YELLOW + "Transaction ID: S" + String.format("%04d", STransaction) + RESET);
                transactionHistory.addTransaction("S" + String.format("%04d", STransaction));
                STransaction++;
            }else if(menuOption==5){
                System.out.println(YELLOW +"Transaction ID: S" + String.format("%04d", STransaction)+RESET);
                transactionHistory.addTransaction("S" + String.format("%04d", STransaction));
                STransaction++;
                for (int i = 0; i < transactionHistory.getTransactions().size(); i++) {
                    System.out.println(YELLOW + transactionHistory.getTransactions().get(i)+ RESET);
                }
            }
            else if(menuOption==6){
                System.out.println(YELLOW + "What would you like to be your new PIN?" + RESET);
                pin = in.nextInt();
                in.nextLine();
                customer.updatePin(pin);
                System.out.println(GREEN + "Pin Successfully updated."+ RESET);
                System.out.println(YELLOW + "Transaction ID: S" + String.format("%04d", STransaction) + RESET);
                transactionHistory.addTransaction("S" + String.format("%04d", STransaction));
                STransaction++;
            }
            System.out.println(YELLOW + "Would you like to do anything else? type yes or no" + RESET);
            anythingElse = in.nextLine();
            if(anythingElse.equals("no")){
                doAnythingElse = false;
            }
            else{
                System.out.println( YELLOW + "To do anything else, please enter your PIN again as a security measure." +RESET);
                checkpin = in.nextInt();
                in.nextLine();
            }
        }
        System.out.println(GREEN + "Thank you for using our ATM Machine. Come back again!" + RESET);
    }
}