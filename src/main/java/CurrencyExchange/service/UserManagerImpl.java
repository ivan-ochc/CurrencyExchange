package currencyexchange.service;

import currencyexchange.model.User;
import currencyexchange.repository.UserDAO;
import currencyexchange.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class UserManagerImpl implements UserManager {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);

    }

    @Override
    @Transactional
    public int getUserId(String userName) {
        return userDAO.getUserId(userName);
    }

    @Override
    @Transactional
    public String getUserName(String userName) {
        return userDAO.getUserName(userName);
    }

    @Override
    @Transactional
    public User getUserObjectById(int userId) {
        return userDAO.getUserObjectById(userId);
    }

    @Override
    @Transactional
    public UserListVO getUserList() {
        List<User> userList = userDAO.getUserList();
        return new UserListVO(userList);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    @Transactional
    public List<String> getAllEmails(String userName) {
        return userDAO.getAllEmails(userName);
    }
}
