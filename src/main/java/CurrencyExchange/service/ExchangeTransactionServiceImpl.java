package currencyexchange.service;

import currencyexchange.model.ExchangeTransaction;
import currencyexchange.repository.ExchangeTransactionDAO;
import currencyexchange.vo.TransactionsListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ExchangeTransactionServiceImpl implements ExchangeTransactionService {

    @Autowired
    private ExchangeTransactionDAO exchangeTransactionDAO;

    @Override
    @Transactional
    public void addTransaction(ExchangeTransaction exchangeTransaction) {
        exchangeTransactionDAO.addTransaction(exchangeTransaction);
    }

    @Override
    @Transactional
    public TransactionsListVO getTransactions(String userName) {
        List<ExchangeTransaction> result =  exchangeTransactionDAO.getTransactions(userName);
        return new TransactionsListVO(result);
    }

    @Override
    @Transactional
    public void declineTransaction(ExchangeTransaction exchangeTransaction) {
        exchangeTransactionDAO.declineTransaction(exchangeTransaction);
    }

    @Override
    @Transactional
    public ExchangeTransaction getExchangeTransaction(int transactionId) {
        return exchangeTransactionDAO.getExchangeTransaction(transactionId);
    }

}
