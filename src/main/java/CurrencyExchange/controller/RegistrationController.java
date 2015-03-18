package CurrencyExchange.controller;

import CurrencyExchange.model.Role;
import CurrencyExchange.model.User;
import CurrencyExchange.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
//@RequestMapping("/registration")
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redirectAttributes, Model model)
    {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/registration";
        }

        String userName = userManager.getUser(user);

        if (userName == null) {
            user.setEnabled("true");
            user.setRole(role.ROLE_USER);
//            if (!user.getPassword().equals(user.getConfirmPassword())) {
////                redirectAttributes.addFlashAttribute("pwdMessage", "Passwords are different");
//                return "redirect:/registration?passwordError";
//            }
            userManager.saveUser(user);
            model.addAttribute("success", "success");
//            redirectAttributes.addFlashAttribute("userMessage", "User was successfully created");
            return "redirect:/registration";
        }
        else
        {
            model.addAttribute("NameError", "nameError");
        //    redirectAttributes.addFlashAttribute("errorMessage", "This user name has already used. Please, try another one");
            return "redirect:/registration?nameError";
        }


    }

}
