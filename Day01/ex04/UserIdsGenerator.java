public class UserIdsGenerator {

    final static private UserIdsGenerator singleObject = new UserIdsGenerator();
    static public UserIdsGenerator getInstance() {
        return singleObject;
    }

    static private int id = 0;
    private UserIdsGenerator() { }

    public int generateId() {
        return ++id;
    }
}
