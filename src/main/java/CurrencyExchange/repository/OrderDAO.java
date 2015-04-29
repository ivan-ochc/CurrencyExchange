package currencyexchange.repository;

import currencyexchange.model.Order;

import java.util.List;

public interface OrderDAO {

    public void addOrder(Order order);

    public List<Order> getMyOrdersList(String userName);

    public List<Order> getAllOrdersList(String userName);

    public void removeOrder(Order order);

    public Order getOrder(int recordId);

    public void updateOrder(Order order);

}
