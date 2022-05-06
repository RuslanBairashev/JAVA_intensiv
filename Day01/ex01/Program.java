import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User John = new User( "John");
        User Mike = new User("Mike");
        User Tom = new User("Tom");

        System.out.println(John.toString());
        System.out.println(Mike.toString());
        System.out.println(Tom.toString());

    }
}
