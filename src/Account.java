public class Account {
    private String accountName;
    private double currentBalance;
    public Account(String accountName, double currentBalance){
        this.accountName = accountName;
        this.currentBalance = currentBalance;
    }
    public double getCurrentBalance(){
        return currentBalance;
    }
    public void updateBalance(double balChange){
        currentBalance+=balChange;
    }
    public void printAccountInfo(){
        System.out.println(accountName + "Balance: $" + currentBalance);
    }
    public void increaseBalance(double amount){currentBalance+=amount;}
    public void decreaseBalance(double amount){currentBalance-=amount;}
}
