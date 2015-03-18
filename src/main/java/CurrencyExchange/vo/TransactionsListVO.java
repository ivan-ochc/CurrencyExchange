package CurrencyExchange.vo;

import java.util.List;

public class TransactionsListVO {
    private List transactions;

    public TransactionsListVO(List transactions){
        this.transactions = transactions;
    }

    public List getTransactions(){
        return transactions;
    }

    public void setTransactions(List transactions){
        this.transactions = transactions;
    }
}

