import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        UserArrayList list = new UserArrayList();
        list.addUser(new User( "John"));
        list.addUser(new User( "Mike"));
        for (int i = 0; i < 8; i++) {
            list.addUser(new User( "Tom"));
        }
        list.addUser(new User( "Jack"));
        list.addUser(new User( "Chuck"));

        System.out.println("Size of list is " + list.size());
        System.out.println("At id 2 is: " + list.getById(2).getName());
        System.out.println("At index 11 is: " + list.getByIndex(11).getName());

        System.out.println("At id 20 is: " + list.getById(20).getName());


    }
}
