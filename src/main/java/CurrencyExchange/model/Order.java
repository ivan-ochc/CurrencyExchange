package currencyexchange.model;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int orderId;

    private String authorName;
    private String orderType;
    private String currency;
    private BigDecimal exchangeRate;
    private int amount;
    private String location;
    private String contactInfo;
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = true, updatable = false)
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order", orphanRemoval=true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<ExchangeTransaction> exchangeTransactions = new ArrayList<ExchangeTransaction>();

    public int getId() {
        return orderId;
    }

    public void setId(int id) {
        this.orderId = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String recordName) {
        this.authorName = recordName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String recordComment) {
        this.orderType = recordComment;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date recordDate) {
        this.orderDate = recordDate;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }
    @JsonIgnore
    public List getExchangeTransactions(){
        return exchangeTransactions;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(Object o) {
        return ((Order) o).orderId == this.orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addToTransaction(ExchangeTransaction exchangeTransaction){
        exchangeTransaction.setOrder(this);
        this.exchangeTransactions.add(exchangeTransaction);
    }

    public void removeTransaction(ExchangeTransaction exchangeTransaction){
        exchangeTransactions.remove(exchangeTransaction);
        exchangeTransaction.setOrder(null);

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
