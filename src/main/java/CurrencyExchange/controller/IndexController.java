package currencyexchange.controller;

import currencyexchange.model.User;
import currencyexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/protected/home")
    public ModelAndView goToWelcomePage(Principal principal, HttpServletRequest request, HttpSession session)  {
        ModelAndView welcomePageView = new ModelAndView("welcomePage");
        User user = (User)session.getAttribute("user");
        if (user == null){
        String currentUserName = principal.getName();
        user = userService.findByName(currentUserName);
        session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("currentUser", currentUserName);
        }
        return welcomePageView;
    }
}
