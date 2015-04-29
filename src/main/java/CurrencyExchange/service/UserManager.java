package currencyexchange.service;

import currencyexchange.model.User;
import currencyexchange.vo.UserListVO;

import java.util.List;

public interface UserManager {

    public void saveUser(User user);
    public int getUserId(String userName);
    public String getUserName(String userName);
    public User getUserObjectById(int userId);
    public UserListVO getUserList();
    public void deleteUser(User user);
    public List<String> getAllEmails(String userName);
}
