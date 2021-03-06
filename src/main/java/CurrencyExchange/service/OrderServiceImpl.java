package currencyexchange.service;

import currencyexchange.model.Order;
import currencyexchange.repository.OrderDAO;
import currencyexchange.vo.OrdersListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;


    @Override
    @Transactional
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

//    @Override
//    @Transactional
//    public List<Order> getOrdersList(String userName) {
//        return orderDAO.getMyOrdersList(userName);
//    }

    @Override
    @Transactional
    public void removeOrder(Order order) {
        orderDAO.removeOrder(order);
    }

    @Override
    @Transactional
    public Order getOrder(int recordId) {
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
