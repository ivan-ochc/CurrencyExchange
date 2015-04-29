package currencyexchange.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import currencyexchange.view.View;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(name = "exchange_transaction")
public class ExchangeTransaction implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView(View.Public.class)
    private int transactionId;
    @JsonView(View.Public.class)
    private int transactionAmount;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = true, updatable = false)
    @JsonView(View.Public.class)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = true, updatable = false)
    @JsonView(View.Public.class)
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
