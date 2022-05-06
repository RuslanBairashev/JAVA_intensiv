import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User(1, "John");
        User Mike = new User(1, "Mike");

        John.setBalance(10000);
        Mike.setBalance(5000);
        Mike.setBalance(-7000);



        System.out.println(John.getName() + " has " + John.getBalance() + " golden dublons.");
        System.out.println(Mike.getName() + " has " + Mike.getBalance() + " golden dublons.");

        System.out.println();
        Transaction ts1 = new Transaction(UUID.randomUUID(), Mike, John, 500, Transaction.Category.INCOME);

        System.out.println("private UUID Identifier:" + ts1.getIdentifier().toString() +
                "\n    private User Recipient:" + ts1.getRecipient().getName() +
                "\n    private User Sender:" + ts1.getSender().getName() +
                "\n    private int Amount:" + ts1.getAmount() +
                "\n    private Category aCategory:" + ts1.getaCategory());

        System.out.println();
        Transaction ts2 = new Transaction(UUID.randomUUID(), Mike, John, -1500, Transaction.Category.INCOME);

        System.out.println("private UUID Identifier:" + ts2.getIdentifier().toString() +
                "\n    private User Recipient:" + ts2.getRecipient().getName() +
                "\n    private User Sender:" + ts2.getSender().getName() +
                "\n    private int Amount:" + ts2.getAmount() +
                "\n    private Category aCategory:" + ts2.getaCategory());
    }
}
