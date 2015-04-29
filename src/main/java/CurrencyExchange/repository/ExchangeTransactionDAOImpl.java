package currencyexchange.repository;

import currencyexchange.model.ExchangeTransaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExchangeTransactionDAOImpl implements ExchangeTransactionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTransaction(ExchangeTransaction exchangeTransaction) {
        sessionFactory.getCurrentSession().save(exchangeTransaction);
    }

    @Override
    public List<ExchangeTransaction> getTransactions(String userName) {
        List<ExchangeTransaction> transactions = sessionFactory.getCurrentSession().createQuery("from ExchangeTransaction where orderId in (select orderId from Order where authorName = '"+ userName +"' )").list();
    //      List transactions = sessionFactory.getCurrentSession().createQuery("select u.name as name, t.transactionAmount, o.currency, o.exchangeRate, o.orderType, u.location from ExchangeTransaction t, User u, Order o where o.orderId in (select orderId from Order where authorName = '"+ userName +"' ) and t.order = o and t.user = u").list();
        return transactions;
    }

    @Override
    public void declineTransaction(ExchangeTransaction exchangeTransaction) {
        sessionFactory.getCurrentSession().delete(exchangeTransaction);
    }

    @Override
    public ExchangeTransaction getExchangeTransaction(int transactionId) {
        return (ExchangeTransaction) sessionFactory.getCurrentSession().get(ExchangeTransaction.class, transactionId);
    }
}
