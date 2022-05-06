import java.util.UUID;

public class TransactionService {
    private UserList userList;

    public TransactionService() {
        this.userList = new UserArrayList();
    }

    static public class IllegalTransactionException extends RuntimeException {

    }

    public void addUserToList(User user) {
        userList.addUser(user);
    }

    public int getUserBalance(int id) {
        return userList.getById(id).getBalance();
    }

    public void makeTransaction(int recipient, int sender, int amount) {
        if (amount <= 0) {
            throw new IllegalTransactionException();
        }
        if (userList.getById(sender).getBalance() >= amount) {
            UUID uuid = UUID.randomUUID();
            Transaction outcome = new Transaction(uuid,
                    userList.getById(recipient),
                    userList.getById(sender),
                    -amount,
                    Transaction.Category.OUTCOME);
            Transaction income = new Transaction(uuid,
                    userList.getById(recipient),
                    userList.getById(sender),
                    amount,
                    Transaction.Category.INCOME);
            userList.getById(sender).getTransactionsList().addTransaction(outcome);
            userList.getById(recipient).getTransactionsList().addTransaction(income);
            userList.getById(sender).setBalance(userList.getById(sender).getBalance() - amount);
            userList.getById(recipient).setBalance(userList.getById(recipient).getBalance() + amount);
        } else {
            throw new IllegalTransactionException();
        }
    }

    public Transaction[] toArray(int client) {
        return userList.getById(client).getTransactionsList().toArray();
    }

    public void removeTransactionFromClient(UUID uuid, int client) {
        userList.getById(client).getTransactionsList().removeTransactionById(uuid);
    }

    public Transaction[] checkValidity() {
        TransactionLinkedList list = new TransactionLinkedList();
        Transaction head, tmp;
        if (userList.size() == 0) {
            return null;
        }
        for (int i = 0; i < userList.getByIndex(0).getTransactionsList().toArray().length; i++) {
            list.addTransaction(userList.getByIndex(0).getTransactionsList().toArray()[i]);
        }
        for (int i = 1; i < userList.size(); i++) {
            for (int j = 0; j < userList.getByIndex(i).getTransactionsList().toArray().length; j++) {
                head = list.getFirst();
                tmp = userList.getByIndex(i).getTransactionsList().toArray()[j];
                for (int k = 0; k < list.getSize(); k++) {
                    if (head.getIdentifier().equals(tmp.getIdentifier())) {
                        list.removeTransactionById(head.getIdentifier());
                        tmp = null;
                        break;
                    }
                    head = head.getNext();
                }
                if (tmp != null) {
                    list.addTransaction(tmp);
                }
            }
        }
        return list.toArray();
    }

}
