package currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import currencyexchange.view.View;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonView(View.Secure.class)
    private int userId;

    @NotEmpty
    @Email
    @JsonView(View.Secure.class)
    private String email;
    @Size(min=2, max=30)
    @JsonView(View.Name.class)
    private String name;
    @JsonView(View.Secure.class)
    private String enabled;
    @NotEmpty
    @JsonView(View.Secure.class)
    private String password;
    @JsonView(View.Public.class)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", orphanRemoval=true)
    @Cascade({CascadeType.ALL})

    private List<Order> orders = new ArrayList<Order>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", orphanRemoval=true)
    @Cascade({CascadeType.ALL})

    private List<ExchangeTransaction> exchangeTransactions = new ArrayList<ExchangeTransaction>();

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonView(View.Secure.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    @JsonIgnore
    public List<ExchangeTransaction> getTransactions(){
        return exchangeTransactions;
    }

    public void addToOrder(Order order) {
        order.setUser(this);
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public void addToTransaction(ExchangeTransaction exchangeTransaction){
        exchangeTransaction.setUser(this);
        this.exchangeTransactions.add(exchangeTransaction);
    }

    public void removeTransaction(ExchangeTransaction exchangeTransaction){
        exchangeTransactions.remove(exchangeTransaction);
        exchangeTransaction.setUser(null);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}