package currencyexchange.controller;

import currencyexchange.model.ExchangeTransaction;
import currencyexchange.model.Order;
import currencyexchange.model.User;
import currencyexchange.service.ExchangeTransactionService;
import currencyexchange.service.OrderService;
import currencyexchange.service.UserManager;
import currencyexchange.vo.TransactionsListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class TransactionController {
    @Autowired
    private ExchangeTransactionService exchangeTransactionService;

    @Autowired
    @Qualifier("userQ")
    private UserManager userManager;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/protected/orders/myTransactions",method = {RequestMethod.GET})
    public ModelAndView getMyTransactionsPage() {
        ModelAndView myTransactionsView = new ModelAndView("myTransactionsPage");
        return myTransactionsView;
    }

    @RequestMapping(value = "/protected/orders/myTransactions",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getMyTransactions(HttpSession session) {
        return createMyTransactionsListResponse(session);
    }


    @RequestMapping(value = "/protected/orders/declineTransaction/{transactionId}", produces = "application/json")
    public ResponseEntity<?> declineTransaction
    (@ModelAttribute("exchangeTransaction") ExchangeTransaction exchangeTransaction, @PathVariable("transactionId") Integer transactionId, HttpSession session){
        exchangeTransaction =  exchangeTransactionService.getExchangeTransaction(transactionId);
        int resumingAmount = exchangeTransaction.getTransactionAmount();
        Order resumingOrder = exchangeTransaction.getOrder();
        int orderAmount = resumingOrder.getAmount();
        resumingOrder.setAmount(orderAmount+resumingAmount);
        orderService.updateOrder(resumingOrder);
        exchangeTransactionService.declineTransaction(exchangeTransaction);
        return createMyTransactionsListResponse(session);
    }

    @RequestMapping(value = "/protected/orders/acceptTransaction/{transactionId}", produces = "application/json")
    public ResponseEntity<?> acceptTransaction
    (@ModelAttribute("exchangeTransaction") ExchangeTransaction exchangeTransaction, @PathVariable("transactionId") Integer transactionId, HttpSession session){
        exchangeTransaction =  exchangeTransactionService.getExchangeTransaction(transactionId);
        User user = userManager.getUserObject(userManager.getUserId(session.getAttribute("currentUser").toString()));
        Order order =  exchangeTransaction.getOrder();
        if (order.getAmount() == 0 && order.getExchangeTransactions().size() == 1) {
            user.removeOrder(order);
            orderService.removeOrder(order);
        } else {
            user.removeTransaction(exchangeTransaction);
            order.removeTransaction(exchangeTransaction);
            exchangeTransactionService.declineTransaction(exchangeTransaction);
        }
        return createMyTransactionsListResponse(session);
    }

    private ResponseEntity<?> createMyTransactionsListResponse (HttpSession session){
        TransactionsListVO myTransactionsListVO =  exchangeTransactionService.getTransactions(session.getAttribute("currentUser").toString());
        return returnTransactionsListToUser(myTransactionsListVO);
    }

    private ResponseEntity<TransactionsListVO> returnTransactionsListToUser(TransactionsListVO transactions) {
        return new ResponseEntity<TransactionsListVO>(transactions, HttpStatus.OK);
    }

}
