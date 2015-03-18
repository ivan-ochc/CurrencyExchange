package CurrencyExchange.service;

import CurrencyExchange.model.User;
import CurrencyExchange.vo.UserListVO;

public interface UserManager {

    public void saveUser(User user);
    public String getUser(User user);
    public int getUserId(String userName);
    public User getUserObject(int userId);
    public UserListVO getUserList();
    public void deleteUser(User user);
}
