package currencyexchange.service;

import currencyexchange.model.User;
import currencyexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByName(String userName){
        return userRepository.findByName(userName);
    }
}
