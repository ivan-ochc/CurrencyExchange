package currencyexchange.repository;

import currencyexchange.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    @Qualifier("ses")
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);

    }

    @Override
    public String getUser(User user) {
       Query query = this.sessionFactory.getCurrentSession().createQuery("select u.name FROM User u where u.name ='"+user.getName()+"' ");
        List<String> nameList = query.list();
        String name = null;

        for (String nameInList : nameList) {
             name = nameInList;
        }
        return name;
    }

    @Override
    public int getUserId(String userName) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select u.id FROM User u where u.name = '"+userName+"' ");
        List<Integer> idList = query.list();
        int userId = 0;

        for (int idInList : idList) {
            userId = idInList;
        }
        return userId;
    }

    @Override
    public User getUserObject(int userId) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
        return user;
    }

    @Override
    public List getUserList() {
        return sessionFactory.getCurrentSession().createQuery("from User where role = 'ROLE_USER' ").list();
    }

    @Override
    public void deleteUser(User user) {
        if (user != null){
            sessionFactory.getCurrentSession().delete(user);
        }
    }


}
