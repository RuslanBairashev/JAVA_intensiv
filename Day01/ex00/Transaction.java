import java.util.UUID;

public class Transaction {
    private UUID Identifier;
    private User Recipient;
    private User Sender;
    private int Amount;
    private Category aCategory;

    public enum Category {
        OUTCOME,
        INCOME
    };

    public Transaction(UUID identifier, User recipient, User sender, int amount, Category aCategory) {
        Identifier = identifier;
        Recipient = recipient;
        Sender = sender;
        if ((aCategory.equals(Category.INCOME) && amount > 0) ||
                (aCategory.equals(Category.OUTCOME) && amount < 0)){
            Amount = amount;
        } else {
            System.out.println("Error: Amount not valid!");
        }
        this.aCategory = aCategory;
    }

    public UUID getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(UUID identifier) {
        Identifier = identifier;
    }

    public User getRecipient() {
        return Recipient;
    }

    public void setRecipient(User recipient) {
        Recipient = recipient;
    }

    public User getSender() {
        return Sender;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        if ((aCategory.equals(Category.INCOME) && amount > 0) ||
                (aCategory.equals(Category.OUTCOME) && amount < 0)){
            Amount = amount;
        } else {
            System.out.println("Error: Amount not valid!");
        }
    }

    public Category getaCategory() {
        return aCategory;
    }

    public void setaCategory(Category aCategory) {
        this.aCategory = aCategory;
    }
}
