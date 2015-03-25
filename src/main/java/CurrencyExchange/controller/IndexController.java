package currencyexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value = "/protected/home")
    public ModelAndView goToWelcomePage(Principal principal, HttpServletRequest request)  {
        ModelAndView welcomePageView = new ModelAndView("welcomePage");
        final String currentUser = principal.getName();
        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", currentUser);
        return welcomePageView;
    }
}
