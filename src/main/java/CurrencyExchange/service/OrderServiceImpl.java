package CurrencyExchange.service;

import CurrencyExchange.model.Order;
import CurrencyExchange.repository.OrderDAO;
import CurrencyExchange.vo.OrdersListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    @Qualifier("recordD")
    private OrderDAO orderDAO;


    @Override
    @Transactional
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    @Transactional
    public List<Order> getOrdersList(String userName) {
        return orderDAO.getMyOrdersList(userName);
    }

    @Override
    @Transactional
    public void removeOrder(Order order) {
        orderDAO.removeOrder(order);
    }

    @Override
    @Transactional
    public long getSumOfAllCosts(String userName) {
        return orderDAO.getSumOfAllCosts(userName);
    }

    @Override
    @Transactional
    public Order getOrder(Integer recordId) {
        return orderDAO.getOrder(recordId);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrdersListVO findMyOrders(String userName) {
       List<Order> result = orderDAO.getMyOrdersList(userName);
       return new OrdersListVO(result);
    }

    @Override
    @Transactional(readOnly = true)
    public OrdersListVO findAllOrders(String userName) {
        List<Order> result = orderDAO.getAllOrdersList(userName);
        return new OrdersListVO(result);
    }

}
