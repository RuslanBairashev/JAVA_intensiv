public class UserArrayList implements UserList{
    private User[] userList;
    private int size = 0;

    public UserArrayList() {
        userList = new User[10];
    }

    static public class UserNotFoundException extends RuntimeException {

    }

    @Override
    public void addUser(User user) {
        if (size < userList.length) {
            userList[size] = user;
            size++;
        } else {
            User[] newList = new User[userList.length + userList.length / 2];
            for (int i = 0; i < userList.length; i++) {
                newList[i] = userList[i];
            }
            userList = newList;
            userList[size] = user;
            size++;
        }
    }

    @Override
    public User getById(int id) {
        for (int i = 0; i < size; i++) {
            if (userList[i].getIdentifier() == id) {
                return userList[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(int index) {
        return userList[index];
    }

    @Override
    public int size() {
        return size;
    }
}
