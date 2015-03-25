package currencyexchange.repository;

import currencyexchange.model.Order;

import java.util.List;

public interface OrderDAO {

    public void addOrder(Order order);

    public List getMyOrdersList(String userName);

    public List getAllOrdersList(String userName);

    public void removeOrder(Order order);

    public long getSumOfAllCosts(String userName);

    public Order getOrder(Integer recordId);

    public void updateOrder(Order order);

}
