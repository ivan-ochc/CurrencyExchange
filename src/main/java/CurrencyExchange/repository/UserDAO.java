package currencyexchange.repository;

import currencyexchange.model.User;

import java.util.List;

public interface UserDAO {

    public void saveUser(User user);
    public int getUserId(String userName);
    public String getUserName(String userName);
    public User getUserObjectById(int userId);
    public List<User> getUserList();
    public void deleteUser(User user);
    public List<String> getAllEmails(String userName);
}
