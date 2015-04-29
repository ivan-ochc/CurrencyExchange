package currencyexchange.repository;

import currencyexchange.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public List<Order> getAllOrdersList(String userName) {
        List<Order> allOrdersList = sessionFactory.getCurrentSession().createQuery("from Order where userId not in (select id from User where name = '" + userName + "') order by orderDate desc").list();
        return allOrdersList;
    }

    @Override
    public void removeOrder(Order order) {
        if (order != null) {
            sessionFactory.getCurrentSession().delete(order);
        }
    }

    @Override
    public Order getOrder(int recordId) {
        Order order = (Order) sessionFactory.getCurrentSession().get(Order.class, recordId);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

}
