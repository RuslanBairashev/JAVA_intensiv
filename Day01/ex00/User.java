public class User {

    private int Identifier;
    private String Name;
    private int Balance;

    public User(int identifier, String name) {
        Identifier = identifier;
        Name = name;
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
}
