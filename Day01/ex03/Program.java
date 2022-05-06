import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User( "John");
        User Mike = new User( "Mike");

        John.setBalance(10000);
        Mike.setBalance(5000);

        Transaction ts1 = new Transaction(UUID.randomUUID(), Mike, John,
                500, Transaction.Category.INCOME);
        Transaction ts2 = new Transaction(UUID.randomUUID(), John, Mike,
                1500, Transaction.Category.INCOME);
        Transaction ts3 = new Transaction(UUID.randomUUID(), John, Mike,
                100, Transaction.Category.INCOME);

        TransactionLinkedList list = new TransactionLinkedList();
        list.addTransaction(ts1);
        list.addTransaction(ts2);
        list.addTransaction(ts3);

        Transaction[] array = new Transaction[list.getSize()];
        array = list.toArray();
        System.out.println("Array size = " +array.length);
        for (Transaction t: array) {
            System.out.println(t);
        }

        list.removeTransactionById(ts2.getIdentifier());
        System.out.println("List size = " + list.getSize());
        list.removeTransactionById(ts1.getIdentifier());
        System.out.println("List size = " + list.getSize());
        list.removeTransactionById(ts3.getIdentifier());
        System.out.println("List size = " + list.getSize());

        Transaction ts4 = new Transaction(UUID.randomUUID(), John, Mike,
                100, Transaction.Category.INCOME);
        list.removeTransactionById(ts4.getIdentifier());
    }
}
