package currencyexchange.controller;

import com.fasterxml.jackson.annotation.JsonView;
import currencyexchange.emailservice.MailExecutor;
import currencyexchange.model.ExchangeTransaction;
import currencyexchange.model.Order;
import currencyexchange.model.User;
import currencyexchange.service.ExchangeTransactionService;
import currencyexchange.service.OrderService;
import currencyexchange.service.UserManager;
import currencyexchange.view.View;
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

    @Autowired
    private MailExecutor mailExecutor;

    @RequestMapping(value = "/protected/orders/myTransactions",method = {RequestMethod.GET})
    public ModelAndView getMyTransactionsPage() {
        ModelAndView myTransactionsView = new ModelAndView("myTransactionsPage");
        return myTransactionsView;
    }

    @RequestMapping(value = "/protected/orders/myTransactions",method = RequestMethod.GET, produces = "application/json")
    @JsonView(View.Public.class)
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

        User userReceiver = exchangeTransaction.getUser();

        mailExecutor.sendMailTo(userReceiver.getEmail(),"Your offer was declined", resumingOrder.getAuthorName() + " has declined your offer.");

        exchangeTransactionService.declineTransaction(exchangeTransaction);
        return createMyTransactionsListResponse(session);
    }

    @RequestMapping(value = "/protected/orders/acceptTransaction/{transactionId}", produces = "application/json")
    public ResponseEntity<?> acceptTransaction
    (@ModelAttribute("exchangeTransaction") ExchangeTransaction exchangeTransaction, @PathVariable("transactionId") Integer transactionId, HttpSession session){
        exchangeTransaction =  exchangeTransactionService.getExchangeTransaction(transactionId);
        User user = (User)session.getAttribute("user");
        Order order =  exchangeTransaction.getOrder();
        User userReceiver = exchangeTransaction.getUser();
        if (order.getAmount() == 0 && order.getExchangeTransactions().size() == 1) {
            user.removeOrder(order);
            orderService.removeOrder(order);
        } else {
            user.removeTransaction(exchangeTransaction);
            order.removeTransaction(exchangeTransaction);
            exchangeTransactionService.declineTransaction(exchangeTransaction);
        }


        mailExecutor.sendMailTo(userReceiver.getEmail(), "Your offer was accepted", order.getAuthorName() + " has accepted your offer.");

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
