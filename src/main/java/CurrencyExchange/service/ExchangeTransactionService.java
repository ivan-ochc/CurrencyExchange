package CurrencyExchange.service;

import CurrencyExchange.model.ExchangeTransaction;
import CurrencyExchange.vo.TransactionsListVO;

public interface ExchangeTransactionService {

    public void addTransaction(ExchangeTransaction exchangeTransaction);
    public TransactionsListVO getTransactions(String userName);
    public void declineTransaction(ExchangeTransaction exchangeTransaction);
    public void acceptTransaction();
    public ExchangeTransaction getExchangeTransaction(int transactionId);
}
