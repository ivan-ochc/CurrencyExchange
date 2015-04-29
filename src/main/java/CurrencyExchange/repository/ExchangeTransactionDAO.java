package currencyexchange.repository;

import currencyexchange.model.ExchangeTransaction;

import java.util.List;

public interface ExchangeTransactionDAO {

    public void addTransaction(ExchangeTransaction exchangeTransaction);
    public List<ExchangeTransaction> getTransactions(String userName);
    public void declineTransaction(ExchangeTransaction exchangeTransaction);
    public ExchangeTransaction getExchangeTransaction(int transactionId);


}
