import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransactionById(UUID id);
    public Transaction[] toArray();
}
