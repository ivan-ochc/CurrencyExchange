package currencyexchange.controller;

import currencyexchange.model.Role;
import currencyexchange.model.User;
import currencyexchange.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private Role role;

    @Autowired
    @Qualifier("userQ")
    private UserManager userManager;


    @RequestMapping(value="/registration", method = {RequestMethod.GET, RequestMethod.POST})
    public String goToRegistrationPage(Model model) {
       if (model.containsAttribute("user")) {
            return "registration";
        }
        return "registration";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid User user){

        String userName = userManager.getUser(user);

        if (userName == null) {
            user.setEnabled("true");
            user.setRole(role.ROLE_USER);
            userManager.saveUser(user);
            return "success";
        }
        else {
            return "nameError";
        }


    }

}
