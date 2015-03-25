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
    public List getTransactions(String userName) {
        List transactions = sessionFactory.getCurrentSession().createQuery("from ExchangeTransaction where orderId in (select orderId from Order where authorName = '"+ userName +"' )").list();
        return transactions;
    }

    @Override
    public void declineTransaction(ExchangeTransaction exchangeTransaction) {
        sessionFactory.getCurrentSession().delete(exchangeTransaction);
    }

    @Override
    public void acceptTransaction() {

    }

    @Override
    public ExchangeTransaction getExchangeTransaction(int transactionId) {
        return (ExchangeTransaction) sessionFactory.getCurrentSession().get(ExchangeTransaction.class, transactionId);
    }
}
