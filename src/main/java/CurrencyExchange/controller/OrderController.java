package currencyexchange.controller;

import currencyexchange.emailservice.MailExecutor;
import currencyexchange.emailservice.MailMail;
import currencyexchange.model.ExchangeTransaction;
import currencyexchange.model.Order;
import currencyexchange.model.User;
import currencyexchange.service.ExchangeTransactionService;
import currencyexchange.service.OrderService;
import currencyexchange.service.UserManager;
import currencyexchange.view.View;
import currencyexchange.vo.OrdersListVO;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/protected/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    @Qualifier("userQ")
    private UserManager userManager;

    @Autowired
    private ExchangeTransactionService exchangeTransactionService;

    @Autowired
    private MailMail mailSender;

    @Autowired
    private MailExecutor mailExecutor;

    @RequestMapping(value = "/myOrders",method = {RequestMethod.GET}) public ModelAndView getMyOrdersPage() {
        ModelAndView myOrdersView = new ModelAndView("myOrdersPage");
        return myOrdersView;
    }

    @RequestMapping(value = "/allOrders",method = {RequestMethod.GET}) public ModelAndView getAllOrdersPage() {
        ModelAndView allOrdersView = new ModelAndView("allOrdersPage");
        return allOrdersView;
    }

    @JsonView(View.Public.class)
    @RequestMapping(value = "/myOrders",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getMyOrders(HttpSession session) {
        return createMyOrdersListResponse(session);
    }


    @RequestMapping(value = "/allOrders",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllOrders(HttpSession session) {
        return createAllOrdersListResponse(session);
    }


    @RequestMapping(value = "/delete/{orderId}", produces = "application/json")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Integer orderId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        Order order = orderService.getOrder(orderId);
        user.removeOrder(order);
        orderService.removeOrder(order);

        return createMyOrdersListResponse(session);
    }

    @RequestMapping(value = "/addOrder", produces = "application/json")
    public ResponseEntity<?> addOrder(@ModelAttribute("order") Order order, HttpSession session){
        User user = (User)session.getAttribute("user");
        order.setAuthorName(session.getAttribute("currentUser").toString());
        order.setOrderDate((new Date()));
        user.addToOrder(order);
        orderService.addOrder(order);

        List<String> allRecipients =  userManager.getAllEmails(session.getAttribute("currentUser").toString());

        mailExecutor.sendMailToAll(allRecipients,
                                       "New order was created",
                                       "<html><body> <b>New order was created by " +user.getName()+"<b>. " +
                                       "<p>Offer details you can find below:" + "<br>" +
                                       "<b>Author</b>: " + user.getName() + "<br>" +
                                       "<b>Order Type</b>: " + order.getOrderType() + "<br>" +
                                       "<b>Amount</b>: " + order.getAmount() + "<br>" +
                                       "<b>Exchange Rate</b>: " + order.getExchangeRate() + "<br>" +
                                       "<b>Currency</b>: " + order.getCurrency() + "<br>" +
                                       "<b>Location</b>: " + user.getLocation() + "<br>" +
                                       "</body></html>");

        return createMyOrdersListResponse(session);
    }

    @RequestMapping(value = "/update", produces = "application/json")
    public ResponseEntity<?> updateOrder(@RequestBody Order order, HttpSession session){
        orderService.updateOrder(order);
        return createMyOrdersListResponse(session);
    }

    @RequestMapping(value = "/addTransaction/{orderId}", produces = "application/json")
    public ResponseEntity<?> addTransaction
    (@ModelAttribute("exchangeTransaction") ExchangeTransaction exchangeTransaction, @PathVariable("orderId") Integer orderId, HttpSession session){
        User user = (User)session.getAttribute("user");
        user.addToTransaction(exchangeTransaction);
        Order order = orderService.getOrder(orderId);

        if (order.getAmount() >= exchangeTransaction.getTransactionAmount()){
            int newAmount = order.getAmount() - exchangeTransaction.getTransactionAmount();
            order.setAmount(newAmount);
            orderService.updateOrder(order);
            order.addToTransaction(exchangeTransaction);
            exchangeTransactionService.addTransaction(exchangeTransaction);
        }

        User userReceiver = userManager.getUserObjectById(userManager.getUserId(order.getAuthorName()));

        mailExecutor.sendMailTo(userReceiver.getEmail(),
                                    "You have got a new offer",
                                    "<html><body> <b>You have got a new offer from " +user.getName()+"<b>. " +
                                    "<p>Offer details you can find below:" + "<br>" +
                                    "<b>Author</b>: " + user.getName() + "<br>" +
                                    "<b>Amount</b>: " + exchangeTransaction.getTransactionAmount() + "<br>" +
                                    "<b>Exchange Rate</b>: " + order.getExchangeRate() + "<br>" +
                                    "<b>Location</b>: " + user.getLocation() + "<br>" +
                                    "<p>Please accept or decline this offer on 'My Transactions' page</body></html>");

        return createAllOrdersListResponse(session);
    }


    private ResponseEntity<?> createMyOrdersListResponse(HttpSession session) {
        OrdersListVO myOrdersListVO = orderService.findMyOrders(session.getAttribute("currentUser").toString());
        return returnListToUser(myOrdersListVO);
    }


    private ResponseEntity<?> createAllOrdersListResponse(HttpSession session) {
        OrdersListVO allOrdersListVO = orderService.findAllOrders(session.getAttribute("currentUser").toString());
        return returnListToUser(allOrdersListVO);
    }

    private ResponseEntity<OrdersListVO> returnListToUser(OrdersListVO orders) {
        return new ResponseEntity<OrdersListVO>(orders, HttpStatus.OK);
    }
}
