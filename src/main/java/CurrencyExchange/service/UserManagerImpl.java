package CurrencyExchange.service;

import CurrencyExchange.model.User;
import CurrencyExchange.repository.UserDAO;
import CurrencyExchange.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManagerImpl implements UserManager {
    @Autowired
    @Qualifier("userDQ")
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);

    }

    @Override
    @Transactional
    public String getUser(User user) {
       return userDAO.getUser(user);
    }

    @Override
    @Transactional
    public int getUserId(String userName) {
        return userDAO.getUserId(userName);
    }

    @Override
    @Transactional
    public User getUserObject(int userId) {
        return userDAO.getUserObject(userId);
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
}
