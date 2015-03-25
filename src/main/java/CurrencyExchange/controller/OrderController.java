package currencyexchange.controller;

import currencyexchange.model.ExchangeTransaction;
import currencyexchange.model.Order;
import currencyexchange.model.User;
import currencyexchange.service.ExchangeTransactionService;
import currencyexchange.service.OrderService;
import currencyexchange.service.UserManager;
import currencyexchange.vo.OrdersListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @RequestMapping(value = "/myOrders",method = {RequestMethod.GET}) public ModelAndView getMyOrdersPage() {
        ModelAndView myOrdersView = new ModelAndView("myOrdersPage");
        return myOrdersView;
    }

    @RequestMapping(value = "/allOrders",method = {RequestMethod.GET}) public ModelAndView getAllOrdersPage() {
        ModelAndView allOrdersView = new ModelAndView("allOrdersPage");
        return allOrdersView;
    }


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
        User user = userManager.getUserObject(userManager.getUserId(session.getAttribute("currentUser").toString()));
        Order order = orderService.getOrder(orderId);
        user.removeOrder(order);
        orderService.removeOrder(order);
        return createMyOrdersListResponse(session);
    }

    @RequestMapping(value = "/addOrder", produces = "application/json")
    public ResponseEntity<?> addOrder(@ModelAttribute("order") Order order, HttpSession session){
        User user = userManager.getUserObject(userManager.getUserId(session.getAttribute("currentUser").toString()));
        order.setAuthorName(session.getAttribute("currentUser").toString());
        order.setOrderDate((new Date()));
        user.addToOrder(order);
        orderService.addOrder(order);
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
        User user = userManager.getUserObject(userManager.getUserId(session.getAttribute("currentUser").toString()));
        user.addToTransaction(exchangeTransaction);
        Order order = orderService.getOrder(orderId);

        if (order.getAmount() >= exchangeTransaction.getTransactionAmount()){
            int newAmount = order.getAmount() - exchangeTransaction.getTransactionAmount();
            order.setAmount(newAmount);
            orderService.updateOrder(order);
            order.addToTransaction(exchangeTransaction);
            exchangeTransactionService.addTransaction(exchangeTransaction);
        }

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
