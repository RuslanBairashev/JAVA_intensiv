public class User {

    private int Identifier;
    private String Name;
    private int Balance;

    public User(String name) {
        Identifier = UserIdsGenerator.getInstance().generateId();
        Name = name;
    }

    public int getIdentifier() {
        return Identifier;
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

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + Identifier +
                ", Name='" + Name + '\'' +
                ", Balance=" + Balance +
                '}';
    }
}
