public class Account {
    private Customer owner;
    private String accountName;
    private double currentBalance;
    public Account(Customer owner, String accountName, double currentBalance){
        this.owner = owner;
        this.accountName = accountName;
        this.currentBalance = currentBalance;
    }
    public double getCurrentBalance(){
        return currentBalance;
    }
    public void updateBalance(double balChange){
        currentBalance+=balChange;
    }
}
