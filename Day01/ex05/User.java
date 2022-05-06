public class User {

    private int Identifier;
    private String Name;
    private int Balance;
    private TransactionsList transactionsList;

    public User(String name) {
        Identifier = UserIdsGenerator.getInstance().generateId();
        Name = name;
        transactionsList = new TransactionLinkedList();
    }

    public int getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(int identifier) {
        Identifier = identifier;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        if (balance >= 0) {
            Balance = balance;
        } else {
            System.out.println("Error: Amount not valid!");
        }
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(TransactionsList transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + Identifier +
                ", Name='" + Name + '\'' +
                ", Balance=" + Balance +
                '}';
    }
}
