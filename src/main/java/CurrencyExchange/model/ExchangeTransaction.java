package CurrencyExchange.model;

import javax.persistence.*;

@Entity
@Table(name = "exchange_transaction")
public class ExchangeTransaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int transactionId;

    private int transactionAmount;
    @ManyToOne
    @JoinColumn(name = "orderId", insertable = true, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = true, updatable = false)
    private User user;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
