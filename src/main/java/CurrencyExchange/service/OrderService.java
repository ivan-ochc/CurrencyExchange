package currencyexchange.service;

import currencyexchange.model.Order;
import currencyexchange.vo.OrdersListVO;

import java.util.List;

public interface OrderService {

    public void addOrder(Order order);

    public List<Order> getOrdersList(String userName);

    public void removeOrder(Order order);

    public long getSumOfAllCosts(String userName);

    public Order getOrder(Integer recordId);

    public void updateOrder(Order order);

    public OrdersListVO findMyOrders(String userName);

    public OrdersListVO findAllOrders(String userName);
}
