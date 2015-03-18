package CurrencyExchange.repository;

import CurrencyExchange.model.ExchangeTransaction;

import java.util.List;

public interface ExchangeTransactionDAO {

    public void addTransaction(ExchangeTransaction exchangeTransaction);
    public List getTransactions(String userName);
    public void declineTransaction(ExchangeTransaction exchangeTransaction);
    public void acceptTransaction();
    public ExchangeTransaction getExchangeTransaction(int transactionId);


}
