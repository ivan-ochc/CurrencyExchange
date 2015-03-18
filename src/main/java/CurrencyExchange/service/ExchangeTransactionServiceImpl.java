package CurrencyExchange.service;

import CurrencyExchange.model.ExchangeTransaction;
import CurrencyExchange.repository.ExchangeTransactionDAO;
import CurrencyExchange.vo.TransactionsListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ExchangeTransactionServiceImpl implements ExchangeTransactionService {

    @Autowired
    @Qualifier("transactionD")
    private ExchangeTransactionDAO exchangeTransactionDAO;

    @Override
    @Transactional
    public void addTransaction(ExchangeTransaction exchangeTransaction) {
        exchangeTransactionDAO.addTransaction(exchangeTransaction);
    }

    @Override
    @Transactional
    public TransactionsListVO getTransactions(String userName) {
        List result =  exchangeTransactionDAO.getTransactions(userName);
        return new TransactionsListVO(result);
    }

    @Override
    @Transactional
    public void declineTransaction(ExchangeTransaction exchangeTransaction) {
        exchangeTransactionDAO.declineTransaction(exchangeTransaction);
    }

    @Override
    public void acceptTransaction() {

    }

    @Override
    @Transactional
    public ExchangeTransaction getExchangeTransaction(int transactionId) {
        return exchangeTransactionDAO.getExchangeTransaction(transactionId);
    }

}
