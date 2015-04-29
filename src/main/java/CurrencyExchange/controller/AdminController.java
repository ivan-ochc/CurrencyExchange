package currencyexchange.controller;

import com.fasterxml.jackson.annotation.JsonView;
import currencyexchange.model.User;
import currencyexchange.service.UserManager;
import currencyexchange.view.View;
import currencyexchange.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/protected/adminPage")
public class AdminController {

    @Autowired
    @Qualifier("userQ")
    private UserManager userManager;

    @RequestMapping(method = {RequestMethod.GET}) public ModelAndView getAdminPage(){
        ModelAndView adminPageView = new ModelAndView("adminPage");
        return adminPageView;
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @JsonView(View.Secure.class)
    public ResponseEntity<?> getAllUsers(){
        return createUserListResponse();
    }

    @RequestMapping(value = "/delete/{userId}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
        User user = userManager.getUserObjectById(userId);
        userManager.deleteUser(user);
        return createUserListResponse();
    }

    private ResponseEntity<?> createUserListResponse(){
        UserListVO userList = userManager.getUserList();
        return returnUserListToAdmin(userList);
    }

    private ResponseEntity<UserListVO> returnUserListToAdmin(UserListVO users){
      return new ResponseEntity<UserListVO>(users, HttpStatus.OK);
    }

}
