package CurrencyExchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CurrencyExchange.model.User;
import CurrencyExchange.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByName(String name){
        return userRepository.findByName(name);
    }


}
