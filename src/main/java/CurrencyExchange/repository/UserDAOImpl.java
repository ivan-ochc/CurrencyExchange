package currencyexchange.repository;

import currencyexchange.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);

    }

    @Override
    public int getUserId(String userName) {
        return (Integer) this.sessionFactory.getCurrentSession().createQuery("select u.id FROM User u where u.name = '"+userName+"' ").uniqueResult();
    }

    @Override
    public String getUserName(String userName) {
        return (String) this.sessionFactory.getCurrentSession().createQuery("select u.name FROM User u where u.name = '"+userName+"' ").uniqueResult();
    }


    @Override
    public User getUserObjectById(int userId) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
        return user;
    }

    @Override
    public List<User> getUserList() {
        return sessionFactory.getCurrentSession().createQuery("from User where role = 'ROLE_USER' ").list();
    }

    @Override
    public void deleteUser(User user) {
        if (user != null){
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public List<String> getAllEmails(String userName) {
        List<String> allEmailsList = sessionFactory.getCurrentSession().createQuery("select email from User where name != '" + userName + "'").list();
        return allEmailsList;
    }


}
