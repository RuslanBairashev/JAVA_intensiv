import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User( "John");
        User Mike = new User( "Mike");
        User Tom = new User( "Tom");

        John.setBalance(10000);
        Mike.setBalance(5000);
        Tom.setBalance(7000);

        TransactionService service = new TransactionService();
        service.addUserToList(John);
        service.addUserToList(Mike);
        service.addUserToList(Tom);

        service.makeTransaction(John.getIdentifier(), Mike.getIdentifier(), 700);
        service.makeTransaction(John.getIdentifier(), Tom.getIdentifier(), 1000);
        service.makeTransaction(John.getIdentifier(), Tom.getIdentifier(), 1000);
        service.makeTransaction(Tom.getIdentifier(), John.getIdentifier(), 1300);
        service.makeTransaction(Mike.getIdentifier(), Tom.getIdentifier(), 2000);

        for (Transaction t: service.toArray(John.getIdentifier())) {
            System.out.println(t);
        }
        service.removeTransactionFromClient(John.getTransactionsList().toArray()[0].getIdentifier(),
                John.getIdentifier());
        service.removeTransactionFromClient(Tom.getTransactionsList().toArray()[0].getIdentifier(),
                Tom.getIdentifier());
        System.out.println("\nTransaction deleted!!!!");
        for (Transaction t: service.toArray(John.getIdentifier())) {
            System.out.println(t);
        }
        System.out.println("\nUnpaired transactions!!!!");
        Transaction[] removed = service.checkValidity();
        for (Transaction t: removed) {
            System.out.println(t);
        }
    }
}
