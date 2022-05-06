public interface UserList {
    public void addUser(User user);
    public User getById(int id);
    public User getByIndex(int index);
    public int size();
}
