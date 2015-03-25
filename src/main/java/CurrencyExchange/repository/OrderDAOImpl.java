package currencyexchange.repository;

import currencyexchange.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);

    }

    @Override
    public List<Order> getMyOrdersList(String userName) {
        return sessionFactory.getCurrentSession().createQuery("from Order where userId = (select id from User where name = '" + userName + "') order by orderDate desc").list();
    }

    @Override
    public List getAllOrdersList(String userName) {
        return sessionFactory.getCurrentSession().createQuery("from Order where userId not in (select id from User where name = '" + userName + "') order by orderDate desc").list();
    }

    @Override
    public void removeOrder(Order order) {
        if (order != null) {
            sessionFactory.getCurrentSession().delete(order);
        }
    }

    @Override
    public long getSumOfAllCosts(String userName) {
        List<Long> sum = sessionFactory.getCurrentSession().createQuery("select sum(price) from Order where userId = (select id from User where name = '" + userName + "')").list();
        long latestSum = 0;
        if (!sum.contains(null)) {
        for (long sumInList : sum) {
            latestSum = sumInList;
            }
        }  else {
                 return latestSum;
         }
      return latestSum;

}

    @Override
    public Order getOrder(Integer recordId) {
        Order order = (Order) sessionFactory.getCurrentSession().get(Order.class, recordId);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

}
