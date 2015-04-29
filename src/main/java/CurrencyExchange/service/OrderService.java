package currencyexchange.service;

import currencyexchange.model.Order;
import currencyexchange.vo.OrdersListVO;

public interface OrderService {

    public void addOrder(Order order);

//    public List<Order> getOrdersList(String userName);

    public void removeOrder(Order order);

    public Order getOrder(int recordId);

    public void updateOrder(Order order);

    public OrdersListVO findMyOrders(String userName);

    public OrdersListVO findAllOrders(String userName);
}
