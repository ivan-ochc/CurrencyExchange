package CurrencyExchange.repository;

import CurrencyExchange.model.User;

import java.util.List;

public interface UserDAO {

    public void saveUser(User user);
    public String getUser(User user);
    public int getUserId(String userName);
    public User getUserObject(int userId);
    public List getUserList();
    public void deleteUser(User user);
}
