import java.util.ArrayList;
import java.util.List;
public class TransactionHistory {
    private List<String> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public List<String> getTransactions() {
        return transactions;
    }
}