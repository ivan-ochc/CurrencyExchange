package currencyexchange.vo;

import currencyexchange.model.User;

import java.util.List;

public class UserListVO {

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
