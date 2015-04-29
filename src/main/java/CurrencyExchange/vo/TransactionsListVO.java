package currencyexchange.vo;

import com.fasterxml.jackson.annotation.JsonView;
import currencyexchange.model.ExchangeTransaction;
import currencyexchange.view.View;

import java.util.List;

public class TransactionsListVO {

    @JsonView(View.Public.class)
    private List<ExchangeTransaction> transactions;

    public TransactionsListVO(List<ExchangeTransaction> transactions){
        this.transactions = transactions;
    }

    public List<ExchangeTransaction> getTransactions(){
        return transactions;
    }

    public void setTransactions(List<ExchangeTransaction> transactions){
        this.transactions = transactions;
    }
}

