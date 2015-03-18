package CurrencyExchange.vo;

import CurrencyExchange.model.Order;

import java.util.List;

public class OrdersListVO {

    private List<Order> orders;

    public OrdersListVO() {
    }

    public OrdersListVO(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
