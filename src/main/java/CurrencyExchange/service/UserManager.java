package currencyexchange.service;

import currencyexchange.model.User;
import currencyexchange.vo.UserListVO;

public interface UserManager {

    public void saveUser(User user);
    public String getUser(User user);
    public int getUserId(String userName);
    public User getUserObject(int userId);
    public UserListVO getUserList();
    public void deleteUser(User user);
}
