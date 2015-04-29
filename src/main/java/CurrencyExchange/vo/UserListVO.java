package currencyexchange.vo;

import com.fasterxml.jackson.annotation.JsonView;
import currencyexchange.model.User;
import currencyexchange.view.View;

import java.util.List;

public class UserListVO {
    @JsonView(View.Secure.class)
    private List<User> users;

    public UserListVO(List<User> users){
        this.users = users;
    }

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }
}
